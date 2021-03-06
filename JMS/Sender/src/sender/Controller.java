package sender;

import com.google.gson.Gson;
import dto.IssueDto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;


public class Controller implements MessageListener {
    private Main main;
    private Connection connection; // to connect to the ActiveMQ
    private Session session; // session for creating messages, producers and
    private Destination sendDestination; // reference to a queue/topic destination
    private MessageProducer producer; // for sending messages
    private Destination receiveDestination; // reference to a queue/topic destination
    private MessageConsumer consumer; // for sending messages
    private Dictionary<String, TableRow> waitingMessages;
    private ObservableList<TableRow> listData;
    private ObservableList<String> gamesList;
    private ObservableList<String> actionList;
    /* Login */
    @FXML private TextField textInput;
    @FXML private ListView listView;
    @FXML private ComboBox gameCombo;
    @FXML private ComboBox actionCombo;

    @FXML
    private void okClicked(ActionEvent event) {
        try {
            String user = textInput.getText();
            IssueDto dto = new IssueDto(gameCombo.getSelectionModel().getSelectedItem().toString(),
                    actionCombo.getSelectionModel().getSelectedItem().toString(), user);
            String json = new Gson().toJson(dto);
            Message msg = session.createTextMessage(json);            // send the message
            msg.setJMSReplyTo(receiveDestination);
            producer.send(msg);
            textInput.setText("");

            //System.out.println(msg.getJMSMessageID());
            TableRow newRow = new TableRow(dto.getGame() + " " + dto.getIssue() + " " + dto.getUsername());
            waitingMessages.put(msg.getJMSMessageID(), newRow);
            listData.add(newRow);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public Controller() {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put(("queue.questionsDestination"), "questionsDestination");
            //props.put(("queue.questionsAnswers"), "questionsAnswers");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    // connect to the receiver destination
            sendDestination = (Destination) jndiContext.lookup("questionsDestination");
            producer = session.createProducer(sendDestination);
            //receiveDestination = (Destination) jndiContext.lookup("questionsAnswers");
            receiveDestination = session.createQueue("questionAnswers" + java.util.UUID.randomUUID());
            consumer = session.createConsumer(receiveDestination);
            connection.start(); // this is needed to start receiving messages
            consumer.setMessageListener(this);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
        listData = FXCollections.observableArrayList();
        gamesList = FXCollections.observableArrayList();
        actionList = FXCollections.observableArrayList();
        gamesList.addAll(new String[] { "Call of Duty", "Cuphead", "Forza"});
        actionList.addAll(new String[] { "Lost password", "Change email", "Appeal ban"});
        waitingMessages = new Hashtable<String,TableRow>();
    }

    public void setMain(Main main){
        this.main = main;
        listView.setItems(listData);
        gameCombo.setItems(gamesList);
        actionCombo.setItems(actionList);
    }

    @Override
    public void onMessage(Message message) {
        Platform.runLater(() -> {
            try {
                String correlationId = message.getJMSCorrelationID();
                TableRow row = waitingMessages.get(correlationId);
                if (row == null)
                {
                    System.out.println("Wrong message: " + correlationId);
                    listData.add(new TableRow("Got wrong response"));
                }
                else
                {
                    row.setResponse(((TextMessage) message).getText());
                    waitingMessages.remove(correlationId);
                    //listData.add(row);
                    listView.refresh();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
