package RunLab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import RunLab.Objects.*;

/**
 * App description
 *
 */
public class App {
    public static void main(String[] args) throws IOException {

        try {
            queryActivity(); // Activity activity = 
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        
    }

    private static void queryActivity() throws IOException, InterruptedException {
        String activity_id = "3470420931";
        String auth = "Bearer 1e23b0779bbce5326ee9bcd1904f3ad523f733a0";

        String uri = "https://www.strava.com/api/v3/activities/"+activity_id;

        Boolean stub = true;

        if (stub){
            File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\data\\asTxt\\GetActivity.txt"); 
  
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String json = br.readLine();
            br.close();

            // return new Activity(json);
        } else {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", auth)
                .build();

            HttpResponse<String> response;

            response = client.send(request, BodyHandlers.ofString());

            // return new Activity(response.body());
        }
    }

}
;