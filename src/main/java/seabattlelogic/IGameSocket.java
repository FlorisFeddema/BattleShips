package seabattlelogic;

public interface IGameSocket {
    void setOpponent(String name);

    void setOpponentReady();

    void shotFiredMultiplayer(int x, int y);
}
