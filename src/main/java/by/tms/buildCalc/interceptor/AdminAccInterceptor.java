package by.tms.buildCalc.interceptor;

import by.tms.buildCalc.enums.UserRoles;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.tms.buildCalc.entity.Constanta.*;

@Component
public class AdminAccInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    UserRoles userFromSession_Role = (UserRoles) request.getSession().getAttribute(USER_FROM_SESSION_ROLE);
//    System.out.println("AdminInterceptor_preHandle_1");

    if (userFromSession_Role != UserRoles.ADMIN){
//      System.out.println("AdminInterceptor_preHandle_2");
      response.sendRedirect("/");
      return false;
    }

//    System.out.println("AdminInterceptor_preHandle_3");
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    System.out.println("AdminInterceptor_postHandle_1");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    System.out.println("AdminInterceptor_afterCompletion_1");
  }
}
