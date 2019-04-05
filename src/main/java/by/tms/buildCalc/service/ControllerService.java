package by.tms.buildCalc.service;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class ControllerService {

    public List<String> bindingResultErrorList(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> error = bindingResult.getFieldErrors();

            for (int i = 0; i < error.size(); i++) {
                errorList.add(error.get(i).getDefaultMessage());
            }

            return errorList;
        }else{
            return new ArrayList<>();
        }
    }

//    public List<ArrayList> userSaveService(UserService userService, User userFromRegForm, ModelAndView modelAndView, List<String> errorsList){
//        List<Object> returnedList = new ArrayList<>();
//        boolean isUserSaved = userService.saveUser(userFromRegForm); // записывыаем пользователя в БД включая роль
//        if (isUserSaved == true) { // если сохранение прошло успешно (вернулось true)
//            modelAndView.setViewName("redirect:/");
//        }
//        else{  // если сохранение не удалось (вернулось false), значит пользователь с введенными данными уже существует
//            modelAndView.setViewName("userRegisterLobby");
//            errorsList.add("Пользователь с таким E-Mail уже зарегистрирован в сиситеме");
//            modelAndView.addObject(Constanta.errorsList, errorsList);
//        }
//        returnedList.add(modelAndView);
//        returnedList.add(errorsList);
//        return returnedList;
//    }

}
