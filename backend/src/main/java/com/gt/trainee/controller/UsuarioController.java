package com.gt.trainee.controller;

import com.gt.trainee.dto.PessoaDTO;
import com.gt.trainee.dto.UsuarioDTO;
import com.gt.trainee.models.Usuario;
import com.gt.trainee.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insertUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.insertUsuario(usuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> updatePessoa(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.updatePessoa(id, usuarioDTO);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
