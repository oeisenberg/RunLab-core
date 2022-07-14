package RunLab.utility;

import java.time.Instant;
import java.util.Date;

public class UnixConverter {

    public static Date JavaTimeStampToDateTime( long unixTimeStamp ) {
        Instant instant = Instant.ofEpochSecond( unixTimeStamp );
        Date date = Date.from( instant );
        return date;
    }

    public static long toUnix(Date date) {
        // TODO:
        return 0;
    }
}