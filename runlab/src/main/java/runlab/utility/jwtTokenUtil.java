package runlab.utility;

import runlab.models.exceptions.CustomException;
import runlab.models.mongoDB.User;
import runlab.repositories.UserRepository;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;

@Component
// https://github.com/murraco/spring-boot-jwt/blob/master/src/main/java/murraco/security/JwtTokenProvider.java
public class jwtTokenUtil {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:86400000}") // 1D
    private long validityDurationInMilliseconds;

    @Autowired
    private UserRepository userRepository;

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000 * 24);
        return Jwts.builder()
            .serializeToJsonWith(new GsonSerializer(new Gson())) // getting an error with default serialiser.
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }

    public Authentication getAuthentication(String token) {
        User user = userRepository.findByUserName(getUsername(token));

        if (user != null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("USER")));
            return auth;
        }

        return null;
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
     }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    // https://github.com/jwtk/jjwt#jws-create-key
    public Boolean validateToken(String token) throws Exception {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
