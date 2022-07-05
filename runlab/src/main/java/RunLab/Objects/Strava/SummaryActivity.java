package RunLab.Objects.Strava;

// Parameters used for Gson JSON serialisation.
@SuppressWarnings("unused")
public class SummaryActivity {
    
    private MetaAthlete athlete_id;
    private String name;
    private float distance;
    private float moving_time;
    private float elapsed_time;
    private float total_elevation_gain;
    private String type; //ActivityType, https://developers.strava.com/docs/reference/#api-models-ActivityType
    private String sport_type;
    private long id;
    private String start_date;
    private String start_date_local;
    private String timezone;
    private float utc_offset;
    // private ? location_city;
    // private ? location_state;
    private String location_country;
    private int kudos_count;
    private Polyline map;
    private String gear_id;

    private float[] start_latlng; //LatLng,  https://developers.strava.com/docs/reference/#api-models-LatLng
    private float[] end_latlng;

    private float average_speed;
    private float max_speed;
    private float average_cadence;
    private boolean has_heartrate;
    private float elev_high;
    private float elev_low;
    private long upload_id;
    private String upload_id_str;
    private float suffer_score;

    // missing from generic call - maybe in detailed?
        // private float calories;
        // private float perceived_exertion;
        // private Split[] splits;
        // private Lap[] laps;
        // private float type;

}