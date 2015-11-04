package agregator.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.context.ApplicationContext;

import agregator.core.StoragesKeeper;
import agregator.io.Log;

@WebFilter("/*")
public class LogErrorsFilter implements Filter {
    private Log log;
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    try {
	        chain.doFilter(request, response);
	    } catch (IOException exc) {
	        log.writeEvent("ERROR " + exc.getMessage());
	        throw new IOException(exc.getMessage());
	    } catch (ServletException exc) {
	        log.writeEvent("ERROR " + exc.getMessage());
            throw new ServletException(exc.getMessage());
	    }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	    log = getLog(fConfig.getServletContext());
	}
	
	private Log getLog(ServletContext context) {
        ApplicationContext springContext = StoragesKeeper.getSpringContext(context);
        return (Log) springContext.getBean("consoleLogger");
    }
}