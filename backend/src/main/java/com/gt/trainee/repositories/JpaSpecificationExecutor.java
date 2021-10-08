package com.gt.trainee.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.List;

public interface JpaSpecificationExecutor<T> {
    List<T> findAll(@Nullable Specification<T> var1);
}
