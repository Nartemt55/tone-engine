package ru.nartemt.tone_engine_ver2.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    public JwtAuthenticationDto getJwtAuthentication(String username) {
        return new JwtAuthenticationDto(
                generateJwtToken(username),
                generateRefreshToken(username)
        );
    }

    public JwtAuthenticationDto refresh(String username, String refreshToken) {
        return new JwtAuthenticationDto(
                generateJwtToken(username),
                refreshToken
        );
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return false;
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
