package by.tms.buildCalc.validator;

public class ValidatorName {
    public String getName(String name){

        char[] nameCharArray = name.toCharArray();

        if (nameCharArray.length < 3){
            return "Введите значение более 3-х символов";
        }

        if (nameCharArray.length > 20){
            return "Вы ввели слишком длинное имя. Введите имя не более 20-ти символов";
        }

        for (int i = 0; i < nameCharArray.length; i++) {

        }

        return null;
    }
}
