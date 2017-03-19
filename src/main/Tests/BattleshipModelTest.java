import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Justin on 2/3/2017.
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
        testModel.placeShip(new Ship("Clipper", 3, new Coordinate(1,1), new Coordinate(1,3), "vertical", true));
        Coordinate c = new Coordinate(0,0);
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                c = new Coordinate(i,j);
                testModel.aiFire(c);
            }
        }
        assertNotEquals(0,testModel.getPlayer().getMisses());
        assertNotEquals(0,testModel.getPlayer().getHits());
    }

    @Test
    void fire1() {
        BattleshipModel testModel = new BattleshipModel();
        Coordinate c = new Coordinate(0,0);
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                c = new Coordinate(i,j);
                testModel.fire(c);
            }
        }
        assertNotEquals(0,testModel.getAI().getMisses());
        assertNotEquals(0,testModel.getAI().getHits());
    }

    @Test
    void fire(){
        Coordinate shot = new Coordinate(1,2);
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShipAI();
        testModel.fire(shot);
        assertNotEquals(testModel.getAI().getMisses().size(), 0);
    }

    @Test
    void aifire(){
        Coordinate shot = new Coordinate(1,2);
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShip(new Ship("Clipper", 3, new Coordinate(1,1), new Coordinate(1,3), "vertical", true));
        testModel.fire(shot);
        assertNotEquals(0,testModel.getPlayer().getMisses());
    }

    @Test
    void aiFireHard(){
        BattleshipModel model = new BattleshipModel();
        model.aiFireHard();
        assertNotNull(model.getPlayer().getAllShots().get(0));
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

    @Test
    void fireAll() {
        Coordinate shot;
        BattleshipModel testModel = new BattleshipModel();
        testModel.placeShipAI();
        for(int i=1;i<11;i++){
            for(int j=1;j<11;j++){
                shot = new Coordinate(i,j);
                testModel.fire(shot);
            }
        }
        assertNotEquals(testModel.getAI().getMisses().size(), 0);
    }

    @Test
    void placeShipAIEasyTest(){
        BattleshipModel t = new BattleshipModel();
        t.placeShipAIEasy();
        assertEquals(5,t.getAI().getShips().size());
    }

    @Test
    void aiFireEasyTest(){
        BattleshipModel t = new BattleshipModel();
        t.aiFireEasy();
        assertNotNull(t.getPlayer().getAllShots().get(0));
    }
}