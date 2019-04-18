package by.tms.buildCalc.filter;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.tms.buildCalc.entity.Constanta.*;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("userInitFilter_1");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        System.out.println("userDoFilter_1");
        if (httpServletRequest.getSession().isNew()) {
            httpServletRequest.getSession().setAttribute(USER_FROM_SESSION, new User());
            httpServletRequest.getSession().setAttribute(USER_FROM_SESSION_ROLE, UserRoles.GUEST);
//            System.out.println("userDoFilter_2");
        }
//        System.out.println("userDoFilter_3");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
//        System.out.println("userDestroyFilter_1");
    }
}

