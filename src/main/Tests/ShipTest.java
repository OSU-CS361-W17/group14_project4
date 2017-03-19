/**
 * Created by julianweisbord on 2/3/17.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private Coordinate[] coords = new Coordinate[1];
    private Coordinate location = new Coordinate(5,5);
    private Ship theBlackPearl = new Ship("Black Pearl", 5, location, location, "vertical",true);



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
        coords[0] = location;
        assertEquals(coords[0].getAcross(), theBlackPearl.getCoordinates()[0].getAcross());
    }

    @org.junit.jupiter.api.Test
    void scanTest(){
        assertEquals(true, theBlackPearl.getCanScan());
    }

    @org.junit.jupiter.api.Test
    void setCivilian(){
        theBlackPearl.setCivilian();
        assertEquals(true, theBlackPearl.isCivilian());

    }

    @org.junit.jupiter.api.Test
    void setBattle(){
        theBlackPearl.setBattle();
        assertEquals(false, theBlackPearl.isCivilian());

    }

    @org.junit.jupiter.api.Test
    void isCivilian(){
        theBlackPearl.setCivilian();
        assertEquals(true, theBlackPearl.isCivilian());

    }


}
