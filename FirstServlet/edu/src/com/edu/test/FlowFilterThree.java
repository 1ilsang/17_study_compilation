package com.edu.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;

@WebFilter(filterName = "timer", urlPatterns = "/third")
public class FlowFilterThree implements Filter{

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		long startTime = System.currentTimeMillis();
		chain.doFilter(req, res);
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		System.out.println("수행 시간 : "+executeTime +" ms");		
	}
	
	@Override
	public void destroy() {
		
	}
}
