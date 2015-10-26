package agregator.io;

public interface SettingsStorage {
    void setLastItemSent(int cnt);
    void setIsAdminVisited(boolean isVisited);
    void setCategoryFilterEnabled(boolean isEnabled);
    void setCategoryFilter(String category);
    int getLastItemSent();
    boolean getIsAdminVisited();
    boolean getCategoryFilterEnabled();
    String getCategoryFilter();
}