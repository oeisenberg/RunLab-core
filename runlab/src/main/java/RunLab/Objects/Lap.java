package RunLab.Objects;

import java.math.BigInteger;
import java.util.Map;

import RunLab.Utility.JsonConverter;

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

    public Lap(Map<String,Object> attributes){
        this.id = JsonConverter.toBigInteger(attributes, "id");
        this.name = JsonConverter.toString(attributes, "name");
        this.distance = JsonConverter.toFloat(attributes, "distance");
        this.elapsed_time = JsonConverter.toFloat(attributes, "elapsed_time");
        this.split = JsonConverter.toFloat(attributes, "split");
        this.max_speed = JsonConverter.toFloat(attributes, "max_speed");
        this.average_speed = JsonConverter.toFloat(attributes, "average_speed");
        this.max_heartrate = JsonConverter.toFloat(attributes, "max_heartrate");
        this.average_heartrate = JsonConverter.toFloat(attributes, "average_heartrate");
        this.pace_zone = JsonConverter.toFloat(attributes, "pace_zone");
    }
}