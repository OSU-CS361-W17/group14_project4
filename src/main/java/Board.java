/**
 * Created by millelog on 2/2/17.
 */
import javax.xml.stream.Location;
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
        if(isHit(location)) {
            if (getIsCivilian(location)) {
                int shipHit = hitShip(location);
                Coordinate shipCoords[] = ships.get(shipHit).getCoordinates();
                for (int i = 0; i < shipCoords.length; i++) {
                    hits.add(shipCoords[i]);
                }
            } else {
                hits.add(location);
            }
        } else{
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
    public void addHit(Coordinate shot) {hits.add(shot);}

    public void addMiss(Coordinate shot) {misses.add(shot);}

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

    public boolean getIsCivilian(Coordinate loc) {
        ArrayList<Ship> temp = ships;
        System.out.println("USER COORDINATES:");
        System.out.println(loc.getAcross());
        System.out.println(loc.getDown());

        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.get(i).getCoordinates().length; j++) {
                System.out.println("SHIP COORDINATES:");
                System.out.println(temp.get(i).getCoordinates()[j].getAcross());
                System.out.println(temp.get(i).getCoordinates()[j].getDown());
                if (temp.get(i).getCoordinates()[j].getAcross() == loc.getAcross() &&
                        temp.get(i).getCoordinates()[j].getDown() == loc.getDown()) {
                    System.out.println("hello");
                    return temp.get(i).isCivilian();
                }
            }
        }
        return false;
        //Failsafe because it'll bug otherwise.
        //This should never run, though, because the precondition is that it'll run first.
    }

    public int hitShip(Coordinate loc) {
        for(int i = 0; i < ships.size(); i++){
            for (int j = 0; j < ships.get(i).getCoordinates().length; j++){
                if (ships.get(i).getCoordinates()[j].getAcross() == loc.getAcross() &&
                        ships.get(i).getCoordinates()[j].getDown() == loc.getDown()){
                    return i;
                }
            }
        }
        return 0;
        //Failsafe again. See above.
    }
}
