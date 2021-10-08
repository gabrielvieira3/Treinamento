package com.gt.trainee.specification;

import com.gt.trainee.models.Receita;
import com.gt.trainee.specification.filter.ReceitaFilter;
import com.gt.trainee.util.SpecificationBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

@Component
public class ReceitaSpecification extends SpecificationBuilder<Receita, ReceitaFilter> {

    public void withIngredientes(ReceitaFilter filtro) {
        if (filtro.getIngredientes() != null)  {
            Predicate predicate = this.cb.and(root.join("ingredientes").get("nome").in(filtro.getIngredientes()));
            addPredicates(predicate);
        }
    }

    public void withCategorias(ReceitaFilter filtro) {
        if (filtro.getIngredientes() != null)  {
            Predicate predicate = this.cb.and(root.join("categorias").get("nome").in(filtro.getIngredientes()));
            addPredicates(predicate);
        }
    }

    @Override
    protected void loadPredicates(ReceitaFilter filtro) {
        withIngredientes(filtro);
        query.distinct(true);
    }
}
