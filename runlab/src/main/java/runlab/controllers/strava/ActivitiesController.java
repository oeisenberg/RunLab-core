package runlab.controllers.strava;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;

import runlab.models.exceptions.InvalidRequest;
import runlab.models.responses.*;
import runlab.models.strava.*;
import runlab.wrappers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/activites")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ActivitiesController {

    private Gson gson = new Gson();
    @Autowired
    private StravaAPI stravaWrapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    ActivitiesController() {}

}
