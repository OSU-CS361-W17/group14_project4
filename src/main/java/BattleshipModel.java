/**
 * Created by root on 2/2/17.
 */
import java.util.concurrent.ThreadLocalRandom;

public class BattleshipModel {

    private Board ai;
    private Board player;

    public void play(){}

    public BattleshipModel(){
        //This constructor is so the boards are initialized so they can be used.
        ai = new Board();
        player = new Board();
    }

    public boolean gameOver(){
        //This causes an error so I'm just gonna leave this here for now -Justin
        return false;
    }
    public void ai_shoot(Board ai, Coordinate Coord){
        fire(ai, Coord);
    }
    public void fire(Board aiOrPlayer, Coordinate Coord){
        aiOrPlayer.isHit(Coord);
    }

    public void placeShipAI(){
        generateShipLocation(ai,"Destroyer",2);
        generateShipLocation(ai,"Submarine",3);
        generateShipLocation(ai,"Cruiser",3);
        generateShipLocation(ai,"Battleship",4);
        generateShipLocation(ai,"Carrier",5);
    }
    public void generateShipLocation(Board ai, String type, int size){
        int x,y,direction;
        Coordinate start = new Coordinate(0,0),end = new Coordinate(0,0);
        //This is because there's a syntax error if it's not assigned to begin with.
        boolean valid = true;
        direction = ThreadLocalRandom.current().nextInt(0,2);
        String dir;
        if (direction == 0){
            //Facing sideways
            dir = "horizontal";
            while(valid){
                valid = false;
                x = ThreadLocalRandom.current().nextInt(0,11-size);
                y = ThreadLocalRandom.current().nextInt(0,11);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross()+size,start.getDown()+size);
                for(int i=0;i<ai.getShips().size();i++){
                    Coordinate[] comp = ai.getShips().get(i).getCoordinates();
                    for(int j=0;j<comp.length;j++){
                        //Make sure there is no intersection.
                        if((comp[j].getAcross() >= start.getAcross() && comp[j].getAcross() <= end.getAcross()) &&
                                (comp[j].getDown() >= start.getDown() && comp[j].getDown() <= end.getDown())){
                            //If the point is greater than the prospective point's start but less than it's end
                            //for both X and Y, that means there's a problem.
                            valid = true;
                        }
                    }

                }
            }

        }
        else {
            //Facing downwards
            dir = "vertical";
            while(valid){
                valid = false;
                x = ThreadLocalRandom.current().nextInt(0,11);
                y = ThreadLocalRandom.current().nextInt(0,11-size);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross()+size,start.getDown()+size);
                for(int i=0;i<ai.getShips().size();i++){
                    Coordinate[] comp = ai.getShips().get(i).getCoordinates();
                    for(int j=0;j<comp.length;j++){
                        //Make sure there is no intersection.
                        if((comp[j].getAcross() >= start.getAcross() && comp[j].getAcross() <= end.getAcross()) &&
                                (comp[j].getDown() >= start.getDown() && comp[j].getDown() <= end.getDown())){
                            //If the point is greater than the prospective point's start but less than it's end
                            //for both X and Y, that means there's a problem.
                            valid = true;
                        }
                    }

                }
            }

        }

        Ship s = new Ship(type,size,start,end,dir);
        ai.addShip(s);

    }

    public Board getAI(){
        //This is for testing purposes.
        return ai;
    }
}
