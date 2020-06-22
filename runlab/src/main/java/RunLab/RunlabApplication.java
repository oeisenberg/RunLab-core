package RunLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RunLab.Models.*;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

@RestController
@SpringBootApplication

public class RunlabApplication {

    Strava stravaWrapper = new Strava();
    Mapbox mapboxWrapper = new Mapbox("walking");
    MongoDB dbWrappers = new MongoDB();

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

    @GetMapping("/oauth")
    public Responce oauthGET(){
        // pull in and update data
        boolean dataUpdated = this.stravaWrapper.refreshAuthTokens();

        if(dataUpdated){
            return new Success();
        } else {
            return new PullFailure();
        }
    }

    @PostMapping("/oauth")
    public Responce oauthPOST(@RequestBody codeModel responceBody){
        Boolean dataUpdated = this.stravaWrapper.setAuthTokens(responceBody);

        if(dataUpdated){
            return new Success();
        } else {
            return new Failure();
        }
    }
    
}