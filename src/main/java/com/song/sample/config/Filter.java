package com.song.sample.config;

import com.song.sample.filter.TransactionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Filter {
    public FilterRegistrationBean<TransactionFilter> loggingFilter() {

        FilterRegistrationBean<TransactionFilter> transactionLoggingFilternBean = new FilterRegistrationBean<>();
        transactionLoggingFilternBean.setFilter(new TransactionFilter());
        transactionLoggingFilternBean.addUrlPatterns("/*");

        return transactionLoggingFilternBean;

    }
}
