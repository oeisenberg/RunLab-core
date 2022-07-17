package RunLab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import RunLab.utility.UnixConverter;

/**
 * Unit tests for RunlabApplication unix conversion.
 */
public class unixConversionTest {

    @Test
    public void testLocalDateToUnix() {
        LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 1, 0, 0);
        assertEquals(0, UnixConverter.toUnix(ldt));
    }

    @Test
    public void testUnixToLocalDate() {
        LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 1, 0, 0);
        assertEquals(ldt, UnixConverter.toDateTime(0));
    }

}
