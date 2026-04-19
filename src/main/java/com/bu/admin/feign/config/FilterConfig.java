package com.bu.admin.feign.config;

import com.bu.admin.filter.HttpTraceIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HttpTraceIdFilter> loggingFilter() {
        FilterRegistrationBean<HttpTraceIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpTraceIdFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
