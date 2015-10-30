package agregator.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import agregator.io.Log;

@WebListener
public class Logger implements ServletRequestListener, ServletContextAttributeListener {
    private Log log;
    
    public Logger() {
        this.log = new Log();
    }

    public void attributeAdded(ServletContextAttributeEvent event)  { 
        log.writeEvent(event.getName() + " context attribute initialized with value " + event.getValue());
    }

    public void requestDestroyed(ServletRequestEvent sre)  { 
        log.writeEvent("end request processing");
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
        log.writeEvent(event.getName() + " context attribute was deleted");
    }

    public void requestInitialized(ServletRequestEvent sre)  {
        log.writeEvent("start request processing");
    }
    
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
        log.writeEvent(event.getName() + " context attribute change value to " + event.getValue());
    }
	
}
