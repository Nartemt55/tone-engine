package ru.nartemt.tone_engine_ver2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("jwt.secret")
    private String jwtSecret;

    public JwtAuthenticationDto getJwtAuthentication(String username) {
        return new JwtAuthenticationDto(
                generateJwtToken(username),
                generateRefreshToken(username)
        );
    }

    private String generateJwtToken(String username) {
        Date expirationTime = Date.from(LocalDateTime.now()
                .plusMinutes(30)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return buildJwts(username, expirationTime);
    }

    private String generateRefreshToken(String username) {
        Date expirationTime = Date.from(LocalDateTime.now()
                .plusDays(1)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return buildJwts(username, expirationTime);
    }

    private String buildJwts(String username, Date expirationTime) {
        return Jwts.builder()
                .subject(username)
                .expiration(expirationTime)
                .signWith(getSingInKey())
                .compact();
    }

    private SecretKey getSingInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
