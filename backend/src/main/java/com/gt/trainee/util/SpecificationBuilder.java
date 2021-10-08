package com.gt.trainee.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificationBuilder<TEntity, Filter extends IFilter> {

    protected Root<TEntity> root;
    protected CriteriaQuery<?> query;
    protected CriteriaBuilder cb;

    protected Pagination pagination;

    protected List<Predicate> predicates;
    protected List<Predicate> predicatesOr;
    protected List<Predicate> simplificadoPredicates;
    protected List<Predicate> simplificadoPredicatesOr;

    public Specification<TEntity> build(Filter filter) {

        return (root, query, cb) -> {

            this.root = root;
            this.query = query;
            this.cb = cb;
            this.predicates = new ArrayList<>();
            this.simplificadoPredicates = new ArrayList<>();
            this.predicatesOr = new ArrayList<>();
            this.simplificadoPredicatesOr = new ArrayList<>();

            if (filter.getSimplificado() == null || filter.getSimplificado()) {
                loadPredicatesSimplificado(filter);
                return gerarSimplificadoPredicates();
            }

            loadPredicates(filter);
            return gerarPredicates();

        };

    }

    protected void loadPredicates(Filter filter) {

    }

    protected void loadPredicatesSimplificado(Filter filter) {

    }

    protected void addPredicates(Predicate predicate) {

        if (predicate == null) {

            return;
        }

        predicates = predicates != null ? predicates : new ArrayList<>();

        predicates.add(predicate);
    }

    protected void addPredicatesOr(Predicate predicate) {

        if (predicate == null) {

            return;
        }

        predicatesOr = predicatesOr != null ? predicatesOr : new ArrayList<>();

        predicatesOr.add(predicate);
    }

    protected void addSimplificadoPredicates(Predicate predicate) {

        if (predicate == null) {

            return;

        }

        simplificadoPredicates = simplificadoPredicates != null ? simplificadoPredicates : new ArrayList<>();

        simplificadoPredicates.add(predicate);
    }

    protected void addSimplificadoPredicatesOr(Predicate predicate) {

        if (predicate == null) {

            return;

        }

        simplificadoPredicatesOr = simplificadoPredicatesOr != null ? simplificadoPredicatesOr : new ArrayList<>();

        simplificadoPredicatesOr.add(predicate);
    }

    protected Predicate gerarPredicates() {

        Predicate[] predicatesArray = this.predicates.toArray(new Predicate[this.predicates.size()]);
        Predicate[] predicatesOrArray = this.predicatesOr.toArray(new Predicate[this.predicatesOr.size()]);

        return this.cb.or(this.cb.and(predicatesArray), this.cb.or(predicatesOrArray));

    }

    private Predicate gerarSimplificadoPredicates() {

        Predicate[] predicatesArray = this.simplificadoPredicates.toArray(new Predicate[this.simplificadoPredicates.size()]);
        Predicate[] predicatesOrArray = this.simplificadoPredicatesOr.toArray(new Predicate[this.simplificadoPredicatesOr.size()]);

        return this.cb.or(this.cb.and(predicatesArray), this.cb.or(predicatesOrArray));

    }

    protected String getNormalizedParam(String param) {

        return param == null ? null : '%' + (Normalizer.normalize(param.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[.&,\\-)(?']", "")) + '%';
    }

    protected Expression<String> getNormalizedEmpression(Path<Object> column) {

        return this.cb.lower(
                this.cb.function(
                        "replace",
                        String.class,
                        this.cb.function("unaccent", String.class, column),
                        this.cb.literal("_"), this.cb.literal(" ")
                )
        );
    }

    protected Expression<String> getOnlyDate(Path<Object> column) {

        return this.cb.function("TO_CHAR",
                String.class, column,
                cb.literal("yyyy-MM-dd"));

    }

}