package ie.atu.week5lab3.controller.errorHandling;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionDetails> showErrorDetails(MethodArgumentNotValidException mae) {

        List<ExceptionDetails> errorList = new ArrayList<>();

        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()){

            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setFieldName(fieldError.getField());
            exceptionDetails.setFieldValue(fieldError.getDefaultMessage());
            errorList.add(exceptionDetails);
        }
        return errorList;
    }

}