package RunLab.Objects;

import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import RunLab.Utility.JsonConverter;

/**
 * class description
 *
 */
public class Activity {
    private String[] vars = {"athlete_id", "name", "distance", "moving_time", "elapsed_time", "total_elevation_gain", "start_date",
                            "start_latlng", "end_latlng", "map", "average_speed", "max_speed", "has_heartrate", "average_heartrate", 
                            "max_heartrate", "elev_high", "elev_low", "calories", "perceived_exertion", "splits", "laps", "type"};
    private Integer athlete_id;
    private String name;
    private float distance;
    private float moving_time;
    private float elapsed_time;
    private float total_elevation_gain;
    private float start_date;
    private float start_latlng; // create latlong object
    private float end_latlng;
    private Route route; // create route object (id, ployline/summary polyline)
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
    
    public Activity(String jsonAsString) {
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonAsString);
        
        Map<String, Object> attributes = JsonConverter.toMap(jsonObject);
        
        // athlete_id = JsonConverter.toInt(attributes, vars[0]); // need to extract the second layer
        name = JsonConverter.toString(attributes, vars[1]);
        distance = JsonConverter.toFloat(attributes, vars[2]);
        moving_time =  JsonConverter.toFloat(attributes, vars[3]);
        elapsed_time =  JsonConverter.toFloat(attributes, vars[4]);
        total_elevation_gain =  JsonConverter.toFloat(attributes, vars[5]);
        // start_date =  JsonConverter.toFloat(attributes, vars[6]); // need to change to date time 
        // start_latlng =  JsonConverter.toFloat(attributes, vars[7]); // need to change to latlng
        // end_latlng =  JsonConverter.toFloat(attributes, vars[8]); // need to change to latlng
        route = JsonConverter.toRoute(attributes, vars[9]);
    }
    
    // public String getMap(){
    //     JsonObject map = (JsonObject) attributes.get("map");

    //     String polyline = map.get("polyline").getAsString();
        
    //     return polyline; // Let JS decode polyline, Maps JavaScript API in google.maps.geometry.encoding namespace
    // }

}