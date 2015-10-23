package agregator.io;

public interface StateStorage {
    void setLastItemSent(int cnt);
    void setIsAdminVisited(boolean isVisited);
    void setCategoryFilterEnabled(boolean isEnabled);
    void setCategoryFilter(String category);
    int getLastItemSent();
    boolean getIsAdminVisited();
    boolean getCategoryFilterEnabled();
    String getCategoryFilter();
}