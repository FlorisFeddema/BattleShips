package client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import seabattlegui.ShotType;
import seabattlelogic.IGameSocket;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.SynchronousQueue;

@ClientEndpoint
public class EventClientSocket implements IEventClientSocket {

    public SynchronousQueue<Integer> playerId;
    public SynchronousQueue<JsonObject> returnShot;

    private Session server;
    private IGameSocket game;

    public SynchronousQueue<Integer> getPlayerId() {
        return playerId;
    }

    public SynchronousQueue<JsonObject> getReturnShot() {
        return returnShot;
    }

    public EventClientSocket(IGameSocket game, String name) {
        this.game = game;
        playerId = new SynchronousQueue<>();
        returnShot = new SynchronousQueue<>();

        URI uri = URI.create("ws://localhost:8096/seabattle/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            //connects to server
            server = container.connectToServer(this, uri);

            JsonObject json = new JsonObject();
            json.addProperty("Register", name);

            //sends name to server
            server.getBasicRemote().sendText(json.toString());
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    public void sendReady() {
        JsonObject json = new JsonObject();
        json.addProperty("Ready", true);
        try {
            server.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResult(ShotType shot) {
        JsonObject json = new JsonObject();
        json.addProperty("ShotType", shot.toString());
        try {
            server.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResult(ShotType shot, int x, int y, int length, boolean horizontal) {
        JsonObject json = new JsonObject();
        json.addProperty("ShotType", shot.toString());
        json.addProperty("X", x);
        json.addProperty("Y", y);
        json.addProperty("Length", length);
        json.addProperty("Horizontal", horizontal);
        try {
            server.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShot(int x, int y) {
        JsonObject json = new JsonObject();
        json.addProperty("Fire", true);
        json.addProperty("X", x);
        json.addProperty("Y", y);
        System.out.println("We will destroy you");
        try {
            server.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }

    @OnMessage
    public void onWebSocketText(String message) {

        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        System.out.println("Message received");
        if (keyInJson(json, "Register")) {
            playerId.offer(json.get("Register").getAsInt());
        } else if (keyInJson(json, "EnemyName")) {
            game.setOpponent(json.get("EnemyName").getAsString());
        } else if (keyInJson(json, "Ready")) {
            game.setOpponentReady();
        } else if (keyInJson(json, "Fire")) {
            game.shotFiredMultiplayer(json.get("X").getAsInt(), json.get("Y").getAsInt());
        } else if (keyInJson(json, "ShotType")) {
            returnShot.offer(json);
        }
    }
    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("[Closed]: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        System.out.println("[ERROR] what: " + cause.getMessage());
    }

    private boolean keyInJson(JsonObject json, String key) {
        try {
            return json.has(key);

        } catch (NullPointerException ex) {
            return false;
        }
    }
}