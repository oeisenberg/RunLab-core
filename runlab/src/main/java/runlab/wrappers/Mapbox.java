package runlab.wrappers;

import runlab.models.exceptions.InvalidMapboxProfile;

// import com.mapbox.api.matching.v5.MapboxMapMatching;

// /**
//  * class description
//  *
//  */
public class Mapbox {
    private String access_token = "pk.eyJ1Ijoib2Vpc2VuYmVyZyIsImEiOiJja2FpNXdsOWIwZjJ3Mnl0aXdkOW02YmI1In0.sFJ73eVSAeGgu22t4EabPg";
    private String profile;

public Mapbox(String profile){
    try {
        this.setProfile(profile);
    } catch (InvalidMapboxProfile e){
        System.out.println("Profile selected incorrectly.");
    } 
}

private void setProfile(String input) throws InvalidMapboxProfile {
    if (input == "driving" || input == "walking" || input == "cycling" || input == "driving-traffic"){
        this.profile = input;
    } else {
        throw new InvalidMapboxProfile("Invalid Profile selected");
    }
}

//     public void snap_to_map(){
//         // https://docs.mapbox.com/api/navigation/#map-matching
//         // https://github.com/mapbox/mapbox-java
//         // https://docs.mapbox.com/android/java/overview/map-matching/
//         // https://docs.mapbox.com/android/java/examples/use-map-matching/#
//     }

}
