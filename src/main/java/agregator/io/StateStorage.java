package agregator.io;

public interface StateStorage {
    void setLastItemSent(int cnt);
    int getLastItemSent();
}