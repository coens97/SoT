package receiver;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }



    public void setMain(Main main) {
        this.main = main;
        listView.setItems(listData);
    }

    @Override
    public void onMessage(Message message) {
        Platform.runLater(() -> {
            try {
                String text =((TextMessage) message).getText();
                listData.add(new TableRow(text, message.getJMSCorrelationID()));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
