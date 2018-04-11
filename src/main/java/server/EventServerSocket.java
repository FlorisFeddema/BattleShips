package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/seabattle/")
public class EventServerSocket {
    static ArrayList<Session> sessions = new ArrayList<>();
    private static String[] players = new String[2];

    @OnOpen
    public void onConnect(Session session){
        System.out.println("[Connected] SessionID:"+session.getId());
        //broadcast(message);
        if (sessions.size() == 2) {
            try {
                session.close();
            } catch (IOException e) {
                System.out.println("To much players");
            }
            return;
        }
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }


    @OnMessage
    public void onMessageReceived(String message, Session session) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        if (keyInJson(json, "Register")) {
            //Register
            registerPlayer(session, json.get("Register").toString());
        } else if (keyInJson(json, "Ready")) {
            //Ready
            ReadyPlayer(session);
        } else if (keyInJson(json, "Fire")) {
            //Fire shot location
            SendShotLocation(json, session);
        } else if (keyInJson(json, "ShotType")) {
            //Fire shot type
            SendShotType(json, session);
        }
    }


    private void SendShotType(JsonObject json, Session session) {
        Session other = getOtherSession(session);
        try {
            other.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SendShotLocation(JsonObject json, Session session) {
        Session other = getOtherSession(session);
        try {
            other.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ReadyPlayer(Session session) {
        Session other = getOtherSession(session);

        JsonObject json = new JsonObject();
        json.addProperty("Ready", true);

        try {
            other.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            System.out.println("Could not send text");
            //ignore
        }
    }

    private void sendEnemyName() {
        int i = sessions.size() - 1;
        for (Session s : sessions) {
            try {
                JsonObject json = new JsonObject();
                json.addProperty("EnemyName", players[i]);
                s.getBasicRemote().sendText(json.toString());
            } catch (IOException e) {
                System.out.println("Could not send text");
                //ignore
            }
            i--;
        }
    }

    private void registerPlayer(Session session, String name) {
        int value = -1;
        if (sessions.size() == 2) {
            if (!players[0].equals(name)) {
                value = 1;
                players[1] = name;
            }
        } else if (sessions.size() == 1) {
            value = 0;
            players[0] = name;
        }
        JsonObject json = new JsonObject();
        json.addProperty("Register", value);

        try {
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            System.out.println("Could not send text");
            //ignore
        }

        if (sessions.size() == 2) {
            //set enemy names
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendEnemyName();
        }
    }

    private Session getOtherSession(Session session) {
        for (Session s : sessions) {
            if (!s.equals(session)) {
                return s;
            }
        }
        return null;
    }

    private boolean keyInJson(JsonObject json, String key) {
        try {
            return json.has(key);
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }
}
