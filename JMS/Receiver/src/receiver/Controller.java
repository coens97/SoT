package receiver;

import com.google.gson.Gson;
import dto.IssueDto;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Controller implements MessageListener {
    private Main main;
    /* Login */
    @FXML private TextField textInput;
    @FXML private ListView listView;
    @FXML private Label gameLabel;
    @FXML private Label issueLabel;
    @FXML private Label userLabel;
    private ObservableList<TableRow> listData;

    private Connection connection; // to connect to the ActiveMQ
    private Session session; // session for creating messages, producers and
    private Destination receiveDestination; // reference to a queue/topic destination
    private MessageConsumer consumer; // for sending messages
    private Destination sendDestination; // reference to a queue/topic destination
    private MessageProducer producer; // for sending messages

    public Controller(){
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,  "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");          // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put(("queue.questionsDestination"), "questionsDestination");
            props.put(("queue.questionsAnswers"), "questionsAnswers");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");

            connection = connectionFactory.createConnection();  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);   // connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup("questionsDestination");
            consumer = session.createConsumer(receiveDestination);
            connection.start(); // this is needed to start receiving messages
            consumer.setMessageListener(this);

            sendDestination = (Destination) jndiContext.lookup("questionsAnswers");
            producer = session.createProducer(sendDestination);
            }
            catch (NamingException | JMSException e) {
                e.printStackTrace();
        }
        // Set ui
        listData = FXCollections.observableArrayList();
    }

    @FXML
    private void okClicked(ActionEvent event) {
        TableRow row = (TableRow) listView.getSelectionModel().getSelectedItem();
        try {
            Message msg = session.createTextMessage(textInput.getText());            // send the message
            msg.setJMSCorrelationID(row.getId());
            producer.send(msg);
            textInput.setText("");
            listData.remove(row);
            gameLabel.setText("");
            issueLabel.setText("");
            userLabel.setText("");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }



    public void setMain(Main main) {
        this.main = main;
        listView.setItems(listData);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TableRow>() {

            @Override
            public void changed(ObservableValue<? extends TableRow> observable, TableRow oldValue, TableRow newValue) {
                // Your action here
                //System.out.println("Selected item: " + newValue);
                IssueDto issue = newValue.getIssue();
                gameLabel.setText(issue.getGame());
                issueLabel.setText(issue.getIssue());
                userLabel.setText(issue.getUsername());
            }
        });
    }

    @Override
    public void onMessage(Message message) {
        Platform.runLater(() -> {
            try {
                String text =((TextMessage) message).getText();
                IssueDto dto = new Gson().fromJson(text, IssueDto.class);
                listData.add(new TableRow(dto, message.getJMSMessageID()));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
