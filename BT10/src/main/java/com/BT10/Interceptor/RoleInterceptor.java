package com.BT10.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userRole = (Integer) request.getSession().getAttribute("role");
        String uri = request.getRequestURI();
        if (uri.contains("/admin") && (userRole == null || userRole != 1)) {
            response.sendRedirect("/login");
            return false;
        }
        if (uri.contains("/user") && (userRole == null || userRole != 2)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
