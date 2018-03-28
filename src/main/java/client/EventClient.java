package client;

import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class EventClient {
    public static void main(String[] args) {
        URI uri = URI.create("ws://localhost:8096/seabattle/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            Session session = container.connectToServer(EventClientSocket.class, uri);
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
