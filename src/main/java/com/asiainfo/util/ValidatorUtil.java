package com.asiainfo.utils;

import org.springframework.ui.ModelMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static  <T> ModelMap validate(T obj){
        HTTPResponse resp = new HTTPResponse();
        if(obj == null) return resp.getResponseError("参数错误");
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if(set != null && set.size() > 0){
            for(ConstraintViolation<T> cv :set){
                String message = cv.getMessage();
                return resp.getResponseError(message);
            }
        }
        return null;
    }
    public static <T> Boolean isValidate(T obj){
        if(obj == null) return false;
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if(set != null && set.size() > 0){
            return false;
        }
        return true;
    }

}
