package com.gt.trainee.security.entities;

import com.gt.trainee.models.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private final Long id;

    private final String email;

    private final String senha;

    public UserPrincipal(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public UserPrincipal(Usuario usuario) {
       this.id = usuario.getId();
       this.email = usuario.getEmail();
       this.senha = usuario.getSenha();
    }

    public static UserPrincipal create(Usuario usuario){
        return new UserPrincipal(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
