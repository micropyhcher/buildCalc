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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static by.tms.buildCalc.entity.Constanta.*;

@Controller
@RequestMapping(path = "/regadmin")
public class UserRegisterController_AdminReg {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView registerAdminLobby(ModelAndView modelAndView) {
		modelAndView.setViewName("userRegisterAddPass");

		modelAndView.addObject(USER_FROM_REG_FORM_ADMIN, new User());

		return modelAndView;
	}

	@PostMapping
	public ModelAndView registerAdminLobby(@Valid @ModelAttribute(USER_FROM_REG_FORM_ADMIN) User userWithAdminPass,
										   BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest request) {

		modelAndView.setViewName("userRegisterAddPass");

		User adminUser = (User) request.getSession().getAttribute("adminRegister");

//		=================================== ВАЛИДАЦИЯ =============================================

		List<String> errorsList = new ArrayList<>();

		ControllerService bindingResultUtil = new ControllerService();
		errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
		modelAndView.addObject(ERRORS_LIST, errorsList);

//		================================= ПРОШЛИ ВАЛИДАЦИЮ =========================================

		String adminPass = userWithAdminPass.getAltPass();

		if (!adminPass.isEmpty() && adminPass.equals("99009900")){

			boolean isUserSaved = userService.saveUser(adminUser); // записывыаем пользователя в БД включая роль

			if (isUserSaved == true) { // если сохранение прошло успешно (вернулось true)
				modelAndView.setViewName("redirect:/");
			}
			else{  // если сохранение не удалось (вернулось false), значит пользователь с введенными данными уже существует
				modelAndView.setViewName("userRegisterLobby");
				errorsList.add("Пользователь с таким E-Mail уже зарегистрирован в сиситеме");
				modelAndView.addObject(ERRORS_LIST, errorsList);
			}
		}

		else{
			errorsList.add("Неверный пароль");
			modelAndView.addObject(ERRORS_LIST, errorsList);
		}

		return modelAndView;
	}
}
