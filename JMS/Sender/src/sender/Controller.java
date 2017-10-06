package sender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


public class Controller {
    private Main main;
    private Connection connection; // to connect to the ActiveMQ
    private Session session; // session for creating messages, producers and
    private Destination sendDestination; // reference to a queue/topic destination
    private MessageProducer producer; // for sending messages
    /* Login */
    @FXML private TextField textInput;
    @FXML private ListView listView;

    @FXML
    private void okClicked(ActionEvent event) {
        try {
            Message msg = session.createTextMessage(textInput.getText());            // send the message
            producer.send(msg);
            textInput.setText("");
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
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    // connect to the receiver destination
            sendDestination = (Destination) jndiContext.lookup("questionsDestination");
            producer = session.createProducer(sendDestination);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
