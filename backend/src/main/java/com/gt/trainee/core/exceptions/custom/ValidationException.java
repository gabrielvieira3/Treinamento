package com.gt.trainee.core.exceptions.custom;

import com.gt.trainee.core.util.ValidationField;

import java.util.List;

public class ValidationException extends BaseException {

    private List<ValidationField> fieldErrors;

    public ValidationException() {

        super();

    }

    public ValidationException(String messageKey, Object... messageArgs) {

        super(messageKey, ExceptionTypesEnum.VALIDATION_EXCEPTION, messageArgs);

    }

    public ValidationException(String messageKey, Boolean erroVersao, Object... messageArgs) {

        super(messageKey, ExceptionTypesEnum.VERSAO_EXCEPTION, messageArgs);

    }

    public List<ValidationField> getFieldErrors() {

        return this.fieldErrors;

    }

    public ValidationException setFieldErrors(List<ValidationField> fieldErrors) {

        this.fieldErrors = fieldErrors;

        return this;

    }
}

