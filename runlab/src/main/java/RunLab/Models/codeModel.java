package RunLab.models;

import RunLab.models.mongoDB.APIDetails;

public class codeModel {
    private String code;
    private APIDetails.API_type api_type;

    public String getCode() {
        return this.code;
    }

    public APIDetails.API_type getAPIType() {
        return this.api_type;
    }
}