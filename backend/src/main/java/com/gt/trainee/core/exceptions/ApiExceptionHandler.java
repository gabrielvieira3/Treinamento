package com.gt.trainee.core.exceptions;

import com.gt.trainee.core.exceptions.custom.BaseException;
import com.gt.trainee.core.exceptions.custom.UnauthorizedException;
import com.gt.trainee.core.exceptions.custom.ValidationException;
import com.gt.trainee.core.exceptions.custom.ValidatorException;
import com.gt.trainee.helpers.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleApiRequestException(Exception ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.get("erro.padrao"), Messages.get("title.erro.padrao"), ex),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> handleApiRequestException(BaseException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.NOT_IMPLEMENTED, ex.getUserMessage(), ex.getMessageTitle(), ex),
                HttpStatus.NOT_IMPLEMENTED
        );
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleApiRequestException(ValidationException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getUserMessage(), ex.getMessageTitle(), ex, ex.getFieldErrors()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {ValidatorException.class})
    protected ResponseEntity<Object> handleApiRequestException(ValidatorException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, null, ex.getMessageTitle(), ex.getErros().stream().map(c -> c.getMessage()).collect(Collectors.toList()), ex),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    protected ResponseEntity<Object> handleApiRequestException(UnauthorizedException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNAUTHORIZED, ex.getUserMessage(), ex.getMessageTitle(), ex),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<Object> handleApiRequestException(BadCredentialsException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.FORBIDDEN, "Usuário e/ou senha inválidos.", "Falha na autenticação!", ex),
                HttpStatus.FORBIDDEN
        );
    }

}
