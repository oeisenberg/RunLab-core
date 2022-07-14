package RunLab.Utility;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.stereotype.Component;

import RunLab.Models.JWTPayload;
import RunLab.Models.MongoDB.User;

@Component
public class jwtTokenUtil {

    private static Gson gson = new Gson();

    private static Boolean isTokenExpired(JWTPayload payload){
        return payload.getExpiraryDateTime().isBefore(LocalDateTime.now()) ? true : false;
    }

    public static String encodeToToken(User user) {
        JWTPayload token = new JWTPayload(user.getUserName(), user.getUserID());
        String tokenAsString = gson.toJson(token);
        return Base64.getEncoder().encodeToString(tokenAsString.getBytes());
    }

    public static JWTPayload decodeToPayload(String token) {
        String unicodeToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        return gson.fromJson(unicodeToken, JWTPayload.class);
    }

    public static Boolean validateToken(JWTPayload payload, User user) {
        return payload.getUserName().equals(user.getUserName()) && !isTokenExpired(payload) ? true : false;
    }
}
