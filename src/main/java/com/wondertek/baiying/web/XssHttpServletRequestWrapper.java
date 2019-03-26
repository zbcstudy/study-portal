package com.wondertek.baiying.web;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 请求恶意攻击
 * Created by wd on 2017/9/8.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getHeader(String name){
        String header = super.getHeader(name);
        return HtmlUtils.htmlEscape(header);
    }

    public String getParameter(String name){
        String parameter = super.getParameter(name);
        return HtmlUtils.htmlEscape(parameter);
    }

    public String[] getParameterValues(String name){
        String[] values = super.getParameterValues(name);
        String[] escapseValues = new String[values.length];
        if (values != null){
            for (int i=0; i<values.length;i++){
                escapseValues[i] = HtmlUtils.htmlEscape(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }
}
