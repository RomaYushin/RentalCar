package ua.nure.yushin.SummaryTask4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class EncodingFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	private static final String ENCODING_INIT_PARAM_NAME = "encoding";
	
	private static final String ENCODING_DEFAULT = "UTF-8";
	
	private String encoding;
	
	@Override
	public void destroy() {
		LOG.info("destroy");	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		LOG.info("doFilter starts");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		LOG.info("Requested URI: " + httpRequest.getRequestURI());
		
		String requestEncoding = request.getCharacterEncoding();
		LOG.info("requestEncoding: " + requestEncoding);
		
		if (requestEncoding == null) {
			request.setCharacterEncoding(encoding);
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
		
		LOG.info("encoding in init: " + encoding);
		if (encoding == null) {
			encoding = ENCODING_DEFAULT;
		}
	}

}
