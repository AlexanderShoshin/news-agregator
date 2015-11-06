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
import agregator.io.SettingsStorage;
import agregator.structure.NewsPack;
import agregator.utils.NewsParser;
import agregator.utils.NewsProcessor;

@WebFilter("/")
public class CategoryFilter implements Filter {
    private SettingsStorage settingsStorage;
    
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
	    NewsPack newsPack;
	    String filteredCategory;
	    
	    if (settingsStorage.getCategoryFilterEnabled()) {
	        filteredCategory = settingsStorage.getCategoryFilter();
	        newsPack = NewsProcessor.getNewsPack(output);
	        newsPack = NewsProcessor.filterByCategory(newsPack, filteredCategory);
	        return NewsParser.getJson(newsPack);
	    } else {
	        return output;
	    }
	    
	}

	public void init(FilterConfig fConfig) throws ServletException {
	    settingsStorage = StoragesKeeper.getSettingsStorage(fConfig.getServletContext());
	}
}