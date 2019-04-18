package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.tms.buildCalc.entity.Constanta.*;

@Controller
@RequestMapping(path = "/delform_currentuser")
public class UserDelete_current {

    @Autowired
    private ImplUserService userService;

    @GetMapping
    public ModelAndView userDeleteCurrentLobby(ModelAndView modelAndView, HttpServletRequest request){

        modelAndView.setViewName("userDelete_current");

        modelAndView.addObject(USER_FROM_DELETE_FORM, new User());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView userDeleteCurrentDo(@ModelAttribute(name = USER_FROM_DELETE_FORM) User userFromDeleteForm,
                                            ModelAndView modelAndView, HttpServletRequest request){

        modelAndView.setViewName("userDelete_current");

        List<String> errorsList = new ArrayList<>();

        User userFromSession = (User) request.getSession().getAttribute(USER_FROM_SESSION);
        boolean isPassRight;

        if (userFromDeleteForm.getPass().equals(userFromSession.getPass())){ // проверка на знание пароля от своего же аккаунта
            isPassRight = true; // если пароль введен првильно
        }else{
            isPassRight = false; // если пароль не правильный
        }

        if (isPassRight){ // если пароль был подтвержден, то удаляем пользователя
            boolean userDelete = userService.delUser(userFromSession); // вернет логическую переменную - был ли удален пользователь
            if (userDelete){ // если пользователь был удален, то сетим в сессию роль ГОСТЬ и редирект на главную станицу
                request.getSession().setAttribute(USER_FROM_SESSION_ROLE,UserRoles.GUEST);
                modelAndView.setViewName("redirect:/");
            }else{ // если при удалении возникли ошибки, то выводим сообщение и остаемся на странице + сообщение об ошибке
                errorsList.add("Пользователь" + userFromSession.getName() + "не был удален. Ошибка базы данных");
                modelAndView.addObject(ERRORS_LIST, errorsList);
            }
        }else{ // если пароль небыл подтвержден, то повтор ввода пароля
            errorsList.add("Пароль не верен. Повторите ввод");
            modelAndView.addObject(ERRORS_LIST, errorsList);
        }

        return modelAndView;
    }
}
