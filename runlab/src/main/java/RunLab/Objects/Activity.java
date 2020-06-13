package RunLab.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * class description
 *
 */
public class Activity {
    private Integer athlete_id;
    private float name;
    private double distance;
    private float moving_time;
    private float elapsed_time;
    private float total_elevation_gain;
    private float start_date;
    private float start_latlng; // create latlong object
    private float end_latlng;
    private float map; // create map object (id, ployline/summary polyline)
    private float average_speed;
    private float max_speed;
    private float has_heartrate;
    private float average_heartrate;
    private float max_heartrate;
    private float elev_high;
    private float elev_low;
    private float calories;
    private float perceived_exertion;
    private float splits; // custom object split
    private float laps;
    private float type;

    private static Map<String, Object> attributes;

    public Activity(String jsonAsString) {
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonAsString);
        
        attributes = new HashMap<String, Object>();
        Set<Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        for(Map.Entry<String,JsonElement> entry : entrySet){
          attributes.put(entry.getKey(), jsonObject.get(entry.getKey()));
        }
    }
    
    public String getMap(){
        JsonObject map = (JsonObject) attributes.get("map");

        String polyline = map.get("polyline").getAsString();
        
        return polyline; // Let JS decode polyline, Maps JavaScript API in google.maps.geometry.encoding namespace
    }

}