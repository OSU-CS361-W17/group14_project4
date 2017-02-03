/**
 * Created by millelog on 2/2/17.
 */
import java.util.ArrayList;

public class Board {

    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private ArrayList<Coordinate> hits = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> misses = new ArrayList<Coordinate>();


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

    public ArrayList<Ship> getShips(){
        return ships;
    }

    public ArrayList<Coordinate> getHits(){
        return hits;
    }

    public ArrayList<Coordinate> getMisses(){
        return misses;
    }
}
