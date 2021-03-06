package RunLab.Objects;

import java.util.Map;

import RunLab.Utility.JsonConverter;

/**
 * class description
 *
 */
public class Split {
    private float distance;
    private float elapsed_time;
    private float elevation_difference;
    private float moving_time;
    private float split;
    private float average_speed;
    private float average_grade_adjusted_speed;
    private float average_heartrate;
    private float pace_zone;

    public Split(Map<String, Object> attributes) {
        this.distance = JsonConverter.toFloat(attributes, "distance");
        this.elapsed_time = JsonConverter.toFloat(attributes, "elapsed_time");
        this.elevation_difference = JsonConverter.toFloat(attributes, "elevation_difference");
        this.moving_time = JsonConverter.toFloat(attributes, "moving_time");
        this.split = JsonConverter.toFloat(attributes, "split");
        this.average_speed = JsonConverter.toFloat(attributes, "average_speed");
        this.average_grade_adjusted_speed = JsonConverter.toFloat(attributes, "average_grade_adjusted_speed");
        this.average_heartrate = JsonConverter.toFloat(attributes, "average_heartrate");
        this.pace_zone = JsonConverter.toFloat(attributes, "pace_zone");
	}
}