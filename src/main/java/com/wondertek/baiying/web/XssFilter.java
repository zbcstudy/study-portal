package com.wondertek.baiying.web;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 跨站请求防范
 * Created by wd on 2017/9/8.
 */
@WebFilter(filterName = "XssFilter",urlPatterns = "/",asyncSupported = true)
public class XssFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XssFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("(XssFilter) initialize");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest =
                new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("(XssFilter) destory");
    }
}
