package RunLab.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.math.BigInteger;

import com.google.gson.JsonObject;

import RunLab.models.strava.Lap;
import RunLab.models.strava.Route;
import RunLab.models.strava.Split;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonConverter {

    public JsonConverter(){
    }

    public static Map<String, Object> toMap(JsonObject jsonObject){
        Map<String, Object> map = new HashMap<String, Object>();

        Set<Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        for(Map.Entry<String,JsonElement> entry : entrySet){
          map.put((String) entry.getKey(), jsonObject.get(entry.getKey()));
        }
        return map;
    }

    public static int toInt(Map<String, Object> attributes, String param){
        return Integer.parseInt(attributes.get(param).toString());
    }

    public static BigInteger toBigInteger(Map<String, Object> attributes, String param){
        return new BigInteger(attributes.get(param).toString());
    }

    public static float toFloat(Map<String, Object> attributes, String param){
        return Float.parseFloat(attributes.get(param).toString());
    }

    public static double toDouble(Map<String, Object> attributes, String param){
        return Double.parseDouble(attributes.get(param).toString());
    }

    public static String toString(Map<String, Object> attributes, String param){
        // JSON responce contains '"' s that are unnecessary.
        String s = attributes.get(param).toString(); 
        return s.substring(1, s.length()-1);
    }

    public static Boolean toBoolean(Map<String, Object> attributes, String param){
        return Boolean.parseBoolean(attributes.get(param).toString());
    }

    public static Split[] toSplits(Map<String, Object> attributes, String param){
        JsonArray jsonArray = (JsonArray) attributes.get(param);
        Split[] splits = new Split[jsonArray.size()];
        for(int iSplit = 0; iSplit < jsonArray.size(); iSplit++ ){
            Map<String, Object> newMap = JsonConverter.toMap((JsonObject) jsonArray.get(iSplit));
            splits[iSplit] = new Split();
        }
        return splits;
    }

    public static Lap[] toLaps(Map<String, Object> attributes, String param){
        JsonArray jsonArray = (JsonArray) attributes.get(param);
        Lap[] laps = new Lap[jsonArray.size()];
        for(int iLap = 0; iLap < jsonArray.size(); iLap++ ){
            Map<String, Object> newMap = JsonConverter.toMap((JsonObject) jsonArray.get(iLap));
            // laps[iLap] = new Lap(newMap);
        }
        return laps;
    }

    public static Route toRoute(Map<String, Object> attributes, String param){
        JsonObject jsonObject = (JsonObject) attributes.get(param);
        Map<String, Object> newMap = JsonConverter.toMap(jsonObject);
        
        // Polyline s = new Polyline(JsonConverter.toString(newMap, "polyline"));
        // Polyline d = new Polyline(JsonConverter.toString(newMap, "summary_polyline"));
        return new Route();
    }
}