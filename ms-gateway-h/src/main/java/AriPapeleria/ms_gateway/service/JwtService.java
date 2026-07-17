package AriPapeleria.ms_gateway.service;

import AriPapeleria.ms_gateway.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtProperties jwt;


    public JwtService(JwtProperties jwt) {

        this.jwt = jwt;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwt.secret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        Instant now = Instant.now();

        return Jwts.builder().subject(username).claim("roles", List.of("USER"))
                .issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(jwt.expirationMinutes())))
                .signWith(getSigningKey())
                .compact();
    }
    public Claims validateToken(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
                .getPayload();
    }


}
