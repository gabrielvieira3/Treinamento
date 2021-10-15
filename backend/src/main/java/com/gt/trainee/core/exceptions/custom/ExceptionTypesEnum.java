package com.gt.trainee.core.exceptions.custom;

import lombok.Getter;

@Getter
public enum ExceptionTypesEnum {

    BASE_EXCEPTION("Erro interno!"),
    VALIDATION_EXCEPTION("Erro de validação!"),
    VERSAO_EXCEPTION("Versão desatualizada!"),
    ATENCAO("Atenção"),
    UNAUTHORIZED_EXCEPTION("Erro de autenticação");

    private final String descricao;

    ExceptionTypesEnum(String descricao) {
        this.descricao = descricao;
    }
}