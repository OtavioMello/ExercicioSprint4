package br.compasso.politicalParty.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<AssociateFormErrorDTO> handler(MethodArgumentNotValidException methodArgumentNotValidException){

        List<AssociateFormErrorDTO> formErrorDTO = new ArrayList<>();

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error ->{
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            AssociateFormErrorDTO associateFormErrorDTO = new AssociateFormErrorDTO(error.getField(), message);
            formErrorDTO.add(associateFormErrorDTO);
        });
        return formErrorDTO;
    }
}
