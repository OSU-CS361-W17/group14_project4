/**
 * Created by root on 2/2/17.
 */
public class Ship {

    private String name;
    private int length;
    private Coordinate start;
    private Coordinate end;
    private String orientation;

    public Ship(String name, int length, Coordinate start, Coordinate end, String orientation){
        this.name=name;
        this.length=length;
        this.start=start;
        this.end=end;
        this.orientation=orientation;
    }


    public Coordinate[] getCoordinates(){
        Coordinate[] location = new Coordinate[length];
        if(orientation == "vertical"){
            for(int i=0; i<length; i++){
                location[i]= new Coordinate(start.getAcross(), start.getDown()-1);
            }
        }
        if(orientation == "horizontal"){
            for(int i=0; i<length; i++){
                location[i]= new Coordinate(start.getAcross()+1, start.getDown());
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
}
