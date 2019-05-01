package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.UnitsConnector;
import by.tms.buildCalc.entity.Role;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import by.tms.buildCalc.service.ControllerService;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RequestMapping(path = "/regstandart")
public class UserRegisterController_StandartUserReg {

	@Autowired
	private ImplUserService userService;

	@GetMapping
	public ModelAndView registerUserLobby (ModelAndView modelAndView){
		modelAndView.setViewName("userRegisterLobby");

//		List<String> rolesList = new ArrayList<>();
//		rolesList.add("USER");
//		rolesList.add("ADMIN");
//		modelAndView.addObject("rolesList",rolesList);

		modelAndView.addObject(STANDART_USER_ROLE, UserRoles.USER);
        modelAndView.addObject(ADMIN_USER_ROLE, UserRoles.ADMIN);
		modelAndView.addObject(USER_FROM_REG_FORM, new User());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView registerUserDo (@Valid @ModelAttribute(USER_FROM_REG_FORM) User userFromRegForm,
                                            BindingResult bindingResult, ModelAndView modelAndView,
                                            HttpServletRequest request) {

        modelAndView.setViewName("userRegisterLobby");

//		=================================== ВАЛИДАЦИЯ =============================================

        List<String> errorsList = new ArrayList<>();

//        ControllerService controllerService = new ControllerService(); // ============= НЕУДАЧНЫЙ МЕТОД ===========
//        errorsList = controllerService.bindingResultErrorList(bindingResult);

        if (bindingResult.hasErrors()){
            List<FieldError> error = bindingResult.getFieldErrors();

            for (int i = 0; i < error.size(); i++) {
                errorsList.add(error.get(i).getDefaultMessage());
            }
            modelAndView.addObject(ERRORS_LIST, errorsList);
        }

        else {

//		================================= ПРОШЛИ ВАЛИДАЦИЮ =========================================

//        -=-=-=-=-=-=-=-=-=-=-=-=- Создание коннектора юнитов -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

            String userForRegisterEmail = userFromRegForm.getEmail();
            UnitsConnector unitsConnectorInitializer = new UnitsConnector();
            unitsConnectorInitializer.setUserEmail(userForRegisterEmail);
            userFromRegForm.setUnitsConnector(unitsConnectorInitializer); // конеектору сетим email юзера с ID что бы и коннектор имел ID

//        -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

//		========================== определяем роль пользователя из формы ===========================

            Role userRoleForAdd = new Role(); // определяем роль пользователя из формы регистрации

            if (userFromRegForm.getUserRole() == null){
                userFromRegForm.setUserRole(UserRoles.USER);
            }

            for (UserRoles userRoleFromEnum : UserRoles.values()) {
                if (userFromRegForm.getUserRole().equals(userRoleFromEnum)) { // если в поле роли пользователя из формы есть роль и она совпадает с Enum то сетим ее
                    userRoleForAdd.setUserRolesEntity(userRoleFromEnum);
                    break;
                } else { // если поле роли пользователя из формы пустое или не совпало со списком Enum то сетим стандартного пользователя
                    userRoleForAdd.setUserRolesEntity(UserRoles.USER);
                }
            }

//            userRoleForAdd.setUserRolesEntity(UserRoles.GUEST); //===============

            userFromRegForm.setRole(userRoleForAdd); // ==== скорее всего на этом этапе ROLES получает ID / тут не так как с коннектором, тут юзеру сетим роль (уже с засеченным узером) (наоборот)====

//		========== сетим пользователя из формы в БД (если User, то тут, если ADMIN, по в админовской форме ===========

            if (userFromRegForm.getUserRole().equals(UserRoles.USER)) { // если роль пользователя - стандартный пользователь, то регистрируем стандартного пользователя

                boolean isUserSaved = userService.saveUser(userFromRegForm); // записывыаем пользователя в БД включая роль

                if (isUserSaved == true) { // если сохранение прошло успешно (вернулось true)
                    modelAndView.setViewName("redirect:/");
                } else {  // если сохранение не удалось (вернулось false), значит пользователь с введенными данными уже существует
                    modelAndView.setViewName("userRegisterLobby");
                    errorsList.add("Пользователь с таким E-Mail уже зарегистрирован в сиситеме");
                    modelAndView.addObject(ERRORS_LIST, errorsList);
                    modelAndView.addObject("redirect:/regstandart");
                }
            } else { // если роль пользователя из формы не если регистрируем администратора

                request.getSession().setAttribute("adminRegister", userFromRegForm);
                modelAndView.addObject(ERRORS_LIST, errorsList);
                modelAndView.setViewName("redirect:/regadmin");
            }
        }
        return modelAndView;
	}
}
