package agregator.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import agregator.io.Log;

@WebListener
public class Logger implements ServletRequestListener, ServletContextAttributeListener {
    public Logger() {
        Log.writeEvent("start log");
    }

    public void attributeAdded(ServletContextAttributeEvent event)  { 
        Log.writeEvent(event.getName() + " context attribute initialized with value " + event.getValue());
    }

    public void requestDestroyed(ServletRequestEvent sre)  { 
        Log.writeEvent("end request processing");
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
        Log.writeEvent(event.getName() + " context attribute was deleted");
    }

    public void requestInitialized(ServletRequestEvent sre)  {
        Log.writeEvent("start request processing");
    }
    
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
        Log.writeEvent(event.getName() + " context attribute change value to " + event.getValue());
    }
	
}
