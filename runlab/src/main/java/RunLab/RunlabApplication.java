package RunLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import RunLab.Responces.*;
import RunLab.Wrappers.*;

@RestController
@SpringBootApplication

public class RunlabApplication {

    Strava stravaWrapper = new Strava();
    Mapbox mapboxWrapper = new Mapbox("walking");

	public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/Runlab");
		SpringApplication.run(RunlabApplication.class, args);
	}

    @GetMapping("/refresh")
    public Responce refresh(){
        // pull in and update data
        boolean dataUpdated = this.stravaWrapper.pull();

        if(dataUpdated){
            return new Success();
        } else {
            return new PullFailure();
        }
    }
    
}