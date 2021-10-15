package com.gt.trainee.security;

import com.gt.trainee.core.Config;
import com.gt.trainee.core.exceptions.custom.UnauthorizedException;
import com.gt.trainee.security.entities.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Service
@NoArgsConstructor
public class TokenProvider {

    public String createToken (Authentication authentication) {
        UserPrincipal usuario = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject("1")
                .claim("id", usuario.getId())
                .claim("email", usuario.getEmail())
                .claim("senha", usuario.getSenha());

        return jwtBuilder
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, Config.secret)
                .compact();
    }

    public void validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(Config.secret).parseClaimsJws(authToken);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new UnauthorizedException();
        }
    }

}
