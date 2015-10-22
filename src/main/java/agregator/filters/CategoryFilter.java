package agregator.filters;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import agregator.utils.NewsProcessor;

@WebFilter("/GetNewsServlet")
public class CategoryFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    ReadableResponseWrapper wrapper = new ReadableResponseWrapper((HttpServletResponse) response);
	    chain.doFilter(request, wrapper);
	    OutputStream out = response.getOutputStream();
	    out.write(filterOutput(request.getServletContext(), wrapper.getData()).getBytes());
	    out.close();
	}
	// todo: add "switch on/off" filter to admin page 
	private String filterOutput(ServletContext context, String output) {
	    return NewsProcessor.filterByField(output, "category", "career");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}