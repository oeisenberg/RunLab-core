package RunLab.Models.Strava;

public class ActivityTotal {
    private int count;
    private float distance;
    private int moving_time;
    private int elapsed_time;
    private float elevation_gain;
    private int achievement_count;

    public int getCount(){
        return this.count;
    }

    public float getDistance(){
        return this.distance;
    }
    
    public int getMovingTime(){
        return this.moving_time;
    }
    
    public int getElapsedTime(){
        return this.elapsed_time;
    }

    public float getElevationGain(){
        return this.elevation_gain;
    }

    public int getAchievementCount(){
        return this.achievement_count;
    }
}
