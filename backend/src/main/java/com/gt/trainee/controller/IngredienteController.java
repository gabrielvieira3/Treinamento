package com.gt.trainee.controller;

import com.gt.trainee.dto.IngredienteDTO;
import com.gt.trainee.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<Page<IngredienteDTO>> findAllPaged (
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<IngredienteDTO> list = ingredienteService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable Long id) {
        IngredienteDTO ingredienteDTO = ingredienteService.findById(id);
        return ResponseEntity.ok().body(ingredienteDTO);
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> insertIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
        ingredienteDTO = ingredienteService.insertIngrediente(ingredienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ingredienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(ingredienteDTO);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<IngredienteDTO> updateIngrediente(@PathVariable Long id, @RequestBody IngredienteDTO ingredienteDTO) {
        ingredienteDTO = ingredienteService.updateIngrediente(id, ingredienteDTO);
        return ResponseEntity.ok().body(ingredienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<IngredienteDTO> deleteById(@PathVariable Long id) {
        ingredienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
