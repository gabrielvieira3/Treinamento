package com.gt.trainee.controller;

import com.gt.trainee.dto.ReceitaDTO;
import com.gt.trainee.models.Receita;
import com.gt.trainee.models.Usuario;
import com.gt.trainee.service.ReceitaService;
import com.gt.trainee.specification.ReceitaSpecification;
import com.gt.trainee.specification.filter.ReceitaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<Page<ReceitaDTO>> findAllPaged (
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<ReceitaDTO> list = receitaService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
        ReceitaDTO receitaDTO = receitaService.findById(id);
        return ResponseEntity.ok().body(receitaDTO);
    }

    @PostMapping
    public ResponseEntity<ReceitaDTO> insertReceita(@RequestBody ReceitaDTO receitaDTO) {
        receitaDTO = receitaService.insertReceita(receitaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> updateReceita(@PathVariable Long id, @RequestBody ReceitaDTO receitaDTO) {
        receitaDTO = receitaService.updateReceita(id, receitaDTO);
        return ResponseEntity.ok().body(receitaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> deleteById(@PathVariable Long id) {
        receitaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/nome")
    public ResponseEntity<Map<String, Object>> findAllByName(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "nome,desc") String[] sort ,
            @AuthenticationPrincipal Usuario usuario) {
        return receitaService.findAllByName(nome, page, size, sort, usuario);
    }

    @GetMapping(value = "/ingredientes")
    public List<Receita> findByIngredientes(@RequestBody ReceitaFilter filtro){
        return receitaService.findByIngredientes(filtro);
    }

    @GetMapping(value = "/categorias")
    public List<Receita> findByCategorias(@RequestBody ReceitaFilter filtro){
        return receitaService.findByCategorias(filtro);
    }
}
