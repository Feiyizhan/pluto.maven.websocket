package pluto.maven.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * j2ee websocket 版本
 * @author a4yl9zz pxu3@mmm.com
 *
 */
public class MyClientApp {

    public Session session;

    protected void start() {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        String uri = "ws://localhost:8080/wsChat/chat";
        System.out.println("Connecting to" + uri);
        try {
            session = container.connectToServer(MyClient.class, URI.create(uri));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        MyClientApp client = new MyClientApp();
        client.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            do {
                input = br.readLine();
                if (!input.equals("exit"))
                    client.session.getBasicRemote().sendText(input);

            } while (!input.equals("exit"));
            client.session.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
