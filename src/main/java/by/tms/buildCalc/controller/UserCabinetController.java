package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/cabinet")
public class UserCabinetController {

    @GetMapping
    public ModelAndView userCabinetLobby(ModelAndView modelAndView, HttpServletRequest request){

        modelAndView.setViewName("userCabinet");

        User userFromSession = (User) request.getSession().getAttribute(Constanta.userFromSession);
        modelAndView.addObject(Constanta.userEntered,userFromSession);
        modelAndView.addObject("userAllInfo",userFromSession.toStringMy());

        return modelAndView;
    }

//    @PostMapping // сюда даже не попадаем
//    public ModelAndView userCabinetDo(ModelAndView modelAndView, HttpServletRequest request){
//        modelAndView.setViewName("userCabinet");
//        User userFromSession = (User) request.getSession().getAttribute("userFromSession");
//        System.out.println("usercabinet - userFromSession" + userFromSession);
//        modelAndView.addObject("userEntered",userFromSession);
//        return modelAndView;
//    }

}
