package RunLab.Wrappers;

import java.io.File;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import RunLab.Objects.Activity;
import RunLab.Utility.JsonConverter;

public class Strava {
    public Strava() {
        
    }

    // get most recent and pull all activities from that to today
    // if fails due to no new data dataUpdated <- false
    public boolean pull(){
        String rawActivity = mockActivityStavaAPIRequest();
        
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(rawActivity);
        Map<String, Object> attributes = JsonConverter.toMap(jsonObject);

        Activity activity = new Activity(attributes);
        // save activity
        
        return true;
    }

    /*
    * Mock function to imitate the Strava REST API call
    * For now, reads the responce - presaved as a string.
    */
    private String mockActivityStavaAPIRequest(){
        File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\data\\asTxt\\GetActivity.txt");

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String data;
            data = br.readLine();
            br.close();
            return data;
        } catch (Exception e){
            return "";
        }
    }

}