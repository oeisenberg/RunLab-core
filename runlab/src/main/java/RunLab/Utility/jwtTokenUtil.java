package RunLab.utility;

import com.google.gson.Gson;

import RunLab.models.JWTPayload;
import RunLab.models.mongoDB.User;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class jwtTokenUtil {

    private static Gson gson = new Gson();

    private static Boolean isTokenExpired(JWTPayload payload){
        return payload.getExpiraryDateTime().isLessThan(Instant.now().getEpochSecond()) ? true : false;
    }

    // TODO: Add a header and hash to the payload.
    public static String encodeToToken(User user) {
        JWTPayload token = new JWTPayload(user.getUserName(), user.getUserID());
        // token = header +
        String tokenAsString = gson.toJson(token);
        // append "." + <hash of token>
        return Base64.getEncoder().encodeToString(tokenAsString.getBytes());
    }

    // TODO: Remove header and hash to get the payload.
    public static JWTPayload decodeToPayload(String token) {
        String unicodeToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        return gson.fromJson(unicodeToken, JWTPayload.class);
    }

    public static Boolean validateToken(JWTPayload payload, User user) {
        if (user == null) {throw new NullPointerException("User not found");}
        return payload.getUserName().equals(user.getUserName()) && !isTokenExpired(payload) ? true : false;
    }
}
