package com.gt.trainee.core.exceptions.custom;

import com.gt.trainee.core.validator.GenericValidationResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidatorException extends BaseException {

    private List<GenericValidationResult> erros;

    public ValidatorException(String messageKey, List<GenericValidationResult> erros) {

        super(messageKey, ExceptionTypesEnum.VALIDATION_EXCEPTION);
        this.erros = erros;
    }
}
