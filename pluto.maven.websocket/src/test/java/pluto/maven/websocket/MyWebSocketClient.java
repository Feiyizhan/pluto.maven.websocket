package pluto.maven.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Jave-webscoket 版本
 * @author a4yl9zz pxu3@mmm.com
 *
 */
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // TODO Auto-generated method stub
        System.out.println("open");
    }

    @Override
    public void onMessage(String message) {
        // TODO Auto-generated method stub
        System.out.println(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // TODO Auto-generated method stub
        System.out.println("close");
    }

    @Override
    public void onError(Exception ex) {
        // TODO Auto-generated method stub
        ex.printStackTrace();
    }
    
    public static void main(String[] args){
        String uri = "ws://localhost:8080";
        MyWebSocketClient client = new MyWebSocketClient(URI.create(uri));
        client.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            do {
                input = br.readLine();
                if (!input.equals("exit"))
                    client.send(input);

            } while (!input.equals("exit"));
            client.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
