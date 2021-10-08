package com.gt.trainee.service;

import com.gt.trainee.dto.PessoaDTO;
import com.gt.trainee.models.Pessoa;
import com.gt.trainee.repositories.PessoaRepository;
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
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAllPaged(PageRequest pageRequest){
        Page<Pessoa> page = pessoaRepository.findAll(pageRequest);
        return page.map(x -> new PessoaDTO(x));
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        Pessoa pessoa = obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        return new PessoaDTO(pessoa);
    }

    @Transactional
    public PessoaDTO insertPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setName(pessoaDTO.getName());
        pessoa.setSobreMim(pessoaDTO.getSobreMim());
        pessoa.setComidaFavorita(pessoaDTO.getComidaFavorita());
        pessoa.setAge(pessoaDTO.getAge());

        pessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoa);
    }

    @Transactional
    public PessoaDTO updatePessoa(Long id, PessoaDTO pessoaDTO) {
        try {
            Pessoa pessoa = pessoaRepository.getById(id);
            pessoa.setName(pessoaDTO.getName());
            pessoa.setSobreMim(pessoaDTO.getSobreMim());
            pessoa.setComidaFavorita(pessoaDTO.getComidaFavorita());
            pessoa.setAge(pessoaDTO.getAge());
            pessoa = pessoaRepository.save(pessoa);
            return new PessoaDTO(pessoa);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        }
    }

    public void deleteById(Long id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
