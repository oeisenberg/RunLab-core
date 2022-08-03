package runlab.wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import runlab.models.codeModel;
import runlab.models.exceptions.InvalidRequest;
import runlab.models.mongoDB.APIDetails;
import runlab.models.mongoDB.APIDetails.API_type;
import runlab.models.strava.AthleteStatistics;
import runlab.models.strava.SummaryActivity;
import runlab.utility.jsonUtil;

public class StravaAPI implements API {

    private String url = "https://www.strava.com/";
    private OkHttpClient client = new OkHttpClient();

    private Integer client_id;
    private String client_secret;
    // private String access_code;
    // private String access_token; // user's
    // private String refresh_token; // user's

    public StravaAPI() {
        readKeys();
    }

    private boolean readKeys() {
        File file = new File("W:\\Dropbox\\Programming\\runlab\\backend\\runlab\\keys.json");
        try {
            JsonObject object = (JsonObject) JsonParser.parseReader(new FileReader(file));
            Map<String, Object> attributes = jsonUtil.toMap(object);

            this.client_id = jsonUtil.toInt(attributes, "client_id");
            this.client_secret = jsonUtil.toString(attributes, "client_secret");

            return true;
        } catch (FileNotFoundException fnfE) {
            System.err.println("File read failed");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        String access_code = requestBody.getCode();

        Response r = makeOauthRequest("token?client_id=" + this.client_id + "&client_secret="
                + this.client_secret + "&code=" + access_code + "&grant_type=authorization_code");
        String responceBody = r.body().string();
        Map<String, Object> attributes = jsonUtil.toMap((JsonObject) JsonParser.parseString(responceBody));

        if (!r.isSuccessful()) {throw new InvalidRequest(responceBody);}

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