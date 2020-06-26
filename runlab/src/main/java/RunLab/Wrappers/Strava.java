package RunLab.Wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import RunLab.Exceptions.InvalidRequest;
import RunLab.Models.KeysModel;
import RunLab.Models.codeModel;
import RunLab.Objects.Activity;
import RunLab.Utility.JsonConverter;

// import io.micrometer.core.instrument.config.validate.Validated.Invalid;

public class Strava {

    private String url = "https://www.strava.com/";

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

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(rawActivity);
        Map<String, Object> attributes = JsonConverter.toMap(jsonObject);

        Activity activity = new Activity(attributes);
        // save activity

        return true;
    }

    /*
     * Mock function to imitate the Strava REST API call For now, reads the responce
     * - presaved as a string.
     */
    private String mockActivityStavaAPIRequest() {
        File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\data\\asTxt\\GetActivity.txt");

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

    private HttpResponse<String> makeAPIRequest(String uri) throws InvalidRequest {
        if (this.access_code == null) {
            File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");
            JsonObject object;

            try {
                object = (JsonObject) JsonParser.parseReader(new FileReader(file));
                Map<String, Object> attributes = JsonConverter.toMap(object);
                Map<String, Object> userAttributes = JsonConverter.toMap((JsonObject) attributes.get("User_Details"));
                this.access_token = JsonConverter.toString(userAttributes, "access_token");
                this.refresh_token = JsonConverter.toString(userAttributes, "refresh_token");
            } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {

            }
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.url + "api/v3/" + uri))
                .header("Authorization", "Bearer " + this.access_token).build();

        return makeRequest(request);
    }

    private HttpResponse<String> makeOauthRequest(String uri) throws InvalidRequest {
        HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(""))
                .uri(URI.create(this.url + "oauth/" + uri)).build();

        return makeRequest(request);
    }

    private HttpResponse<String> makeRequest(HttpRequest request) throws InvalidRequest {
        HttpClient client = HttpClient.newHttpClient();
        try {
            return client.send(request, BodyHandlers.ofString());
        } catch (IOException ioE) {
            System.err.println("IOException during Strava request");
            throw new InvalidRequest("");
        } catch (InterruptedException iE) {
            System.err.println("InterruptedException during Strava request");
            throw new InvalidRequest("");
        }
    }

    public boolean readKeys() {
        File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");

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
        String filepath = "C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json";
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
                HttpResponse<String> r = makeOauthRequest("token?client_id="+this.client_id+"&client_secret="+this.client_secret+"&code="+this.access_code+"&grant_type=authorization_code");
                String responceBody = r.body();
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
        File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\runlab\\keys.json");

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
                HttpResponse<String> r = makeOauthRequest("token?client_id="+this.client_id+"&client_secret="+this.client_secret+"&grant_type=refresh_token&refresh_token="+this.refresh_token);
                String responceBody = r.body();
                
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
            HttpResponse<String> r = makeAPIRequest("athlete");
             if (r.statusCode() == 200){
                String responceBody = r.body();
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