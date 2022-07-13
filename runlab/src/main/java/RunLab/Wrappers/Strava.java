package RunLab.Wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import RunLab.Models.KeysModel;
import RunLab.Models.codeModel;
import RunLab.Models.Exceptions.InvalidRequest;
import RunLab.Models.Strava.AthleteStatistics;
import RunLab.Models.Strava.SummaryActivity;
import RunLab.Utility.JsonConverter;


// import io.micrometer.core.instrument.config.validate.Validated.Invalid;

public class Strava {

    private String url = "https://www.strava.com/";
    private OkHttpClient client = new OkHttpClient();

    private Integer client_id;
    private String client_secret;
    private String access_code;
    private String access_token; // user's
    private String refresh_token; // user's

    public Strava() {

    }

    // Gets the last 30 activites
    public Response pull() throws InvalidRequest {
        // Could look into a builder design pattern for the urls with optional parameters
        // int before, int after, int page, int perPage
        // https://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295
        Response response = makeAPIRequest("athlete/activities?page=1&per_page=10");
        
        return response;
    }

    public Response getAthleteStats(String AthleteID) throws InvalidRequest { 
        Response response = makeAPIRequest("athletes/" + AthleteID + "/stats");
        
        return response;
    }

    public Response getAthleteProfile() throws InvalidRequest {
        Response response = makeAPIRequest("athlete/");
        
        return response;
    }

    private Response makeAPIRequest(String uri) throws InvalidRequest {
        if (this.access_code == null) {
            File file = new File("W:\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");
            JsonObject object;

            try {
                object = (JsonObject) JsonParser.parseReader(new FileReader(file));
                Map<String, Object> attributes = JsonConverter.toMap(object);
                // Map<String, Object> userAttributes = JsonConverter.toMap((JsonObject) attributes.get("User_Details"));
                this.access_token = JsonConverter.toString(attributes, "access_token");
                this.refresh_token = JsonConverter.toString(attributes, "refresh_token");
            } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {

            }
        }

        Request request = new Request.Builder()
            .url(this.url + "api/v3/" + uri)
            .header("Authorization", "Bearer " + this.access_token)
            .build();

        return makeRequest(request);
    }

    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    
    private Response makeOauthRequest(String uri) throws InvalidRequest {
        Request request = new Request.Builder()
            .post(RequestBody.create("", MEDIA_TYPE_MARKDOWN))
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

    public boolean readKeys() {
        File file = new File("W:\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");
        try {
            JsonObject object = (JsonObject) JsonParser.parseReader(new FileReader(file));
            Map<String, Object> attributes = JsonConverter.toMap(object);

            this.client_id = JsonConverter.toInt(attributes, "client_id");
            this.client_secret = JsonConverter.toString(attributes, "client_secret");
            
            return true;
        } catch (FileNotFoundException fnfE) {
            System.err.println("File read failed");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveKeys() {  	
        Gson gson = new Gson();
        KeysModel keys = new KeysModel(this.client_id, this.client_secret, this.access_code, this.access_token, this.refresh_token);
        String filepath = "W:\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json";
        try {
            FileWriter writer = new FileWriter(filepath);
            gson.toJson(keys, writer);
            writer.flush();
            writer.close();
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setAuthTokens(codeModel requestBody){
        this.access_code = requestBody.getCode();

        if (readKeys()){
            try {
                Response r = makeOauthRequest("token?client_id="+this.client_id+"&client_secret="+this.client_secret+"&code="+this.access_code+"&grant_type=authorization_code");
                String responceBody = r.body().string();
                Map<String, Object> attributes = JsonConverter.toMap((JsonObject) JsonParser.parseString(responceBody));

                this.access_token = JsonConverter.toString(attributes, "access_token");
                this.refresh_token = JsonConverter.toString(attributes, "refresh_token");

                // write these to file, save user access code and refresh token for future requests
                return saveKeys();
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean setRefreshToken() {
        File file = new File("W:\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");

        try {
            JsonObject object = (JsonObject) JsonParser.parseReader(new FileReader(file));
            Map<String, Object> attributes = JsonConverter.toMap(object);

            // this.client_id = JsonConverter.toInt(attributes, "client_id");
            // this.client_secret = JsonConverter.toString(attributes, "client_secret"); 
            this.refresh_token = JsonConverter.toString(attributes, "refresh_token");            
            
            return true;
        } catch (FileNotFoundException fnfE) {
            System.err.println("File read failed");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean refreshAuthTokens(){
        if (setRefreshToken()){
            try{
                Response r = makeOauthRequest("token?client_id="+this.client_id+"&client_secret="+this.client_secret+"&grant_type=refresh_token&refresh_token="+this.refresh_token);
                String responceBody = r.body().string();
                
                Map<String, Object> attributes = JsonConverter.toMap((JsonObject) JsonParser.parseString(responceBody));
                this.access_token = JsonConverter.toString(attributes, "access_token");
                this.refresh_token = JsonConverter.toString(attributes, "refresh_token");
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    // List Athlete Activities 
    public void getActivities(){

    }

    // Get Activity
    // public Activity getActivity(){
    //     JsonObject jsonObject = (JsonObject) JsonParser.parseString(rawActivity);
    //     Map<String, Object> attributes = JsonConverter.toMap(jsonObject);
    //     return new Activity(attributes);
    // }

    public void getProfile(){
        try {
            Response r = makeAPIRequest("athlete");
             if (r.code() == 200){
                String responceBody = r.body().string();
                Map<String, Object> attributes = JsonConverter.toMap((JsonObject) JsonParser.parseString(responceBody));
                // assign to profile obj
            } else {
                System.err.println("Error");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
      
        // return new Profile();
    }
}