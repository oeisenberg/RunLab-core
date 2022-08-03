package runlab.wrappers;

import java.io.IOException;

import okhttp3.Response;

import runlab.models.codeModel;
import runlab.models.exceptions.InvalidRequest;
import runlab.models.mongoDB.APIDetails;

public interface API {
    public APIDetails refreshAPIDetails(APIDetails api_details);
    public Response makeAPIRequest(String auth_token, String url) throws InvalidRequest;
    public APIDetails createAPIDetails(codeModel code) throws InvalidRequest, IOException;
}
