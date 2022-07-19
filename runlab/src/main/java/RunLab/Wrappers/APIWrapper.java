package RunLab.wrappers;

import java.io.IOException;

import okhttp3.Response;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.exceptions.UnsupportedAPIException;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.mongoDB.User;
import RunLab.models.mongoDB.APIDetails.API_type;

public class APIWrapper {

    private StravaAPI stravaAPI = new StravaAPI();



    public APIDetails refreshAPIDetails(APIDetails apiDetails) {
        switch(apiDetails.getAPIType()) {
            case STRAVA:
                return stravaAPI.refreshAPIDetails(apiDetails);
            default:
                return apiDetails;
        }
    }

    public APIDetails createAPIDetails(User user, codeModel requestBody) throws UnsupportedAPIException, InvalidRequest, IOException {
        switch(APIDetails.API_type.valueOf(requestBody.getAPIType())) {
            case STRAVA:
                if (user.hasAPI(API_type.STRAVA)) {return null;}
                return stravaAPI.createAPIDetails(requestBody);
            default:
                throw new UnsupportedAPIException("API type not supported");
        }
    }

    public Response makeAPIRequest(APIDetails apiDetails, String url) throws InvalidRequest, UnsupportedAPIException {
        switch(apiDetails.getAPIType()) {
            case STRAVA:
                return stravaAPI.makeAPIRequest(apiDetails.getAuthenticationToken(), url);
            default:
                throw new UnsupportedAPIException("API type not supported");
        }
    }

}
