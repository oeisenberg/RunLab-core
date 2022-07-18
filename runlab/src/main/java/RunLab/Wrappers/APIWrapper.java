package RunLab.wrappers;

import java.io.IOException;

import okhttp3.Response;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.exceptions.UnsupportedAPIException;
import RunLab.models.mongoDB.APIDetails;

public class APIWrapper {

    private StravaAPI stravaAPI = new StravaAPI();

    public APIDetails refreshAPIDetails(APIDetails apiDetails) {
        switch(apiDetails.getAPIType()) {
            case STRAVA:
                stravaAPI.refreshAPIDetails(apiDetails);
            break;
        }
        return apiDetails;
    }

    public APIDetails createAPIDetails(codeModel requestBody) throws UnsupportedAPIException, InvalidRequest, IOException {
        switch(requestBody.getAPIType()) {
            case STRAVA:
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
