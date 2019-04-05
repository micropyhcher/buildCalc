package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
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

        modelAndView.addObject(Constanta.userFrom_editForm,new User());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView editUserDo (@Valid @ModelAttribute(Constanta.userFrom_editForm) User userFromEditForm,
                                    BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest request){

        System.out.println("--- 1 ---");


        modelAndView.setViewName("userEdit");

//		=================================== ВАЛИДАЦИЯ =============================================

        List<String> errorsList = new ArrayList<>();

        ControllerService bindingResultUtil = new ControllerService();
        errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
        modelAndView.addObject(Constanta.errorsList, errorsList);

        System.out.println("--- 2 ---");

//		================================= ПРОШЛИ ВАЛИДАЦИЮ =========================================

        User userFromSession = (User) request.getSession().getAttribute(Constanta.userFromSession);
//        System.out.println(userFromSession);
//        System.out.println(userFromEditForm);
        Long userFromSessionId = userFromSession.getId();
        boolean isUserEdit = userService.editUser(userFromSession,userFromEditForm);

        if (isUserEdit){

            System.out.println("--- 3 ---");

            User newUser = userService.getUserById(new User(),userFromSessionId);
            request.getSession().setAttribute(Constanta.userFromSession, newUser);
            request.getSession().setAttribute(Constanta.userFromSession_role, newUser.getRole().getUserRolesEntity());

            System.out.println("--- 4 ---");

            modelAndView.addObject("redirect:/");
        }else{

            System.out.println("--- 5 ---");

            errorsList.add("Edit error");
            modelAndView.addObject(Constanta.errorsList,errorsList);

            System.out.println("--- 6 ---");

        }

        System.out.println("--- 7 ---");

        return modelAndView;
    }


}
