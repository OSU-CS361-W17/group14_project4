/**
 * Created by root on 2/2/17.
 */
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class BattleshipModel {

    private Board ai;
    private Board player;

    //public void play(){}

    public BattleshipModel(){
        //This constructor is so the boards are initialized so they can be used.
        ai = new Board();
        player = new Board();
    }

    public boolean gameOver(){
        //This causes an error so I'm just gonna leave this here for now -Justin
        //oh gawd this made me laugh
        return false;
    }
    public void aiFire(Board target, Coordinate shot){
        //get array of all occupied coordinates on ai board
        ArrayList<Coordinate> ships = target.getAllShips();
        //for each cordinate
        for(Coordinate occupied: ships){
            //if the shot matches a ship location
            if(shot.getAcross() == occupied.getAcross() &&
                    shot.getDown() == occupied.getDown()){
                //add to ai's hit list
                player.addHit(shot);
                return;
            }
        }
        //If it is not occupied then add to miss list
        player.addMiss(shot);
    }

    public void fire(Board target, Coordinate shot){
        //get array of all occupied coordinates on ai board
        ArrayList<Coordinate> ships = target.getAllShips();
        //for each cordinate
        for(Coordinate occupied: ships){
            //if the shot matches a ship location
            if(shot.getAcross() == occupied.getAcross() &&
                    shot.getDown() == occupied.getDown()){
                //add to ai's hit list
                ai.addHit(shot);
                return;
            }
        }
        //If it is not occupied then add to miss list
        ai.addMiss(shot);

    }

    public void placeShipAI(){
        generateShipLocation(ai,"Clipper",3);
        generateShipLocation(ai,"Submarine",3);
        generateShipLocation(ai,"Dinghy",1);
        generateShipLocation(ai,"Battleship",4);
        generateShipLocation(ai,"Carrier",5);
    }
    public void placeShip(){
        generateShipLocation(player,"Clipper",3);
        generateShipLocation(player,"Submarine",3);
        generateShipLocation(player,"Dinghy",1);
        generateShipLocation(player,"Battleship",4);
        generateShipLocation(player,"Carrier",5);
    }
  
    public void generateShipLocation(Board ai, String type, int size){
        int x,y,direction;
        Coordinate start = new Coordinate(0,0),end = new Coordinate(0,0);
        //This is because there's a syntax error if it's not assigned to begin with.
        boolean valid = false;
        direction = ThreadLocalRandom.current().nextInt(0,2);
        String dir;
        Ship s = null;
        if (direction == 0){
            //Facing sideways
            dir = "horizontal";
            while(!valid){
                x = ThreadLocalRandom.current().nextInt(0,11-size);
                y = ThreadLocalRandom.current().nextInt(0,11);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross()+size,start.getDown());
                //get the coordinates of the current ship
                ArrayList<Coordinate> currentShipCoordinates = new ArrayList<Coordinate>();
                for(int i=0; i<size; i++){
                    currentShipCoordinates.add(new Coordinate(x+i, y));
                }
                //get all occupied coordinates on the ai board
                ArrayList<Coordinate> occupiedCoordinates = ai.getAllShips();
                outerloop:
                for(Coordinate currentShipCoordinate : currentShipCoordinates) {
                    //Make sure there is no intersection.
                    for (Coordinate occupied : occupiedCoordinates) {
                        if (occupied.getAcross() == currentShipCoordinate.getAcross() &&
                                occupied.getDown() == currentShipCoordinate.getDown()) {
                            valid = false;
                            break outerloop;
                        } else {
                            valid = true;
                        }
                    }
                }
            }

        }
        else {
            //Facing downwards
            dir = "vertical";
            while(!valid){
                x = ThreadLocalRandom.current().nextInt(0,11);
                y = ThreadLocalRandom.current().nextInt(0,11-size);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross(),start.getDown()+size);
                //get the coordinates of the current ship
                ArrayList<Coordinate> currentShipCoordinates = new ArrayList<Coordinate>();
                for(int i=0; i<size; i++){
                    currentShipCoordinates.add(new Coordinate(x, y+i));
                }
                //get all occupied coordinates on the ai board
                ArrayList<Coordinate> occupiedCoordinates = ai.getAllShips();
                outerloop:
                for(Coordinate currentShipCoordinate : currentShipCoordinates) {
                    //Make sure there is no intersection.
                    for (Coordinate occupied : occupiedCoordinates) {
                        if (occupied.getAcross() == currentShipCoordinate.getAcross() &&
                                occupied.getDown() == currentShipCoordinate.getDown()) {
                            valid = false;
                            break outerloop;
                        } else {
                            valid = true;
                        }
                    }
                }
            }

        }
        if (type == "Clipper"){
            s = new Civillianship(type,size,start,end,dir,true);
        } else if (type == "Submarine"){
            s = new Battleship(type,size,start,end,dir,false);
        } else if (type == "Dinghy"){
            s = new Civillianship(type,size,start,end,dir,true);
        } else if (type == "Battleship"){
            s = new Battleship(type,size,start,end,dir,true);
        } else if (type == "Carrier"){
            s = new Battleship(type,size,start,end,dir,true);
        }
        //Hard coded, but it's fine since this is all that's going to be inputted.

        ai.addShip(s);

    }

    public Board getAI(){
        //This is for testing purposes.
        return ai;
    }
    public Board getPlayer(){
        return player;
    }
}
