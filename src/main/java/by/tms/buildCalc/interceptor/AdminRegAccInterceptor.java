//package by.tms.buildCalc.interceptor;
//
//import by.tms.buildCalc.entity.Constanta;
//import by.tms.buildCalc.enums.UserRoles;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class AdminRegAccInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        UserRoles userFromSession_Role = (UserRoles) request.getSession().getAttribute(Constanta.userFromSession_role);
////    System.out.println("AdminRegInterceptor_preHandle_1");
//
//        if (userFromSession_Role != UserRoles.ADMIN){
////      System.out.println("AdminRegInterceptor_preHandle_2");
//            response.sendRedirect("/");
//            return false;
//        }
//
////    System.out.println("AdminRegInterceptor_preHandle_3");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////    System.out.println("AdminRegInterceptor_postHandle_1");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
////    System.out.println("AdminRegInterceptor_afterCompletion_1");
//    }
//
//}
