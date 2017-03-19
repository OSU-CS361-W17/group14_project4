/**
 * Created by root on 2/2/17.
 */
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Random;

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
        return false;
    }
    //Why does this even ask for the board? It should know it already!
    public void aiFire(Coordinate shot){
        //get array of all occupied coordinates on player board
        ArrayList<Coordinate> ships = player.getAllShips();
        //for each cordinate
        for(Coordinate occupied: ships){
            //if the shot matches a ship location
            if(shot.getAcross() == occupied.getAcross() &&
                    shot.getDown() == occupied.getDown()){
                //add to player's hit list
                player.addHit(shot);
                player.addShot(shot);
                return;
            }
        }
        //If it is not occupied then add to miss list
        player.addMiss(shot);
        player.addShot(shot);
    }

    public void fire(Coordinate shot){
        //get array of all occupied coordinates on ai board
        ArrayList<Coordinate> ships = ai.getAllShips();
        //for each cordinate
        for(Coordinate occupied: ships){
            //if the shot matches a ship location
            if(shot.getAcross() == occupied.getAcross() &&
                    shot.getDown() == occupied.getDown()){
                //add to ai's hit list
                ai.addHit(shot);
                ai.addShot(shot);
                return;
            }
        }
        //If it is not occupied then add to miss list
        ai.addMiss(shot);
        ai.addShot(shot);

    }

    public void aiFireHard()
    {
        ArrayList<Coordinate> ships = player.getAllShips();


        ArrayList<Coordinate> hits = player.getHits();
        ArrayList<Coordinate> shots = player.getAllShots();

        boolean checkUp = true;
        boolean checkDown = true;
        boolean checkLeft = true;
        boolean checkRight = true;

        Coordinate up = null;
        Coordinate down = null;
        Coordinate left = null;
        Coordinate right = null;

        if(shots.size() > 0) {

            if (hits.size() > 0) {
                Coordinate previousHit = hits.get(hits.size() - 1);


                int x = previousHit.getAcross();
                int y = previousHit.getDown();

                up = new Coordinate(x, y - 1);
                down = new Coordinate(x, y + 1);
                left = new Coordinate(x - 1, y);
                right = new Coordinate(x + 1, y);


                for (int i = 0; i < shots.size(); i++) {
                    Coordinate curr = shots.get(i);
                    if (up.getAcross() == curr.getAcross() && up.getDown() == curr.getDown()) {
                        checkUp = false;
                    }
                    if (down.getAcross() == curr.getAcross() && down.getDown() == curr.getDown()) {
                        checkDown = false;
                    }
                    if (left.getAcross() == curr.getAcross() && left.getDown() == curr.getDown()) {
                        checkLeft = false;
                    }
                    if (right.getAcross() == curr.getAcross() && right.getDown() == curr.getDown()) {
                        checkRight = false;
                    }
                }
            } }

        if(up != null && checkUp == true){ aiFire(up); }
        else if(down != null && checkDown == true){ aiFire(down); }
        else if(left != null && checkLeft == true){ aiFire(left); }
        else if(right != null && checkRight == true){ aiFire(right); }
        else{
            int max = 10;
            int min = 1;
            Random random = new Random();
            int randRow = random.nextInt(max - min + 1) + min;
            int randCol = random.nextInt(max - min + 1) + min;
            Boolean repeated = false;
            while(!repeated){
                repeated = true;
                randRow = random.nextInt(max - min + 1) + min;
                randCol = random.nextInt(max - min + 1) + min;

                for(int i=0;i<shots.size();i++){
                    if(shots.get(i).getDown() == randCol && shots.get(i).getAcross() == randRow){
                        repeated = false;
                    }
                }
            }

            Coordinate coor = new Coordinate(randRow,randCol);
            aiFire(coor);

        }


    }

    public void placeShipAI(){
        generateShipLocation(ai,"Clipper",3);
        generateShipLocation(ai,"Submarine",3);
        generateShipLocation(ai,"Dinghy",1);
        generateShipLocation(ai,"Battleship",4);
        generateShipLocation(ai,"Carrier",5);
    }

    //This could either go in a
    public void placeShipAIEasy(){
        //This could probably be shortened, but this should be fine.
        Coordinate start = new Coordinate(3,3);
        Coordinate end = new Coordinate(3,5);
        Ship s = new Civillianship("Clipper",3,start,end,"vertical",true);
        ai.addShip(s);

        start = new Coordinate(5,3);
        end = new Coordinate(5,5);
        s = new Battleship("Submarine",3,start,end,"vertical",false);
        ai.addShip(s);

        start = new Coordinate(9,5);
        end = new Coordinate(9,5);
        s = new Civillianship("Dinghy",1,start,end,"vertical",true);
        ai.addShip(s);

        start = new Coordinate(2,6);
        end = new Coordinate(5,6);
        s = new Battleship("Battleship",4,start,end,"horizontal",true);
        ai.addShip(s);

        start = new Coordinate(1,1);
        end = new Coordinate(5,1);
        s = new Battleship("Carrier",5,start,end,"horizontal",true);
        ai.addShip(s);
    }

    public void aiFireEasy(){
        //Pattern: Just shoot row by row.
        int turn = player.getMisses().size()+player.getHits().size();
        int x = turn/10+1;
        int y = turn%10+1;
        Coordinate c = new Coordinate(x,y);
        aiFire(c);

    }

    public void placeShip(Ship ship){
        player.addShip(ship);
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
                valid = true;
                x = ThreadLocalRandom.current().nextInt(1,11-size);
                y = ThreadLocalRandom.current().nextInt(1,11);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross()+size-1,start.getDown());
                for(int i=0;i<ai.getShips().size();i++){
                    Coordinate[] comp = ai.getShips().get(i).getCoordinates();
                    for(int j=0;j<comp.length;j++){
                        //Make sure there is no intersection.
                        if(comp[j].getAcross() == start.getAcross() && comp[j].getDown() == end.getDown()){
                            //If the point is greater than the prospective point's start but less than it's end
                            //for both X and Y, that means there's a problem.
                            valid = false;
                        }
                    }

                }
            }

        }
        else {
            //Facing downwards
            dir = "vertical";
            while(!valid){
                valid = true;
                x = ThreadLocalRandom.current().nextInt(1,11);
                y = ThreadLocalRandom.current().nextInt(1,11-size);
                start = new Coordinate(x,y);
                end = new Coordinate(start.getAcross(),start.getDown()+size-1);
                for(int i=0;i<ai.getShips().size();i++){
                    Coordinate[] comp = ai.getShips().get(i).getCoordinates();
                    for(int j=0;j<comp.length;j++){
                        //Make sure there is no intersection.
                        if(comp[j].getAcross() == start.getAcross() && comp[j].getDown() == end.getDown()){
                            //If the point is greater than the prospective point's start but less than it's end
                            //for both X and Y, that means there's a problem.
                            valid = false;
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
