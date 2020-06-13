package RunLab.Utility;

import java.util.Map;

public class JsonConverter {

    public JsonConverter(){
    }

    public int toInt(Map<String, Object> attributes, String param){
        return Integer.parseInt(attributes.get(param).toString());
    }

    public float toFloat(Map<String, Object> attributes, String param){
        return Float.parseFloat(attributes.get(param).toString());
    }

    public double toDouble(Map<String, Object> attributes, String param){
        return Double.parseDouble(attributes.get(param).toString());
    }

    public String toString(Map<String, Object> attributes, String param){
        return attributes.get(param).toString();
    }
}