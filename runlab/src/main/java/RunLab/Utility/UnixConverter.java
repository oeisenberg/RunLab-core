package RunLab.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// Epoch is Unix at time 0
// Unix is time passed since Epoch
public class UnixConverter {

    public static LocalDateTime toDateTime(long unixTimeStamp) {
        Instant instant = Instant.ofEpochSecond(unixTimeStamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static long toUnix(LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}