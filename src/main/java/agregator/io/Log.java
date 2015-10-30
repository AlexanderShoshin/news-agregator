package agregator.io;

import java.util.Date;

public class Log {
    synchronized public void writeEvent(String message) {
        System.out.print(new Date());
        System.out.print(" - ");
        System.out.println(message);
    }
}