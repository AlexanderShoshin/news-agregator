package agregator.io;

public interface StateStorage {
    void setLastItemSent(int cnt);
    void setIsAdminVisited(boolean isVisited);
    int getLastItemSent();
    boolean getIsAdminVisited();
}