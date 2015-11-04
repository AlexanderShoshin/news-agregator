package agregator.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;

import agregator.core.StoragesKeeper;
import agregator.io.Log;

@WebListener
public class Logger implements ServletRequestListener, ServletContextAttributeListener {
    private Log getLog(ServletContext context) {
        ApplicationContext springContext = StoragesKeeper.getSpringContext(context);
        return (Log) springContext.getBean("consoleLogger");
    }
    
    public void attributeAdded(ServletContextAttributeEvent event)  {
        Log log = getLog(event.getServletContext());
        log.writeEvent(event.getName() + " context attribute initialized with value " + event.getValue());
    }

    public void requestDestroyed(ServletRequestEvent sre)  { 
        Log log = getLog(sre.getServletContext());
        log.writeEvent("end request processing");
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
        Log log = getLog(event.getServletContext());
        log.writeEvent(event.getName() + " context attribute was deleted");
    }

    public void requestInitialized(ServletRequestEvent sre)  {
        Log log = getLog(sre.getServletContext());
        log.writeEvent("start request processing");
    }
    
    public void attributeReplaced(ServletContextAttributeEvent event)  {
        Log log = getLog(event.getServletContext());
        log.writeEvent(event.getName() + " context attribute change value to " + event.getValue());
    }
	
}
