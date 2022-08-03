package runlab.models.strava;

// Parameters used for Gson JSON serialisation.
public class SummaryActivity {
    
    public MetaAthlete athlete_id;
    public String name;
    public float distance;
    public float moving_time;
    public float elapsed_time;
    public float total_elevation_gain;
    public String type; //ActivityType, https://developers.strava.com/docs/reference/#api-models-ActivityType
    public String sport_type;
    public long id;
    public String start_date;
    public String start_date_local;
    public String timezone;
    public float utc_offset;
    // public ? location_city;
    // public ? location_state;
    public String location_country;
    public int kudos_count;
    public Polyline map;
    public String gear_id;

    public float[] start_latlng; //LatLng,  https://developers.strava.com/docs/reference/#api-models-LatLng
    public float[] end_latlng;

    public float average_speed;
    public float max_speed;
    public float average_cadence;
    public boolean has_heartrate;
    public float elev_high;
    public float elev_low;
    public long upload_id;
    public String upload_id_str;
    public float suffer_score;

    // missing from generic call - maybe in detailed?
        // public float calories;
        // public float perceived_exertion;
        // public Split[] splits;
        // public Lap[] laps;
        // public float type;

}
