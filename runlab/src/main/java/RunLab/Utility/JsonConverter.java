package RunLab.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import RunLab.Objects.Route;
import RunLab.Objects.Polyline;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;

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

    public static Route toRoute(Map<String, Object> attributes, String param){
        JsonObject jsonObject = (JsonObject) attributes.get(param);
        Map<String, Object> newMap = JsonConverter.toMap(jsonObject);
        
        Polyline s = new Polyline(JsonConverter.toString(newMap, "polyline"));
        Polyline d = new Polyline(JsonConverter.toString(newMap, "summary_polyline"));
        return new Route(s, d);
    }
}