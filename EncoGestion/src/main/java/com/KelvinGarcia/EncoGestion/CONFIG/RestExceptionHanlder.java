package com.KelvinGarcia.EncoGestion.CONFIG;

import com.KelvinGarcia.EncoGestion.EXCEPTION.BadRequestException;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ContraseñaEnUsoException;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@AllArgsConstructor
@RestControllerAdvice
public class RestExceptionHanlder {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "La solicitud tiene unos errores de validacion.");
        Set<String> errors = new HashSet<>();
        List<FieldError> fieldErrors= ex.getFieldErrors();

        for (FieldError fe : fieldErrors) {
            String message = messageSource.getMessage(fe, Locale.getDefault());
            errors.add(message);
        }
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ContraseñaEnUsoException.class)
    public ProblemDetail handleContraseñaEnUsoException(ContraseñaEnUsoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
