package com.amm.filter;


import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by csw on 2016/8/11 17:40.
 * Explain:
 */
public class AMMRequestCsrfMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

//    private Pattern unAllowedMethods = Pattern.compile("^(DELETE|POST|PUT)$");

    /* (non-Javadoc)
     * @see org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.servlet.http.HttpServletRequest)
     */
    public boolean matches(HttpServletRequest request) {

//        String method = request.getMethod();
//        String basic = request.getHeader("Authorization");
//        String currentAuth = "Basic YWRtaW46YWRtaW4=";
//        if(unAllowedMethods.matcher(method).matches()) {
//            if(currentAuth != null) {
//                if(currentAuth.equals(basic)) {
//                    return true;
//                }
//            }
//        }
        if (excludeUrls != null && excludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : excludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }

        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    private List<String> excludeUrls;

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
