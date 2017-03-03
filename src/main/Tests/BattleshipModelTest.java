import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pomer on 2/3/2017.
 */
class BattleshipModelTest {
    @Test
    void gameOver() {
        BattleshipModel testModel = new BattleshipModel();
        assertEquals(false,testModel.gameOver());
    }

    @Test
    void aiFire() {
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShip();
        Coordinate c = new Coordinate(0,0);
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                c = new Coordinate(i,j);
                testModel.aiFire(testModel.getPlayer(),c);
            }
        }
        assertNotEquals(0,testModel.getPlayer().getMisses().size());
        assertNotEquals(0,testModel.getPlayer().getHits().size());
    }

    @Test
    void fire1() {
        BattleshipModel testModel = new BattleshipModel();
        Coordinate c = new Coordinate(0,0);
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                c = new Coordinate(i,j);
                testModel.fire(testModel.getAI(),c);
            }
        }
        assertNotEquals(0,testModel.getAI().getMisses().size());
        assertNotEquals(0,testModel.getAI().getHits().size());
    }

    @Test
    void fire(){
        Coordinate shot = new Coordinate(1,2);
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShipAI();
        testModel.fire(testModel.getAI(), shot);
        assertNotEquals(testModel.getAI().getMisses().size(), 0);
    }

    @Test
    void aifire(){
        Coordinate shot = new Coordinate(1,2);
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShip();
        testModel.fire(testModel.getPlayer(), shot);
        assertNotEquals(0,testModel.getPlayer().getMisses());
    }


    @Test
    void placeShipAI() {
        //Make sure all ships are placed
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShipAI();
        //This basically assumes all 5 ships have been placed.
        assertEquals(5,testModel.getAI().getShips().size());
    }

    @Test
    void generateShipLocation() {
        //This one should make sure there's no intersection.
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShipAI();
        for(int i=0;i<5;i++){
            for(int j=i+1;j<5;j++){
                for(int k=0;k<testModel.getAI().getShips().get(i).getCoordinates().length;k++){
                    for(int m=0;m<testModel.getAI().getShips().get(j).getCoordinates().length;m++){
                        //Basically just running a bruteforce check here
                        //to make sure no points intersect.
                        assertNotEquals(testModel.getAI().getShips().get(i).getCoordinates()[k],
                                testModel.getAI().getShips().get(j).getCoordinates()[m]);
                        //Efficiency and I are not on speaking terms. -Justin
                        //We still love you - Group 14
                    }
                }
            }
        }
    }

}