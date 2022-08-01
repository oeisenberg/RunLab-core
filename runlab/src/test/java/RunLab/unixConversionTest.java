package RunLab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import RunLab.models.Unix;

/**
 * Unit tests for RunlabApplication unix conversion.
 */
public class unixConversionTest {

    @Test
    public void testUnixToLocalDate() {
        LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 1, 0, 0);
        assertEquals(ldt, new Unix(0).toDateTime());
    }

}
