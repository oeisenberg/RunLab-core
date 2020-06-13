package RunLab.Wrappers;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import RunLab.Objects.Activity;

public class Strava {
    public Strava() {
        
    }

    // get most recent and pull all activities from that to today
    // if fails due to no new data dataUpdated <- false
    public boolean pull(){
        String rawActivity = mockActivityStavaAPIRequest();
        Activity activity = new Activity(rawActivity);
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