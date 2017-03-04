/**
 * Created by millelog on 2/2/17.
 */
public class Coordinate {
    private int Across, Down; //Capitalized as that's what the front end expected

    public Coordinate(int a, int d){
        Across=a;
        Down=d;
    }

    public int getAcross(){
        return Across;
    }

    public int getDown(){
        return Down;
    }
}
