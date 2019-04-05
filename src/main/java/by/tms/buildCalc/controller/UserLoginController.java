package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
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

@Controller
@RequestMapping(path = "/logform")
public class UserLoginController {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView userLoginLobby(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("userLogin");

		modelAndView.addObject(Constanta.userFrom_logForm, new User());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView userLoginDo(@Valid @ModelAttribute(name = Constanta.userFrom_logForm) User userFromLoginForm, BindingResult bindingResult,
									ModelAndView modelAndView, HttpServletRequest request) {

		modelAndView.setViewName("userLogin");

//		============================= ВАЛИДАЦИЯ ===============================
		List<String> errorsList = new ArrayList<>();

		ControllerService bindingResultUtil = new ControllerService();
		errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
		modelAndView.addObject(Constanta.errorsList, errorsList);


//		========================== ПРОШЛИ ВАЛИДАЦИЮ ===========================


		User userFromDB = userService.getUser(userFromLoginForm);
		if(userFromDB.getName() == null){
			modelAndView.setViewName("userLogin");
			errorsList.add("Email или пароль не верны");
			modelAndView.addObject(Constanta.errorsList, errorsList);
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
			request.getSession().setAttribute(Constanta.userFromSession_role,userRole);
			request.getSession().setAttribute(Constanta.userFromSession, userFromDB);
			modelAndView.setViewName("redirect:/");

		}
		return modelAndView;
	}
}
