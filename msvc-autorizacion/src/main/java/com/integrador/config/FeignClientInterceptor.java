package com.integrador.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String USER_HEADER = "X-User";
    private static final String ROLE_HEADER = "X-Role";

    @Override
    public void apply(RequestTemplate template) {
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(attributes -> (ServletRequestAttributes) attributes)
                .map(ServletRequestAttributes::getRequest)
                .ifPresent(request -> {
                    addHeader(template, request, AUTHORIZATION_HEADER);
                    addHeader(template, request, USER_HEADER);
                    addHeader(template, request, ROLE_HEADER);
                });
    }

    private void addHeader(RequestTemplate template, HttpServletRequest request, String headerName) {
        String headerValue = request.getHeader(headerName);
        if (headerValue != null) {
            template.header(headerName, headerValue);
        }
    }
}