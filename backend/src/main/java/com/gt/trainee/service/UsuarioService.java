package com.gt.trainee.service;

import com.gt.trainee.dto.UsuarioDTO;
import com.gt.trainee.models.Usuario;
import com.gt.trainee.repositories.UsuarioRepository;
import com.gt.trainee.service.exceptions.DatabaseException;
import com.gt.trainee.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        Usuario usuario = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrada"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO insertUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO updatePessoa(Long id, UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(usuarioDTO.getSenha());


            usuario = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        }
    }

    public void deleteById(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found! " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
