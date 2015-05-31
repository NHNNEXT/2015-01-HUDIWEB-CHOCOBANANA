package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/personal/*", "/party/*", "/guild/*"})
public class RedirectFilter implements Filter {
	private static final String START_PAGE = "/";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("user") == null){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(START_PAGE);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}