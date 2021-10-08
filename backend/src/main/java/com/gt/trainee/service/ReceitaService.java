package com.gt.trainee.service;

import com.gt.trainee.dto.ReceitaDTO;
import com.gt.trainee.models.Ingrediente;
import com.gt.trainee.models.Receita;
import com.gt.trainee.models.Usuario;
import com.gt.trainee.repositories.IngredienteRepository;
import com.gt.trainee.repositories.ReceitaRepository;
import com.gt.trainee.repositories.UsuarioRepository;
import com.gt.trainee.service.exceptions.DatabaseException;
import com.gt.trainee.service.exceptions.ResourceNotFoundException;
import com.gt.trainee.specification.ReceitaSpecification;
import com.gt.trainee.specification.filter.ReceitaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReceitaSpecification receitaSpecification;

    @Transactional(readOnly = true)
    public Page<ReceitaDTO> findAllPaged(PageRequest pageRequest){
        Page<Receita> page = receitaRepository.findAll(pageRequest);
        return page.map(x -> new ReceitaDTO(x));
    }

    @Transactional(readOnly = true) //transação só para leitura tornando a transação mais performática
    public ResponseEntity<Map<String, Object>> findAllByName(String nome, int page, int size, String[] sort, Usuario usuario) {

        try {
            List<Sort.Order> orders = new ArrayList<>();

            if (sort[0].contains(",")) {
                // tem mais de dois campos
                // sortOrder="campo, direção"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[campo, direção]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Receita> receitas = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size, Sort.by(orders));

            Page<Receita> pageReceitas;

            if (nome == null) {
                pageReceitas = this.receitaRepository.findAll(paging);
            } else {
                pageReceitas = this.receitaRepository.findByNomeContaining(nome, paging);
            }

            receitas = pageReceitas.getContent();

            if (receitas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();

            Usuario u = usuarioRepository.getById(usuario.getId());

            Set<Receita> r = u.getPessoa().getReceita();

            for(Receita x : receitas){
                x.setFavorita(r.contains(x));
            }

            response.put("receitas", receitas);
            response.put("currentPage", pageReceitas.getNumber());
            response.put("totalItems", pageReceitas.getTotalElements());
            response.put("totalPages", pageReceitas.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Sort.Direction getSortDirection(String direcao){
        return direcao.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @Transactional
    public ReceitaDTO findById(Long id) {
        Optional<Receita> obj = receitaRepository.findById(id);
        Receita receita = obj.orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada"));
        return new ReceitaDTO(receita);
    }

    @Transactional
    public ReceitaDTO insertReceita(ReceitaDTO receitaDTO) {
        Receita receita = new Receita();
        receita.setNome(receitaDTO.getNome());
        receita.setDescricao(receitaDTO.getDescricao());
        receita.setTempoDePreparo(receitaDTO.getTempoDePreparo());
        receita.setRendimento(receitaDTO.getRendimento());
        receita.setFavorita(receitaDTO.getFavorita());

        receita = receitaRepository.save(receita);
        return new ReceitaDTO(receita);
    }

    @Transactional
    public ReceitaDTO updateReceita(Long id, ReceitaDTO receitaDTO) {
        try {
            Receita receita = new Receita();
            receita.setNome(receitaDTO.getNome());
            receita.setDescricao(receitaDTO.getDescricao());
            receita.setTempoDePreparo(receitaDTO.getTempoDePreparo());
            receita.setRendimento(receitaDTO.getRendimento());
            receita.setFavorita(receitaDTO.getFavorita());
            receita = receitaRepository.save(receita);
            return new ReceitaDTO(receita);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        }
    }

    public void deleteById(Long id) {
        try {
            receitaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    public List<Receita> findByIngredientes (ReceitaFilter filtro) {
        filtro.setSimplificado(false);

        ReceitaSpecification receitaSpecification = new ReceitaSpecification();

        Specification<Receita> build = receitaSpecification.build(filtro);

        return this.receitaRepository.findAll(build);
    }

    public List<Receita> findByCategorias (ReceitaFilter filtro) {
        filtro.setSimplificado(false);

        ReceitaSpecification receitaSpecification = new ReceitaSpecification();

        Specification<Receita> build = receitaSpecification.build(filtro);

        return this.receitaRepository.findAll(build);
    }


}
