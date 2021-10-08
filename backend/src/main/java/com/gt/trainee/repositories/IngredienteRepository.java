package com.gt.trainee.repositories;

import com.gt.trainee.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
