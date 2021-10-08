package com.gt.trainee.specification.filter;

import com.gt.trainee.models.Receita;
import com.gt.trainee.util.IFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;


import java.util.List;

@Getter
@Setter
public class ReceitaFilter implements IFilter {

    private Boolean simplificado = false;
    private String ordem;
    private List<String> ordemList;
    private Sort.Direction ordemDirecao;
    private Receita receita;
    private List<String> ingredientes;
    private List<String> categorias;
}
