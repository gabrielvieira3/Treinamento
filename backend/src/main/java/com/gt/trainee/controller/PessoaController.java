package com.gt.trainee.controller;

import com.gt.trainee.dto.PessoaDTO;
import com.gt.trainee.service.PessoaService;
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
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAllPaged (
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
            ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<PessoaDTO> list = pessoaService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> insertPessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaDTO = pessoaService.insertPessoa(pessoaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDTO);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        pessoaDTO = pessoaService.updatePessoa(id, pessoaDTO);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> deletePessoa(@PathVariable Long id) {
        pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
