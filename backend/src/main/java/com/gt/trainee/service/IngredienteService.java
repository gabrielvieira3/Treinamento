package com.gt.trainee.service;

import com.gt.trainee.dto.IngredienteDTO;
import com.gt.trainee.models.Ingrediente;
import com.gt.trainee.repositories.IngredienteRepository;
import com.gt.trainee.service.exceptions.DatabaseException;
import com.gt.trainee.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional(readOnly = true)
    public Page<IngredienteDTO> findAllPaged(PageRequest pageRequest) {
        Page<Ingrediente> page = ingredienteRepository.findAll(pageRequest);
        return page.map(x -> new IngredienteDTO(x));
    }

    @Transactional
    public IngredienteDTO findById(Long id) {
        Optional<Ingrediente> obj = ingredienteRepository.findById(id);
        Ingrediente ingrediente = obj.orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada"));
        return new IngredienteDTO(ingrediente);
    }

    @Transactional
    public IngredienteDTO insertIngrediente(IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(ingredienteDTO.getNome());
        ingrediente.setQuantidade(ingredienteDTO.getQuantidade());

        ingrediente = ingredienteRepository.save(ingrediente);
        return new IngredienteDTO(ingrediente);
    }

    @Transactional
    public IngredienteDTO updateIngrediente(Long id, IngredienteDTO ingredienteDTO) {
        try {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNome(ingredienteDTO.getNome());
            ingrediente.setQuantidade(ingredienteDTO.getQuantidade());

            ingrediente = ingredienteRepository.save(ingrediente);
            return new IngredienteDTO(ingrediente);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        }
    }

    public void deleteById(Long id) {
        try {
            ingredienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

}
