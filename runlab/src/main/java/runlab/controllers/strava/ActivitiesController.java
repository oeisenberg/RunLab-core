package runlab.controllers.strava;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/activites")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ActivitiesController {

    ActivitiesController() {}

}
