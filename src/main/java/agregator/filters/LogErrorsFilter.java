package agregator.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import agregator.io.Log;

@WebFilter("/*")
public class LogErrorsFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    try {
	        chain.doFilter(request, response);
	    } catch (IOException exc) {
	        Log.writeEvent("ERROR " + exc.getMessage());
	        throw new IOException(exc.getMessage());
	    } catch (ServletException exc) {
	        Log.writeEvent("ERROR " + exc.getMessage());
            throw new ServletException(exc.getMessage());
	    }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}