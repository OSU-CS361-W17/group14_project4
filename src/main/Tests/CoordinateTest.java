import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by root on 2/3/17.
 */
class CoordinateTest {
    private Coordinate test = new Coordinate(1, 2);
    @Test
    void getAcross() {
        assertEquals(1, test.getAcross());
    }

    @Test
    void getDown() {
        assertEquals(2, test.getDown());
    }

}