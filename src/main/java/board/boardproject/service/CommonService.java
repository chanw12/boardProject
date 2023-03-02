package board.boardproject.service;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class CommonService {
    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();
        System.out.println(errors);
        for (FieldError error: errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }
}
