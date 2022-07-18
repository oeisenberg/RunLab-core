package RunLab.wrappers;

import java.io.IOException;

import okhttp3.Response;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.mongoDB.APIDetails;

public interface API {
    public APIDetails refreshAPIDetails(APIDetails api_details);
    public Response makeAPIRequest(String auth_token, String url) throws InvalidRequest;
    public APIDetails createAPIDetails(codeModel code) throws InvalidRequest, IOException;
}
