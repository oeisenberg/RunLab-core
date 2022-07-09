package RunLab;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;

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
import RunLab.Objects.Strava.SummaryActivity;
import RunLab.Objects.Strava.AthleteProfile;
import RunLab.Objects.Strava.AthleteStatistics;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@SpringBootApplication

public class RunlabApplication {

    Gson gson = new Gson();
    Strava stravaWrapper = new Strava();
    Mapbox mapboxWrapper = new Mapbox("walking");
    // MongoDB dbWrappers = new MongoDB();

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/Runlab");
        SpringApplication.run(RunlabApplication.class, args);
    }

    @GetMapping("/refresh")
    public CustomResponse<SummaryActivity[]> refresh() throws InvalidRequest, IOException {
        Response response = this.stravaWrapper.pull();
        Success<SummaryActivity[]> r = new Success<SummaryActivity[]>();
        SummaryActivity[] activity = gson.fromJson(response.body().string().replace("\"", "'"), SummaryActivity[].class);
        
        r.setBody(activity);
        return r;
    }

    @GetMapping("/getAtheleteProfile")
    public CustomResponse<AthleteProfile> getAtheleteProfile()  throws InvalidRequest, IOException {
        Response response = this.stravaWrapper.getAtheleteProfile();
        Success<AthleteProfile> r = new Success<AthleteProfile>();
        
        AthleteProfile profile = gson.fromJson(response.body().string().replace("\"", "'"), AthleteProfile.class);
        r.setBody(profile);
        return r;
    }

    @GetMapping(value = { "/getAtheleteStatistics/{atheleteID}" })
    public CustomResponse<AthleteStatistics> getAtheleteStatistics(@PathVariable(value = "atheleteID") String atheleteID) throws InvalidRequest, IOException {
        Response response = this.stravaWrapper.getAtheleteStats(atheleteID);
        Success<AthleteStatistics> r = new Success<AthleteStatistics>();

        AthleteStatistics stats = gson.fromJson(response.body().string().replace("\"", "'"), AthleteStatistics.class);
        r.setBody(stats);
        return r;
    }

    @GetMapping("/oauth")
    public CustomResponse<String> oauthGET() {
        // pull in and update data
        boolean isAuthenticated = this.stravaWrapper.refreshAuthTokens();

        if (isAuthenticated) {
            return new Success<String>();
        } else {
            return new Failure<String>();
        }
    }

    @PostMapping("/oauth")
    public CustomResponse<String> oauthPOST(@RequestBody codeModel requestBody) {
        Boolean dataUpdated = this.stravaWrapper.setAuthTokens(requestBody);

        if (dataUpdated) {
            return new Success<String>();
        } else {
            return new Failure<String>();
        }
    }

}