package RunLab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
}
