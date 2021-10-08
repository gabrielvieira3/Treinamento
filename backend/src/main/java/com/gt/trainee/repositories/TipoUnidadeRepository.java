package com.gt.trainee.repositories;

import com.gt.trainee.models.TipoUnidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUnidadeRepository extends JpaRepository<TipoUnidade, Long> {
}
