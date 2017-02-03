/**
 * Created by root on 2/2/17.
 */
public class BattleshipModel {

    private Board ai;
    private Board player;

    public void play(){}

    public boolean gameOver(){
    }
    public void ai_shoot(Board ai, Coordinate Coord){
        fire(ai, Coord);
    }
    public void fire(Board aiOrPlayer, Coordinate Coord){
        aiOrPlayer.isHit(Coord);
    }
}
