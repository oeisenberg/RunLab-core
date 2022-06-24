package RunLab;

import java.io.IOException;

import okhttp3.Response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RunLab.Exceptions.InvalidRequest;
import RunLab.Models.*;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@SpringBootApplication

public class RunlabApplication {

    Strava stravaWrapper = new Strava();
    Mapbox mapboxWrapper = new Mapbox("walking");
    // MongoDB dbWrappers = new MongoDB();

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/Runlab");
        SpringApplication.run(RunlabApplication.class, args);
    }

    @GetMapping("/refresh")
    public CustomResponse refresh() throws InvalidRequest {
        // pull in and update data
        boolean dataUpdated = this.stravaWrapper.pull();

        if (dataUpdated) {
            return new Success();
        } else {
            return new PullFailure();
        }
    }

    @GetMapping("/getAtheleteProfile")
    public void getAtheleteProfile() {
        // TODO: Query to get athelete information.
    }

    @GetMapping(value = { "/getAtheleteStatistics/{atheleteID}" })
    public Response getAtheleteStatistics(@PathVariable(value = "atheleteID") String atheleteID) throws InvalidRequest, IOException {
        Response response = this.stravaWrapper.getAtheleteStats(atheleteID);

        return response;        
    }

    @GetMapping("/oauth")
    public CustomResponse oauthGET() {
        // pull in and update data
        boolean dataUpdated = this.stravaWrapper.refreshAuthTokens();

        if (dataUpdated) {
            return new Success();
        } else {
            return new PullFailure();
        }
    }

    @PostMapping("/oauth")
    public CustomResponse oauthPOST(@RequestBody codeModel responceBody) {
        Boolean dataUpdated = this.stravaWrapper.setAuthTokens(responceBody);

        if (dataUpdated) {
            return new Success();
        } else {
            return new Failure();
        }
    }

}