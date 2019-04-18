package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.buildCalc.entity.Constanta.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/cabinet")
public class UserCabinetController {

    @GetMapping
    public ModelAndView userCabinetLobby(ModelAndView modelAndView, HttpServletRequest request){

        modelAndView.setViewName("userCabinet");

        User userFromSession = (User) request.getSession().getAttribute(USER_FROM_SESSION);
        modelAndView.addObject(USER_ENTERED,userFromSession);
        modelAndView.addObject("userAllInfo",userFromSession);
        modelAndView.addObject("userRoleInfo",userFromSession.getRole());
        modelAndView.addObject("userUnitsConnectorInfo", userFromSession.getUnitsConnector());
        modelAndView.addObject("unitsInfo",userFromSession.getUnitsConnector().getUnit_1_list());

        return modelAndView;
    }

}
