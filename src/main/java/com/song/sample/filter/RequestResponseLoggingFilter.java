package com.song.sample.filter;

import com.song.sample.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        String uuid = UuidUtil.createUuid();

        HttpServletRequest req = (HttpServletRequest) request;
        LOG.info("Request UUID : {}, REQUEST_METHOD : {}, REQUEST_URI: {}, REQUEST_HEADER : {}",
                 uuid, req.getMethod(), req.getRequestURI(), getRequestHeaderInfo(req));

        HttpServletRequest requestToCache = new ContentCachingRequestWrapper(req);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse)response);

        chain.doFilter(requestToCache, responseWrapper);

        LOG.info("Response UUID : {}, STATUS_CODE : {}, RESPONSE : {}, RESPONSE_HEADER : {}",
                 uuid, responseWrapper.getStatusCode(), getResponseData(responseWrapper),
                 getResponseHeaderInfo(responseWrapper));
    }


    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }

    private String getRequestHeaderInfo(HttpServletRequest httpServletRequest) {

        StringBuffer headrInfo = new StringBuffer();

        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerContent = httpServletRequest.getHeader(headerName);
            headrInfo.append(headerName).append(": ").append(headerContent).append(" ");
        }

        return headrInfo.toString();
    }

    private String getResponseHeaderInfo(ContentCachingResponseWrapper contentCachingResponseWrapper) {

        StringBuffer headrInfo = new StringBuffer();
        for (String headerName : contentCachingResponseWrapper.getHeaderNames()) {
            headrInfo.append(headerName).append(": ").append(contentCachingResponseWrapper.getHeader(headerName)).append(" ");
        }
        return headrInfo.toString();
    }

    private static String getResponseData(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                wrapper.copyBodyToResponse();
            }
        }
        return payload;
    }
}
