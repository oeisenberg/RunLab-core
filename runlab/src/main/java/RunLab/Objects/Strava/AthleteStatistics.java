package RunLab.Objects.Strava;

public class AthleteStatistics {
    private float biggest_ride_distance;
    private float biggest_climb_elevation_gain;
    private ActivityTotal recent_ride_totals;
    private ActivityTotal recent_run_totals;
    private ActivityTotal recent_swim_totals;
    private ActivityTotal ytd_ride_totals;
    private ActivityTotal ytd_run_totals;
    private ActivityTotal ytd_swim_totals;
    private ActivityTotal all_ride_totals;
    private ActivityTotal all_run_totals;
    private ActivityTotal all_swim_totals;

    public float getBiggestRideDistance(){
        return this.biggest_ride_distance;
    }

    public float getBiggestClimbElevationGain(){
        return this.biggest_climb_elevation_gain;
    }

    public ActivityTotal getRecentRideTotals(){
        return this.recent_ride_totals;
    }

    public ActivityTotal getRecentRunTotals(){
        return this.recent_run_totals;
    }

    public ActivityTotal getRecentSwimTotals(){
        return this.recent_swim_totals;
    }

    public ActivityTotal getYTDRideTotals(){
        return this.ytd_ride_totals;
    }

    public ActivityTotal getYTDRunTotals(){
        return this.ytd_run_totals;
    }

    public ActivityTotal getYTDSwimTotals(){
        return this.ytd_swim_totals;
    }

    public ActivityTotal getAllRideTotals(){
        return this.all_ride_totals;
    }

    public ActivityTotal getAllRunTotals(){
        return this.all_run_totals;
    }

    public ActivityTotal getAllSwimTotals(){
        return this.all_swim_totals;
    }

}
