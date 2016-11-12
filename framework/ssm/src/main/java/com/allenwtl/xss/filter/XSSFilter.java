package com.allenwtl.xss.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XSSFilter implements Filter{

    private FilterConfig config = null ;

    private static final String APOSTROPHE = "apostrophe";
    private static boolean no_init = true;
    private String apostrophe = "&#39;";
    private static final String CPR = "(c) Coldbeans mailto:info@servletsuite.com";
    private static final String VERSION = "ver. 1.3";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        no_init = false;
        String str = filterConfig.getInitParameter("apostrophe");
        if (str != null) {
            this.apostrophe = str.trim();
        }
        System.out.println("XSS filter (c) Coldbeans mailto:info@servletsuite.com ver. 1.3");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new RequestWrapper((HttpServletRequest)request, this.apostrophe), response);
    }

    @Override
    public void destroy() {
        this.config = null;
    }

    public void setFilterConfig(FilterConfig paramFilterConfig)
    {
        if (no_init)
        {
            no_init = false;
            this.config = paramFilterConfig;
            String str = paramFilterConfig.getInitParameter("apostrophe");
            if (str != null) {
                this.apostrophe = str.trim();
            }
            System.out.println("XSS filter (c) Coldbeans mailto:info@servletsuite.com ver. 1.3");
        }
    }

    public FilterConfig getConfig() {
        return this.config;
    }
}
