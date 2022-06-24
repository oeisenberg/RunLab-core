package RunLab;

import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RunLab.Exceptions.InvalidRequest;
import RunLab.Models.*;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

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
    public Response refresh() {
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
    public Response getAtheleteStatistics(@PathVariable(value = "atheleteID") String atheleteID) {
        try {
            HttpResponse<String> response = this.stravaWrapper.getAtheleteStats(atheleteID);
            if (response.statusCode() == 200){
                Response myResponse = new Success();
                myResponse.ingest(response);
                return myResponse;
            }
        } catch (InvalidRequest e) {
            return new Failure();
        }

        return new Failure();
    }

    @GetMapping("/oauth")
    public Response oauthGET() {
        // pull in and update data
        boolean dataUpdated = this.stravaWrapper.refreshAuthTokens();

        if (dataUpdated) {
            return new Success();
        } else {
            return new PullFailure();
        }
    }

    @PostMapping("/oauth")
    public Response oauthPOST(@RequestBody codeModel responceBody) {
        Boolean dataUpdated = this.stravaWrapper.setAuthTokens(responceBody);

        if (dataUpdated) {
            return new Success();
        } else {
            return new Failure();
        }
    }

}