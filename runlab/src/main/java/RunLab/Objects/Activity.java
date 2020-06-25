package RunLab.Objects;

import java.util.Map;
import java.time.ZonedDateTime;

import RunLab.Utility.JsonConverter;

/**
 * class description
 *
 */
public class Activity {
    private String[] vars = {"athlete_id", "name", "distance", "moving_time", "elapsed_time", "total_elevation_gain", "start_date",
                            "start_latlng", "end_latlng", "map", "average_speed", "max_speed", "has_heartrate", "average_heartrate", 
                            "max_heartrate", "elev_high", "elev_low", "calories", "perceived_exertion", "splits_metric", "laps"};
    private Integer athlete_id;
    private String name;
    private float distance;
    private float moving_time;
    private float elapsed_time;
    private float total_elevation_gain;
    private ZonedDateTime start_date;
    private Coord start_latlng;
    private Coord end_latlng;
    private Route route;
    private float average_speed;
    private float max_speed;
    private boolean has_heartrate;
    private float average_heartrate;
    private float max_heartrate;
    private float elev_high;
    private float elev_low;
    private float calories;
    private float perceived_exertion;
    private Split[] splits;
    private Lap[] laps;
    private float type;
    
    public Activity(Map<String, Object> attributes) {        
        // athlete_id = JsonConverter.toInt(attributes, vars[0]); // need to extract the second layer
        name = JsonConverter.toString(attributes, vars[1]);
        distance = JsonConverter.toFloat(attributes, vars[2]);
        moving_time = JsonConverter.toFloat(attributes, vars[3]);
        elapsed_time = JsonConverter.toFloat(attributes, vars[4]);
        total_elevation_gain =  JsonConverter.toFloat(attributes, vars[5]);
        start_date =  JsonConverter.toDateTime(attributes, vars[6]);
        start_latlng = JsonConverter.toCoord(attributes, vars[7]); // need to change to latlng
        end_latlng = JsonConverter.toCoord(attributes, vars[8]); // need to change to latlng
        route = JsonConverter.toRoute(attributes, vars[9]);
        average_speed = JsonConverter.toFloat(attributes, vars[10]);
        max_speed = JsonConverter.toFloat(attributes, vars[11]);
        has_heartrate = JsonConverter.toBoolean(attributes, vars[12]);
        if (has_heartrate){
            average_heartrate = JsonConverter.toFloat(attributes, vars[13]);
            max_heartrate = JsonConverter.toFloat(attributes, vars[14]);
        }
        elev_high = JsonConverter.toFloat(attributes, vars[15]);
        elev_low = JsonConverter.toFloat(attributes, vars[16]);
        calories = JsonConverter.toFloat(attributes, vars[17]);
        perceived_exertion = JsonConverter.toFloat(attributes, vars[18]);
        splits = JsonConverter.toSplits(attributes, vars[19]);
        laps = JsonConverter.toLaps(attributes, vars[20]);
    }
    
}