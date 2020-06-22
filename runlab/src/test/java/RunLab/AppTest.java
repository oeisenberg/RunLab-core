package RunLab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import RunLab.Models.codeModel;
import RunLab.Wrappers.Strava;

/**
 * Unit tests for RunlabApplication.
 */
public class AppTest 
{
    @Test
    public void wrapStravaActivityRequest()
    {
        Strava stravaWrapper = new Strava();
        boolean b = stravaWrapper.pull();
        assertTrue( b );
    }

    @Test
    public void testAuth()
    {
        Strava stravaWrapper = new Strava();
        codeModel responce = new codeModel("mock code");
        stravaWrapper.setAuthTokens(responce);
    }

    @Test
    public void testReadKeys(){
        Strava stravaWrapper = new Strava();
        stravaWrapper.readKeys();
        stravaWrapper.saveKeys();
    }

    @Test
    public void testSaveKeys(){
        Strava stravaWrapper = new Strava();
        stravaWrapper.saveKeys();
    }

    @Test
    public void profile(){
        Strava stravaWrapper = new Strava();
        stravaWrapper.getProfile();
        assertTrue( true );
    }

    @Test
    public void testRefreshTokens(){
        Strava stravaWrapper = new Strava();
        stravaWrapper.readKeys();
        assertTrue( stravaWrapper.refreshAuthTokens() );
    }
}
