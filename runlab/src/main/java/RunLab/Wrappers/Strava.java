package RunLab.Wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import RunLab.Exceptions.InvalidRequest;
import RunLab.Models.KeysModel;
import RunLab.Models.codeModel;
import RunLab.Objects.Strava.Activity;
import RunLab.Objects.Strava.AthleteStatistics;
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

    // get most recent and pull all activities from that to today
    // if fails due to no new data dataUpdated <- false
    public boolean pull() {
        String rawActivity = mockActivityStavaAPIRequest();

        // JsonObject jsonObject = (JsonObject) JsonParser.parseString(rawActivity);
        // Map<String, Object> attributes = JsonConverter.toMap(jsonObject);

        // Activity activity = new Activity(attributes);
        // save activity

        return true;
    }

    public Response getAtheleteStats(String atheleteID) throws InvalidRequest { 
        Response response = makeAPIRequest("athletes/" + atheleteID + "/stats");
        
        return response;
    }

    public Response getAtheleteProfile() throws InvalidRequest {
        Response response = makeAPIRequest("athlete/");
        
        return response;
    }

    /*
     * Mock function to imitate the Strava REST API call For now, reads the responce
     * - presaved as a string.
     */
    private String mockActivityStavaAPIRequest() {
        File file = new File("W:\\Dropbox\\Programming\\RunLab\\backend\\data\\asTxt\\GetActivity.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String data;
            data = br.readLine();
            br.close();
            return data;
        } catch (Exception e) {
            return "";
        }
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

    private Response makeOauthRequest(String uri) throws InvalidRequest {
        Request request = new Request.Builder()
            .post(null)
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

    public boolean setAuthTokens(codeModel responce){
        this.access_code = responce.getCode();

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