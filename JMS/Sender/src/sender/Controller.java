package sender;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    /* Login */
    @FXML private TextField textInput;
    @FXML private ListView listView;

    @FXML
    private void okClicked(ActionEvent event) {
        try {
            String input = textInput.getText();
            Message msg = session.createTextMessage(input);            // send the message
            producer.send(msg);
            textInput.setText("");

            //System.out.println(msg.getJMSMessageID());
            TableRow newRow = new TableRow(input);
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
            props.put(("queue.questionsAnswers"), "questionsAnswers");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    // connect to the receiver destination
            sendDestination = (Destination) jndiContext.lookup("questionsDestination");
            producer = session.createProducer(sendDestination);
            receiveDestination = (Destination) jndiContext.lookup("questionsAnswers");
            consumer = session.createConsumer(receiveDestination);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
        listData = FXCollections.observableArrayList();
        waitingMessages = new Hashtable<String,TableRow>();
    }

    public void setMain(Main main){
        this.main = main;
        listView.setItems(listData);
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
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
