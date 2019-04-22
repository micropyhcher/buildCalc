package by.tms.buildCalc.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorName {
    public List<String> getName(String nameString){

        List<String> valideteErrorsList = new ArrayList<>();


        char[] nameCharArray = nameString.toCharArray();

        if (nameCharArray.length < 3){
            valideteErrorsList.add("Введите значение более 3-х символов");
        }

        String[] blackListNames = {"admin", "user", "guest"};
        String[] wordsName;
        String separator = "-";
        wordsName = nameString.split(separator);
        for (int nch = 0; nch < nameCharArray.length; nch++) {
            for (int bl = 0; bl < blackListNames.length; bl++) {
                if (blackListNames[bl].equals(wordsName[nch])){
                    valideteErrorsList.add(wordsName[nch] + " - недопустимое имя");
                }
            }
        }

        if (nameCharArray.length > 20){
            valideteErrorsList.add("Вы ввели слишком длинное имя. Введите имя не более 20-ти символов");
        }

        return valideteErrorsList;
    }
}
