package agregator.io;

import java.util.List;

public interface StateStorage {
    void setNewsSequense(List<Integer> newsSequense);
    void setItemsSent(int cnt);
    List<Integer> getNewsSequense();
    int getItemsSent();
}