package RunLab.EndPoints.Strava;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;

import RunLab.Exceptions.InvalidRequest;

import RunLab.Objects.Strava.*;
import RunLab.Responces.*;
import RunLab.Wrappers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strava-api/v1/athlete")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AthleteController {

    private Gson gson = new Gson();
    @Autowired
    private Strava stravaWrapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    AthleteController() {}

    @GetMapping("/profile")
    public CustomResponse<AthleteProfile> getAthleteProfile()  throws InvalidRequest, IOException {
        logger.info("Athlete profile Called");
        Response response = this.stravaWrapper.getAthleteProfile();
        Success<AthleteProfile> r = new Success<AthleteProfile>();
        
        AthleteProfile profile = gson.fromJson(response.body().string().replace("\"", "'"), AthleteProfile.class);
        r.setBody(profile);
        return r;
    }

    @GetMapping(value = { "/statistics" })
    public CustomResponse<AthleteStatistics> getAthleteStatistics(@RequestParam(value = "ID", defaultValue="16443776") String ID) throws InvalidRequest, IOException {
        logger.info("Athlete Statistics Called");
        Response response = this.stravaWrapper.getAthleteStats(ID);
        Success<AthleteStatistics> r = new Success<AthleteStatistics>();

        AthleteStatistics stats = gson.fromJson(response.body().string().replace("\"", "'"), AthleteStatistics.class);
        r.setBody(stats);
        return r;
    }

    @GetMapping("/activies")
    public CustomResponse<SummaryActivity[]> refresh() throws InvalidRequest, IOException {
        logger.info("Refresh Called");
        Response response = this.stravaWrapper.pull();
        Success<SummaryActivity[]> r = new Success<SummaryActivity[]>();
        SummaryActivity[] activity = gson.fromJson(response.body().string().replace("\"", "'"), SummaryActivity[].class);
        
        r.setBody(activity);
        return r;
    }

}