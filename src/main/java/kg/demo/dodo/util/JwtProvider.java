package kg.demo.dodo.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;


@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.authKey}")
    private String authKey;

    public String generateAccessToken(Long userId) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiration);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, authKey)
                .claim("userId", userId)
                .compact();
    }


    public Long validateToken(String token, int lang) {

        try {

            Claims claims = Jwts.parser().setSigningKey(authKey)
                    .parseClaimsJws(token).getBody();
            if (claims != null) {

                return Long.valueOf(String.valueOf(claims.get("userId")));

            } else {
                throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "emptyToken"));
            }
        } catch (ExpiredJwtException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "tokenExpired"));
        } catch (MalformedJwtException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "hackedToken"));
        } catch (ResponseStatusException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "emptyToken"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "tokenNotValidated"));
        }
    }

    public String getClaim(String token, int lang) {
        try {

            int i = token.lastIndexOf('.');
            String withoutSignature = token.substring(0, i + 1);

            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);

            Claims claims = untrusted.getBody();

            if (claims != null) {
                return String.valueOf(claims.get("claim"));
            } else {
                throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "emptyToken"));
            }
        } catch (ExpiredJwtException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "tokenExpired"));
        } catch (MalformedJwtException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "hackedToken"));
        } catch (ResponseStatusException e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "emptyToken"));
        } catch (Exception e) {
            throw new JwtException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "tokenNotValidated"));
        }
    }
}
