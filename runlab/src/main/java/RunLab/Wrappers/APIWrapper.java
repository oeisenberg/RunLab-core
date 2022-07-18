package RunLab.wrappers;

import RunLab.models.codeModel;
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

    public APIDetails createAPIDetails(codeModel requestBody) throws UnsupportedAPIException {
        switch(requestBody.getAPIType()) {
            case STRAVA:
                return stravaAPI.createAPIDetails(requestBody);
            default:
                throw new UnsupportedAPIException("API type not supported");
        }
    }

}
