/**
 * Created by millelog on 2/2/17.
 */
import java.util.ArrayList;

public class Board {

    private ArrayList<Ship> ships;
    private ArrayList<Coordinate> hits;
    private ArrayList<Coordinate> misses;

    public Board(){
        ships = new ArrayList<Ship>();
        hits = new ArrayList<Coordinate>();
        misses = new ArrayList<Coordinate>();
    }


    public void firedAt(int across, int down){
        Coordinate location = new Coordinate(across, down);
        if(isHit(location)){
            hits.add(location);
        }
        else{
            misses.add(location);
        }

    }
    public boolean isHit(Coordinate shot){
        for(Ship ship: ships){
            for(Coordinate location : ship.getCoordinates()){
                if (location.getAcross() == shot.getAcross() && location.getDown() == shot.getDown()){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Coordinate> getAllShips(){
        ArrayList<Coordinate> occupied = new ArrayList<Coordinate>();
        for(Ship currentShip: ships) {
            for(Coordinate currentCord: currentShip.getCoordinates()) {
                occupied.add(currentCord);
            }
        }
        return occupied;
    }

    public ArrayList<Ship> getShips(){
        return ships;
    }

    public ArrayList<Coordinate> getHits(){
        return hits;
    }

    public ArrayList<Coordinate> getMisses(){
        return misses;
    }

    public void addShip(Ship newShip){
        ships.add(newShip);
    }
}
