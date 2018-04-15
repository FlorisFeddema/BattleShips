package client;

import com.google.gson.JsonObject;
import seabattlegui.ShotType;

import java.util.concurrent.SynchronousQueue;

public interface IEventClientSocket {
    SynchronousQueue<Integer> playerId = new SynchronousQueue<>();
    SynchronousQueue<JsonObject> returnShot = new SynchronousQueue<>();
    ;

    void sendReady();

    void sendResult(ShotType shot);

    void sendResult(ShotType shot, int x, int y, int length, boolean horizontal);

    void sendShot(int x, int y);
}
