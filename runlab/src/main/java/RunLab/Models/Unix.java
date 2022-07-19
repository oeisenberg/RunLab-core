package RunLab.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// Epoch is Unix at time 0 (Midnight, 1st Jan 1970 in UTC)
// Unix is time passed since the Epoch
public class Unix {

    public long value;

    // MongoRepository requires empty constructor
    public Unix() {}

    public Unix(long value) {
        this.value = value;
    }

    public Unix(LocalDateTime date) {
        this.value = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public long getValue() {
        return this.value;
    }

    public LocalDateTime toDateTime() {
        Instant instant = Instant.ofEpochSecond(this.value);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public boolean isGreaterThan(long otherUnixTime) {
        return this.value > otherUnixTime;
    }

    public boolean isGreaterThanOrEquals(long otherUnixTime) {
        return this.value >= otherUnixTime;
    }

    public boolean isLessThan(long otherUnixTime) {
        return this.value < otherUnixTime;
    }

    public boolean isLessThanOrEquals(long otherUnixTime) {
        return this.value <= otherUnixTime;
    }

}