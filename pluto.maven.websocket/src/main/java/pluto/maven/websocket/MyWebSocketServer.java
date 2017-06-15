package pluto.maven.websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * Jave-webscoket 版本
 * @author a4yl9zz pxu3@mmm.com
 *
 */
public class MyWebSocketServer extends WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    public MyWebSocketServer(int port){
        super(new InetSocketAddress(port));
    }
    

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // TODO Auto-generated method stub
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // TODO Auto-generated method stub
        subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // TODO Auto-generated method stub
        System.out.println("收到消息:"+message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // TODO Auto-generated method stub
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        System.out.println("服务启动");
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        MyWebSocketServer.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        MyWebSocketServer.onlineCount--;
    }
    

}
