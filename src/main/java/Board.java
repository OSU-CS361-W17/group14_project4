/**
 * Created by millelog on 2/2/17.
 */
import java.util.ArrayList;

public class Board {

    ArrayList<Ship> ships = new ArrayList<Ship>();
    ArrayList<Coordinate> hits = new ArrayList<Coordinate>();
    ArrayList<Coordinate> misses = new ArrayList<Coordinate>();


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
                if (location.across == shot.across && location.down == shot.down){
                    return true;
                }
            }
        }
        return false;
    }


}
