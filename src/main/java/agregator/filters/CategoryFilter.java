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

import agregator.utils.NewsFilter;

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
	
	private String filterOutput(ServletContext context, String output) {
	    if (context.getAttribute("CatFilter") != null) {
	        return NewsFilter.filterByField(output, "category", "career");
	    } else {
	        return output;
	    }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}