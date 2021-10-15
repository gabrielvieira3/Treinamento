package com.gt.trainee.core.exceptions;

import com.gt.trainee.core.util.ValidationField;
import com.gt.trainee.helpers.StringHelpers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiException {

    private final int status;
    private final String message;
    private final List<String> erros;
    private final String messageTitle;
    private final String error;
    private final String date;
    private final String exception;
    private final List<ValidationField> errors;

    public ApiException(HttpStatus status, String message, String messageTitle, Exception exception) {

        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.messageTitle = messageTitle;
        this.date = StringHelpers.formatTimetable(new Date());
        this.exception = exception.getClass().getName();
        this.errors = null;
        this.erros = null;

    }

    public ApiException(HttpStatus status, String message, String messageTitle, List<String> erros, Exception exception) {

        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.messageTitle = messageTitle;
        this.date = StringHelpers.formatTimetable(new Date());
        this.exception = exception.getClass().getName();
        this.errors = null;
        this.erros = erros;

    }

    public ApiException(HttpStatus status, String message, String messageTitle, Exception exception, List<ValidationField> errors) {

        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.messageTitle = messageTitle;
        this.date = StringHelpers.formatTimetable(new Date());
        this.exception = exception.getClass().getName();
        this.errors = errors;
        this.erros = null;

    }

}
