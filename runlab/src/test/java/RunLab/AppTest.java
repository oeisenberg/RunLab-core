package RunLab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import okhttp3.Response;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import RunLab.models.codeModel;
import RunLab.models.responses.*;
import RunLab.models.strava.AthleteStatistics;
import RunLab.models.strava.SummaryActivity;
import RunLab.wrappers.Strava;

/**
 * Unit tests for RunlabApplication.
 */
public class AppTest {
    
    Gson gson = new Gson();
    Strava stravaWrapper = new Strava();

    // @Test
    // public void wrapStravaActivityRequest()
    // {
    //     Strava stravaWrapper = new Strava();
    //     boolean b = stravaWrapper.pull();
    //     assertTrue( b );
    // }

    // @Test
    // public void testAuth()
    // {
    //     Strava stravaWrapper = new Strava();
    //     codeModel responce = new codeModel("mock code");
    //     stravaWrapper.setAuthTokens(responce);
    // }

    // @Test
    // public void testReadKeys(){
    //     Strava stravaWrapper = new Strava();
    //     stravaWrapper.readKeys();
    //     stravaWrapper.saveKeys();
    // }

    // @Test
    // public void testSaveKeys(){
    //     Strava stravaWrapper = new Strava();
    //     stravaWrapper.saveKeys();
    // }

    // @Test
    // public void profile(){
    //     Strava stravaWrapper = new Strava();
    //     stravaWrapper.getProfile();
    //     assertTrue( true );
    // }

    // @Test
    // public void testRefreshTokens(){
    //     Strava stravaWrapper = new Strava();
    //     stravaWrapper.readKeys();
    //     assertTrue( stravaWrapper.refreshAuthTokens() );
    // }

    // @Test
    // public void testMongoDB(){
    //     // MongoDB db = new MongoDB();
    //     assertTrue( true );
    // }

    // @Test
    // public void testAtheleteStatsRequest(){
    //     Strava stravaWrapper = new Strava();
    //     try {
    //         HttpResponse<String> response = stravaWrapper.getAtheleteStats("16443776");
    //         if (response.statusCode() == 200){
    //             assertTrue( true );
    //         } 
    //     } catch (Exception e) {
    //         assertTrue( false );
    //     }
    // }

    // @Test
    // public void testIngestRequest(){
    //     Strava stravaWrapper = new Strava();
    //     try {
    //         HttpResponse<String> response = stravaWrapper.getAtheleteStats("16443776");
    //         if (response.statusCode() == 200){
    //             Response myResponse = new Success();
    //             myResponse.ingest(response);
    //             assertTrue( true );
    //         } 
    //     } catch (Exception e) {
    //         assertTrue( false );
    //     }
    // }

    @Test
    public void testAthleteStatisticsEndPoint(){
        try {
            Response response = this.stravaWrapper.getAthleteStats("16443776");
            Success<AthleteStatistics> r = new Success<AthleteStatistics>();

            AthleteStatistics stats = gson.fromJson(response.body().string().replace("\"", "'"), AthleteStatistics.class);
            r.setBody(stats);
            
        } catch (Exception e) {
            assertTrue( false );
        }
    }

    @Test
    public void testPullEndPoint() throws Exception{
        try {
            Response response = this.stravaWrapper.pull();
            Success<SummaryActivity[]> r = new Success<SummaryActivity[]>();

            SummaryActivity[] activity = gson.fromJson(response.body().string().replace("\"", "'"), SummaryActivity[].class);
            r.setBody(activity);

            System.out.println(r);
            
        } catch (Exception e) {
            throw e;
            // assertTrue( false );
        }
    }
}
