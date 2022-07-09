package RunLab.EndPoints;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;

import RunLab.Exceptions.InvalidRequest;
import RunLab.Models.*;
import RunLab.Objects.Strava.*;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Strava/v1")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class StravaController {

    private Gson gson = new Gson();
    private Strava stravaWrapper = new Strava();
    private Logger logger = LoggerFactory.getLogger(getClass());

    StravaController() {}

    @GetMapping("/refresh")
    public CustomResponse<SummaryActivity[]> refresh() throws InvalidRequest, IOException {
        logger.info("Refresh Called");
        Response response = this.stravaWrapper.pull();
        Success<SummaryActivity[]> r = new Success<SummaryActivity[]>();
        SummaryActivity[] activity = gson.fromJson(response.body().string().replace("\"", "'"), SummaryActivity[].class);
        
        r.setBody(activity);
        return r;
    }

    @GetMapping("/getAthleteProfile")
    public CustomResponse<AthleteProfile> getAthleteProfile()  throws InvalidRequest, IOException {
        logger.info("Athlete profile Called");
        Response response = this.stravaWrapper.getAthleteProfile();
        Success<AthleteProfile> r = new Success<AthleteProfile>();
        
        AthleteProfile profile = gson.fromJson(response.body().string().replace("\"", "'"), AthleteProfile.class);
        r.setBody(profile);
        return r;
    }

    @GetMapping(value = { "/getAthleteStatistics/{AthleteID}" })
    public CustomResponse<AthleteStatistics> getAthleteStatistics(@PathVariable(value = "AthleteID") String AthleteID) throws InvalidRequest, IOException {
        logger.info("Athlete Statistics Called");
        Response response = this.stravaWrapper.getAthleteStats(AthleteID);
        Success<AthleteStatistics> r = new Success<AthleteStatistics>();

        AthleteStatistics stats = gson.fromJson(response.body().string().replace("\"", "'"), AthleteStatistics.class);
        r.setBody(stats);
        return r;
    }

    @GetMapping("/oauth")
    public CustomResponse<String> oauthGET() {
        logger.info("Check if Authenticated");
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
        logger.info("Authentication Called");
        Boolean dataUpdated = this.stravaWrapper.setAuthTokens(requestBody);

        if (dataUpdated) {
            return new Success<String>();
        } else {
            return new Failure<String>();
        }
    }
}
