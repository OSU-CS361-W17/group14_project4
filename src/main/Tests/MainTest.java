import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by julianweisbord on 2/3/17.
 */
class MainTest {
    @Test
    void main() {
        String[] a =  {"/Test/1/2/3","/Something/SomethingElse"};
//        Main t = new Main(a);
//        assertNotEquals(null,t);
    }

    @Test
    void newModel() {
        Main t = new Main();
        assertEquals(true,t.createdNewModel());
    }

    @Test
    void getModelFromReq() {
        Main t = new Main();

        //assertEquals(true,t.createdGetModelFromReq());
    }

    @Test
    void placeShip() {
        Main t = new Main();
        //assertEquals(true,t.createdPlaceShip());
    }

    @Test
    void fireAt() {
        Main t = new Main();
        //assertEquals(true,t.createdFireAt());
    }

}
