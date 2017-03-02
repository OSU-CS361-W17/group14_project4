/**
 * Created by Justin on 2/28/2017.
 */
public class Battleship extends Ship {
    public Battleship(String name, int length, Coordinate start, Coordinate end, String orientation,boolean canScan){
        super(name,length,start,end,orientation,canScan);
        setBattle();
    }
}
