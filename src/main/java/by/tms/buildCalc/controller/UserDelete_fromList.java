package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import by.tms.buildCalc.repository.DBUserRepository;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/deluser")
public class UserDelete_fromList {

    @Autowired
    private ImplUserService userService;

    @GetMapping(path = "/{id}")
    public ModelAndView userDeleteLobby(@PathVariable("id") Long idFromForm, ModelAndView modelAndView) {

        //todo !!!реализовать логику!!!

//        User userFromForm = new User();
//        userFromForm.setId(id);

        User userForDeleteById = userService.getUserById(new User(),idFromForm);
        userService.delUser(userForDeleteById);
        modelAndView.setViewName("redirect:/userlist");
        return modelAndView;
    }

//    @PostMapping
//    public ModelAndView
}
