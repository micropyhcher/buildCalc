package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import by.tms.buildCalc.service.ControllerService;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static by.tms.buildCalc.entity.Constanta.*;

@Controller
@RequestMapping(path = "/logform")
public class UserLoginController {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView userLoginLobby(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("userLogin");

		modelAndView.addObject(USER_FROM_LOG_FORM, new User());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView userLoginDo(@Valid @ModelAttribute(name = USER_FROM_LOG_FORM) User userFromLoginForm, BindingResult bindingResult,
									ModelAndView modelAndView, HttpServletRequest request) {

		modelAndView.setViewName("userLogin");

//		============================= ВАЛИДАЦИЯ ===============================
		List<String> errorsList = new ArrayList<>();

		ControllerService bindingResultUtil = new ControllerService();
		errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
		modelAndView.addObject(ERRORS_LIST, errorsList);


//		========================== ПРОШЛИ ВАЛИДАЦИЮ ===========================


		User userFromDB = userService.getUser(userFromLoginForm);
		if(userFromDB.getName() == null){
			modelAndView.setViewName("userLogin");
			errorsList.add("Email или пароль не верны");
			modelAndView.addObject(ERRORS_LIST, errorsList);
		}else{ // todo реализовать логин админовской записи через роли из БД

//				========================== определяем роль пользователя в сессии ===========================
			UserRoles userRole = null;
			for (UserRoles userRoleFromEnum : UserRoles.values()) {
				if (userFromDB.getRole().getUserRolesEntity().equals(userRoleFromEnum)){
					userRole = userRoleFromEnum;
					break;
				}else {
					userRole = UserRoles.GUEST;
				}
			}
//				=========================== сетим полученную роль в сессию ==================================
			request.getSession().setAttribute(USER_FROM_SESSION_ROLE,userRole);
			request.getSession().setAttribute(USER_FROM_SESSION, userFromDB);
			modelAndView.setViewName("redirect:/");

		}
		return modelAndView;
	}
}
