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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/regstandart")
public class UserRegisterController_StandartUserReg {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView registerUserLobby (ModelAndView modelAndView){
		modelAndView.setViewName("userRegisterLobby");
		modelAndView.addObject(Constanta.userRole_standart, UserRoles.USER);
        modelAndView.addObject(Constanta.userRole_admin, UserRoles.ADMIN);
		modelAndView.addObject(Constanta.userFrom_regForm, new User());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView registerUserDo (@Valid @ModelAttribute(Constanta.userFrom_regForm) User userFromRegForm,
                                            BindingResult bindingResult, ModelAndView modelAndView,
                                            HttpServletRequest request) {
//        System.out.println("UserRegisterControllerLobby_1" + userFromRegForm); //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

        modelAndView.setViewName("userRegisterLobby");

//		=================================== ВАЛИДАЦИЯ =============================================

        List<String> errorsList = new ArrayList<>();

        ControllerService controllerService = new ControllerService();
        errorsList = controllerService.bindingResultErrorList(bindingResult);
        modelAndView.addObject(Constanta.errorsList, errorsList);

//		================================= ПРОШЛИ ВАЛИДАЦИЮ =========================================

//		========================== определяем роль пользователя из формы ===========================

        Role userRoleForAdd = new Role(); // определяем роль пользователя из формы регистрации
        for (UserRoles userRoleFromEnum : UserRoles.values()) {
            if (userFromRegForm.getUserRole() != null && userFromRegForm.getUserRole().equals(userRoleFromEnum)) { // если в поле роли пользователя из формы есть роль и она совпадает с Enum то сетим ее
                userRoleForAdd.setUserRolesEntity(userRoleFromEnum);
                break;
            } else { // если поле роли пользователя из формы пустое или не совпало со списком Enum то сетим стандартного пользователя
                userRoleForAdd.setUserRolesEntity(UserRoles.USER);
            }
        }

        userFromRegForm.setRole(userRoleForAdd);

//		========== сетим пользователя из формы в БД (если User, то тут, если ADMIN, по в админовской форме ===========

        if (userFromRegForm.getRole().getUserRolesEntity().equals(UserRoles.USER)) { // если роль пользователя - стандартный пользователь, то регистрируем стандартного пользователя

            boolean isUserSaved = userService.saveUser(userFromRegForm); // записывыаем пользователя в БД включая роль

            if (isUserSaved == true) { // если сохранение прошло успешно (вернулось true)
                modelAndView.setViewName("redirect:/");
            }
            else{  // если сохранение не удалось (вернулось false), значит пользователь с введенными данными уже существует
                modelAndView.setViewName("userRegisterLobby");
                errorsList.add("Пользователь с таким E-Mail уже зарегистрирован в сиситеме");
                modelAndView.addObject(Constanta.errorsList, errorsList);
            }
        }

        else{ // если роль пользователя из формы не если регистрируем администратора

            request.getSession().setAttribute("adminRegister",userFromRegForm);

            modelAndView.setViewName("redirect:/regadmin");
        }

        return modelAndView;
	}


}
