package RunLab.Objects.Strava;


public class Lap {
    private Long id;
    private String name;
    private float distance;
    private float elapsed_time;
    private float split;
    private float max_speed;
    private float average_speed;
    private float max_heartrate;
    private float average_heartrate;
    private float pace_zone;

    public Long getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }
    
    public float getDistance(){
        return this.distance;
    }

    public float getElapsedTime(){
        return this.elapsed_time;
    }

    public float getSplit(){
        return this.split;
    }

    public float getMaxSpeed(){
        return this.max_speed;
    }

    public float getAverageSpeed(){
        return this.average_speed;
    }

    public float getMaxHeartrate(){
        return this.max_heartrate;
    }

    public float getAverageHeartrate(){
        return this.average_heartrate;
    }

    public float getPaceZone(){
        return this.pace_zone;
    }
}