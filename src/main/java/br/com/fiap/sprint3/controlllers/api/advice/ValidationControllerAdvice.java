package br.com.fiap.sprint3.controlllers.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidacaoCampoError> handle(MethodArgumentNotValidException e) {
        List<ValidacaoCampoError> list = new ArrayList<>();


        List<FieldError> errors = e.getBindingResult().getFieldErrors();


        return list;
    }
}
