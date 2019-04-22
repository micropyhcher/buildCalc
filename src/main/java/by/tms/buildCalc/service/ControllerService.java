package by.tms.buildCalc.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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

}
