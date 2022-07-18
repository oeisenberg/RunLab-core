package RunLab.controllers.Strava;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;

import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.exceptions.UnsupportedAPIException;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.mongoDB.User;
import RunLab.models.responses.*;
import RunLab.models.strava.*;
import RunLab.wrappers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/athlete")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AthleteController {

    private Gson gson = new Gson();
    @Autowired
    private APIWrapper apiWrapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    AthleteController() {
    }

    @GetMapping("/profile")
    public CustomResponse<AthleteProfile> getAthleteProfile() throws InvalidRequest, UnsupportedAPIException, IOException {
        logger.info("Athlete profile Called");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response response = this.apiWrapper.makeAPIRequest(principal.getAPI(APIDetails.API_type.STRAVA), "athlete/");
        if (response.isSuccessful()) {
            Success<AthleteProfile> r = new Success<AthleteProfile>();
            AthleteProfile profile = gson.fromJson(response.body().string().replace("\"", "'"),AthleteProfile.class);
            r.setBody(profile);
            return r;
        } else {
            throw new InvalidRequest(response.message());
        }
    }

    @GetMapping(value = { "/statistics" })
    public CustomResponse<AthleteStatistics> getAthleteStatistics (
            @RequestParam(value = "ID", defaultValue = "16443776") String ID) throws InvalidRequest, UnsupportedAPIException, IOException {
        logger.info("Athlete Statistics Called");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response response = this.apiWrapper.makeAPIRequest(principal.getAPI(APIDetails.API_type.STRAVA), "athletes/" + ID + "/stats");
        if (response.isSuccessful()) {
            Success<AthleteStatistics> r = new Success<AthleteStatistics>();
            AthleteStatistics stats = gson.fromJson(response.body().string().replace("\"", "'"),
                    AthleteStatistics.class);
            r.setBody(stats);
            return r;
        } else {
            throw new InvalidRequest(response.message());
        }
    }

    @GetMapping("/activities")
    public CustomResponse<SummaryActivity[]> refresh() throws InvalidRequest, UnsupportedAPIException, IOException {
        logger.info("Refresh Called");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response response = this.apiWrapper.makeAPIRequest(principal.getAPI(APIDetails.API_type.STRAVA), "athlete/activities?page=1&per_page=10");
        if (response.isSuccessful()) {
            Success<SummaryActivity[]> r = new Success<SummaryActivity[]>();
            SummaryActivity[] activity = gson.fromJson(response.body().string().replace("\"", "'"),
                    SummaryActivity[].class);
            r.setBody(activity);
            return r;
        } else {
            throw new InvalidRequest(response.message());
        }

    }

}
