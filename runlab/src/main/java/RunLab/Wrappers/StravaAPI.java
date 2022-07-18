package RunLab.wrappers;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.mongoDB.APIDetails.API_type;
import RunLab.models.strava.AthleteStatistics;
import RunLab.models.strava.SummaryActivity;
import RunLab.utility.jsonUtil;

public class StravaAPI implements API {

    private String url = "https://www.strava.com/";
    private OkHttpClient client = new OkHttpClient();

    private Integer client_id;
    private String client_secret;
    private String access_code;
    // private String access_token; // user's
    // private String refresh_token; // user's

    public StravaAPI() {

    }

    public Response makeAPIRequest(String auth_token, String uri) throws InvalidRequest {
        Request request = new Request.Builder()
                .url(this.url + "api/v3/" + uri)
                .header("Authorization", "Bearer " + auth_token)
                .build();

        return makeRequest(request);
    }

    private Response makeOauthRequest(String uri) throws InvalidRequest {
        Request request = new Request.Builder()
                .post(RequestBody.create("", MediaType.parse("text/x-markdown; charset=utf-8")))
                .header("Content-Length", "0")
                .url(this.url + "oauth/" + uri)
                .build();

        return makeRequest(request);
    }

    private Response makeRequest(Request request) throws InvalidRequest {
        try {
            return this.client.newCall(request).execute();
        } catch (IOException ioE) {
            System.err.println("IOException during Strava request");
            throw new InvalidRequest("");
        }
    }

    public APIDetails createAPIDetails(codeModel requestBody) throws InvalidRequest, IOException {
        this.access_code = requestBody.getCode();

        Response r = makeOauthRequest("token?client_id=" + this.client_id + "&client_secret="
                + this.client_secret + "&code=" + this.access_code + "&grant_type=authorization_code");
        String responceBody = r.body().string();
        Map<String, Object> attributes = jsonUtil.toMap((JsonObject) JsonParser.parseString(responceBody));

        APIDetails api = new APIDetails.APIDetailsBuilder(API_type.STRAVA)
            .authenticationToken(jsonUtil.toString(attributes, "access_token"))
            .refreshToken(jsonUtil.toString(attributes, "refresh_token"))
            .expiresAt(jsonUtil.toUnix(attributes, "expires_at"))
            .expiresIn(jsonUtil.toUnix(attributes, "expires_in"))
            .Build();
        
        return api;
    }

    public APIDetails refreshAPIDetails(APIDetails apiDetails) {
        try {
            Response r = makeOauthRequest("token?client_id=" + this.client_id + "&client_secret="
                    + this.client_secret + "&grant_type=refresh_token&refresh_token=" + apiDetails.geRefreshToken());
            String responceBody = r.body().string();
            Map<String, Object> attributes = jsonUtil.toMap((JsonObject) JsonParser.parseString(responceBody));

            APIDetails api = new APIDetails.APIDetailsBuilder(API_type.STRAVA)
                .authenticationToken(jsonUtil.toString(attributes, "access_token"))
                .refreshToken(jsonUtil.toString(attributes, "refresh_token"))
                .expiresAt(jsonUtil.toUnix(attributes, "expires_at"))
                .expiresIn(jsonUtil.toUnix(attributes, "expires_in"))
                .Build();

            return api;
        } catch (Exception e) {
            e.printStackTrace();
            return apiDetails;
        }
    }

}