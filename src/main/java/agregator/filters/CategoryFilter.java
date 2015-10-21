package agregator.filters;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/GetNewsServlet")
public class CategoryFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    ReadableResponseWrapper wrapper = new ReadableResponseWrapper((HttpServletResponse) response);
	    chain.doFilter(request, wrapper);
	    
	    OutputStream out = response.getOutputStream();
	    out.write(wrapper.getData().getBytes());
	    out.close();
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}