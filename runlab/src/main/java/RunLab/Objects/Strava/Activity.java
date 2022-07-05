package RunLab.Objects.Strava;

import java.time.ZonedDateTime;

import RunLab.Objects.Coord;
import RunLab.Objects.Route;
import RunLab.Objects.Split;

public class Activity {
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
    

    
}