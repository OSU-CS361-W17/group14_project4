import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by julianweisbord on 2/3/17.
 */
class MainTest {
    @Test
    void main() {
        String[] a =  {"/Test/1/2/3","/Something/SomethingElse"};
    }

    @Test
    void newModel() {
        Main t = new Main();
        assertEquals(true,t.createdNewModel());
    }

    @Test
    void getModelFromReq() {
        Main t = new Main();

        assertEquals(true,t.createdGetModelFromReq());
    }

    //The test for this is to go to the hosted site and fill in each attribute with a valid position
    //Click place ship and if the ship is placed on the board then the test passes
    //If no ship appears on the player board then the test fails.
    //assertEquals(true,t.createdPlaceShip());
    @Test
    void placeShip() {
        Main t = new Main();
        assertEquals(true,t.createdPlaceShip());
    }

    //To test select an x,y coordinate to fire at. Click the fire button in the UI.
    //If a shot appears on the enemy board that is either a hit or miss then the test succeeds.
    //If nothing appears or it crashes then the test fails
    //Please see /r/therewasanattempt for more information
    @Test
    void fireAt() {
        Main t = new Main();
        assertEquals(true,t.createdFireAt());
    }
}
