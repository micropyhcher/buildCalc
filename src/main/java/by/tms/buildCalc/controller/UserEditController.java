package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.service.ControllerService;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.buildCalc.entity.Constanta.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/editform")
public class UserEditController {

    @Autowired
    private ImplUserService userService;

    @GetMapping
    public ModelAndView editUserLobby (ModelAndView modelAndView){

        modelAndView.setViewName("userEdit");

        modelAndView.addObject(USER_FROM_EDIT_FORM,new User());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView editUserDo (@Valid @ModelAttribute(USER_FROM_EDIT_FORM) User userFromEditForm,
                                    BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest request){

        modelAndView.setViewName("userEdit");

//		=================================== ВАЛИДАЦИЯ =============================================

        List<String> errorsList = new ArrayList<>();

        ControllerService bindingResultUtil = new ControllerService();
        errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
        modelAndView.addObject(ERRORS_LIST, errorsList);

//		================================= ПРОШЛИ ВАЛИДАЦИЮ =========================================

        User userFromSession = (User) request.getSession().getAttribute(USER_FROM_SESSION);
//        System.out.println(USER_FROM_SESSION);
//        System.out.println(userFromEditForm);
        Long userFromSessionId = userFromSession.getId();
        boolean isUserEdit = userService.editUser(userFromSession,userFromEditForm);

        if (isUserEdit){

            User newUser = userService.getUserById(new User(),userFromSessionId);
            request.getSession().setAttribute(USER_FROM_SESSION, newUser);
            request.getSession().setAttribute(USER_FROM_SESSION_ROLE, newUser.getRole().getUserRolesEntity());

            modelAndView.addObject("redirect:/");
        }else{

            errorsList.add("Edit error");
            modelAndView.addObject(ERRORS_LIST,errorsList);

        }
        return modelAndView;
    }


}
