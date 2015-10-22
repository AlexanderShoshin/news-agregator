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

import agregator.core.StoragesKeeper;
import agregator.io.StateStorage;
import agregator.utils.NewsProcessor;

@WebFilter("/GetNewsServlet")
public class CategoryFilter implements Filter {
    private StateStorage stateStorage;
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    ReadableResponseWrapper wrapper = new ReadableResponseWrapper((HttpServletResponse) response);
	    chain.doFilter(request, wrapper);
	    OutputStream out = response.getOutputStream();
	    out.write(filterOutput(wrapper.getData()).getBytes());
	    out.close();
	}

	private String filterOutput(String output) {
	    String filteredCategory;
	    
	    if (stateStorage.getCategoryFilterEnabled()) {
	        filteredCategory = stateStorage.getCategoryFilter();
	        return NewsProcessor.filterByField(output, "category", filteredCategory);
	    } else {
	        return output;
	    }
	    
	}

	public void init(FilterConfig fConfig) throws ServletException {
	    stateStorage = StoragesKeeper.getStateStorage(fConfig.getServletContext());
	}
}