package pluto.maven.websocket;

public class RunServer {

    public static void main(String[] arags){
        MyWebSocketServer server = new MyWebSocketServer(8080);
        server.start();
    }

}
