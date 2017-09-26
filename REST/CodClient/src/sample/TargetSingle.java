package sample;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class TargetSingle {
    private static TargetSingle ourInstance = new TargetSingle();

    public static TargetSingle getInstance() {
        return ourInstance;
    }

    private WebTarget target;

    public WebTarget getTarget() {
        return target;
    }

    private TargetSingle() {
        URI baseURI = UriBuilder.fromUri("http://localhost:8080/").build();
        //Client client = ClientBuilder.newClient();
        //target = client.target(baseURI);
    }
}
