import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by root on 2/3/17.
 */
class BoardTest {
    private Board testBoard = new Board();
    private Ship testShip = new Ship("submarine", 3, new Coordinate(1, 3), new Coordinate(1,5), "horizontal");
    @Test
    void firedAt() {
        testBoard.addMiss(new Coordinate(1,3));
        assertNotEquals(testBoard.getMisses().size(), 0);

    }

    @Test
    void isHit() {
        testBoard.addShip(testShip);
        Coordinate shot = new Coordinate(1, 3);
        assertTrue(testBoard.isHit(shot));

    }

    @Test
    void getAllShips() {
        testBoard.addShip(testShip);
        assertNotEquals(testBoard.getAllShips().size(), 0);

    }

    @Test
    void addHit() {
        Coordinate shot = new Coordinate(7, 1);
        testBoard.addHit(shot);
        assertNotEquals(testBoard.getMisses().size(), 0);

    }

    @Test
    void addMiss() {
        testBoard.firedAt(7,7);
        assertNotEquals(testBoard.getMisses().size(), 0);

    }

    @Test
    void getShips() {
        testBoard.addShip(testShip);
        assertNotEquals((testBoard.getShips().size()), 0);


    }

    @Test
    void getHits() {
        testBoard.addHit(new Coordinate(5,5));
        assertNotEquals(testBoard.getHits().size(), 0);

    }

    @Test
    void getMisses() {
        testBoard.firedAt(5,5);
        assertNotEquals(testBoard.getMisses().size(), 0);
    }

    @Test
    void addShip() {
        testBoard.addShip(testShip);
        assertNotEquals(testBoard.getShips().size(), 0);
    }

}