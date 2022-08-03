package runlab.models.mongoDB;

import java.time.Instant;

import runlab.models.Unix;

public class APIDetails extends DBdocument {

    public enum API_type {
        STRAVA
    };

    private API_type tokenType; // Mandatory
    private Unix expiresAt;
    private Unix expiresIn;
    private String authentication_token;
    private String refresh_token;

    // MongoRepository requires empty constructor
    public APIDetails(){}

    private APIDetails(APIDetailsBuilder builder) {
        this.tokenType = builder.api_type;
        this.expiresAt = builder.expiresAt;
        this.expiresIn = builder.expiresIn;
        this.authentication_token = builder.authentication_token;
        this.refresh_token = builder.refresh_token;
    }

    public static class APIDetailsBuilder {
        private final API_type api_type;
        private Unix expiresAt;
        private Unix expiresIn;

        private String authentication_token;
        private String refresh_token;

        public APIDetailsBuilder(API_type api_type) {
            this.api_type = api_type;
        }

        public APIDetailsBuilder authenticationToken (String token) {
            this.authentication_token = token;
            return this;
        }

        public APIDetailsBuilder refreshToken (String token) {
            this.refresh_token = token;
            return this;
        }

        public APIDetailsBuilder expiresAt (Unix timestamp) {
            this.expiresAt = timestamp;
            return this;
        }

        public APIDetailsBuilder expiresIn (Unix timestamp) {
            this.expiresIn = timestamp;
            return this;
        }

        public APIDetails Build() {
            APIDetails api = new APIDetails(this);
            validateAPIDetailsObject(api);
            return api;
        }

        private void validateAPIDetailsObject(APIDetails api) {
            // to check any assumptions made are valid
        }
    }

    public boolean hasExpired() {
        return this.expiresAt.isGreaterThan(Instant.now().getEpochSecond()) ? true : false;
    }

    public API_type getAPIType() {
        return this.tokenType;
    }

    public String getAuthenticationToken() {
        return this.authentication_token;
    }

    public String geRefreshToken() {
        return this.refresh_token;
    }
}
