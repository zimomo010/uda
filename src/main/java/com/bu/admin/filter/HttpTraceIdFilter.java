package com.bu.admin.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.nio.charset.StandardCharsets;


@Slf4j
public class HttpTraceIdFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("http set trace id filter init");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            request.setCharacterEncoding(StandardCharsets.UTF_8.name());
            // Get trace Id from http header
            String traceId = request.getHeader(TRACE_ID);
            MDC.put(TRACE_ID, traceId);
            // Put trace id into MDC and set into response
            response.addHeader(traceId, MDC.get(TRACE_ID));
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        log.info("http trace id filter destroy......");
    }
}