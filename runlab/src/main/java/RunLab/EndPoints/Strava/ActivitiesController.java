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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strava-api/v1/activites")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ActivitiesController {

    private Gson gson = new Gson();
    @Autowired
    private Strava stravaWrapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    ActivitiesController() {}

}
