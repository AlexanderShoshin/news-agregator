package agregator.io;

public interface SettingsStorage {
    void setIsAdminVisited(boolean isVisited);
    void setCategoryFilterEnabled(boolean isEnabled);
    void setCategoryFilter(String category);
    boolean getIsAdminVisited();
    boolean getCategoryFilterEnabled();
    String getCategoryFilter();
}