package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/userlist")
public class UserListController {

    @Autowired
    private ImplUserService userService;

    @GetMapping
    public ModelAndView userListLobby (ModelAndView modelAndView){

        modelAndView.setViewName("userList");

        List<User> userList = userService.getUserList();
        boolean isUserListEmpty = true;

        if (!userList.isEmpty()){
            isUserListEmpty = false;
        }

        modelAndView.addObject("isUserListEmpty",isUserListEmpty);
        modelAndView.addObject(Constanta.userList,userList);

        return modelAndView;
    }

// =========================================== rev1 =========================================================

//    @GetMapping
//    public ModelAndView userListLobby (ModelAndView modelAndView, HttpServletRequest request){
//        modelAndView.setViewName("userList");
//        UserRoles userFromSession_Role = (UserRoles) request.getSession().getAttribute("userFromSession_Role");
//        if (userFromSession_Role == UserRoles.ADMIN){
//            List<User> userList = userService.getUserList();
//            boolean isUserListEmpty = true;
//            if (!userList.isEmpty()){
//                isUserListEmpty = false;
//            }
//            modelAndView.addObject("isUserListEmpty",isUserListEmpty);
//            modelAndView.addObject("userList",userList);
//        }else{
//            modelAndView.setViewName("redirect:/");
//        }
//        return modelAndView;
//    }
}
