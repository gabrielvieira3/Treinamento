package com.gt.trainee.service;

import com.gt.trainee.models.ModoPreparo;
import com.gt.trainee.repositories.ModoPreparoRepository;
import com.gt.trainee.service.exceptions.DatabaseException;
import com.gt.trainee.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModoPreparoService {

    @Autowired
    private ModoPreparoRepository modoPreparoRepository;

    @Transactional(readOnly = true)
    public List<ModoPreparo> findAll() {
        return this.modoPreparoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ModoPreparo findById(Long id) {
        return this.modoPreparoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        try {
            modoPreparoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not Found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ModoPreparo save(ModoPreparo modoPreparo) {
        return modoPreparoRepository.save(modoPreparo);
    }

    public List<ModoPreparo> save(List<ModoPreparo> list) {
        return modoPreparoRepository.saveAll(list);
    }
}
