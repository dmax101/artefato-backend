package br.inatel.icc.idp.artefato.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.error.ErrorDTO;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class, IllegalArgumentException.class,
            HttpMessageNotReadableException.class, UnexpectedTypeException.class, InvalidFormatException.class,
            ConverterNotFoundException.class, NullPointerException.class, HttpMessageConversionException.class })
    public ResponseEntity<?> handleValidationException(RuntimeException e) {

        String message = e.getMessage();

        return ResponseEntity.badRequest().body(new BasicMessageDTO(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error(HttpStatus.BAD_REQUEST.toString(), errors);

        return ResponseEntity.badRequest().body(new ErrorDTO(HttpStatus.BAD_REQUEST.toString(),
                "Alguns erros foram encontrados na requisição", Arrays.asList(errors)));
    }

    @ExceptionHandler(value = { ResourceAccessException.class, TransientDataAccessResourceException.class })
    public ResponseEntity<?> handleResourceException(RuntimeException e) {

        String message = e.getMessage();

        return ResponseEntity.internalServerError()
                .body(new BasicMessageDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), message));
    }

}
