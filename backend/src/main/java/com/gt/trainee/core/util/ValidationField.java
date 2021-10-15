package com.gt.trainee.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationField {
    private String message;
    private String field;
    private Object parameter;
}
