package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.Role;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/regadmin")
public class UserRegisterController_AdminReg {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView registerAdminLobby(ModelAndView modelAndView) {
		modelAndView.setViewName("userRegisterAddPass");

		modelAndView.addObject(Constanta.userFrom_regForm_admin, new User());

		return modelAndView;
	}

	@PostMapping
	public ModelAndView registerAdminLobby(@Valid @ModelAttribute(Constanta.userFrom_regForm_admin) User userWithAdminPass,
										   BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest request) {

		modelAndView.setViewName("userRegisterAddPass");

		User adminUser = (User) request.getSession().getAttribute("adminRegister");

//		=================================== ВАЛИДАЦИЯ =============================================

		List<String> errorsList = new ArrayList<>();

		ControllerService bindingResultUtil = new ControllerService();
		errorsList = bindingResultUtil.bindingResultErrorList(bindingResult);
		modelAndView.addObject(Constanta.errorsList, errorsList);

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
				modelAndView.addObject(Constanta.errorsList, errorsList);
			}
		}

		else{
			errorsList.add("Неверный пароль");
			modelAndView.addObject(Constanta.errorsList, errorsList);
		}

		return modelAndView;
	}
}
