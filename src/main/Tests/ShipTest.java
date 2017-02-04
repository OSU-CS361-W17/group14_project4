/**
 * Created by julianweisbord on 2/3/17.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private Coordinate[] coords = new Coordinate[1];
    private Coordinate location = new Coordinate(5,5);
    private Ship theBlackPearl = new Ship("Black Pearl", 5, location, location, "vertical");



    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Black Pearl",theBlackPearl.getName());

    }

    @org.junit.jupiter.api.Test
    void getLength() { assertEquals(5, theBlackPearl.getLength());
    }

    @org.junit.jupiter.api.Test
    void getStart() { assertEquals(location, theBlackPearl.getStart());

    }

    @org.junit.jupiter.api.Test
    void getEnd() {
        assertEquals(location, theBlackPearl.getEnd());
    }

    @org.junit.jupiter.api.Test
    void getOrientation() {
        assertEquals("vertical", theBlackPearl.getOrientation());

    }
    @org.junit.jupiter.api.Test
    void getCoordinates(){
    Coordinate[] getCoordinates(){
        coords[0] = location;
        assertEquals(coords, theBlackPearl.getCoordinates());
    }

}
