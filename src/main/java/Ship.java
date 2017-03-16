/**
 * Created by root on 2/2/17.
 */
public class Ship {

    private String name;
    private int length;
    private Coordinate start;
    private Coordinate end;
    private String orientation;
    private boolean canScan;
    private boolean isCivilian;

    public Ship(String name, int length, Coordinate start, Coordinate end, String orientation,boolean canScan){
        this.name=name;
        this.length=length;
        this.start=start;
        this.end=end;
        this.orientation=orientation;
        this.canScan = canScan;
    }


    public Coordinate[] getCoordinates(){
        Coordinate[] location = new Coordinate[length];
        if(orientation == "vertical"){
            for(int i=0; i<length; i++){
                location[i]= new Coordinate(start.getAcross(), start.getDown()-i);
            }
        }
        if(orientation == "horizontal"){
            for(int i=0; i<length; i++){
                location[i]= new Coordinate(start.getAcross()+i, start.getDown());
            }
        }
        return location;
    }

    public String getName(){
        return name;
    }

    public int getLength(){
        return length;
    }

    public Coordinate getStart(){
        return start;
    }

    public Coordinate getEnd(){
        return end;
    }

    public String getOrientation(){
        return orientation;
    }

    public boolean getCanScan(){ return canScan; }

    public void setCivilian(){ isCivilian = true; }
    public void setBattle(){ isCivilian = false; }

    public boolean isCivilian(){
        return isCivilian;
    }
}
