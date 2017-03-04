/**
 * Created by julianweisbord on 2/3/17.
 */
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private BattleshipModel myModel = new BattleshipModel();
    private String g = "testjson";

    @org.junit.jupiter.api.Test
    void generate_JSON() {
        Game t = new Game();
        Gson gson = new Gson();
        String json = gson.toJson(myModel);
        json = g;
        assertNotEquals(null, t.generate_JSON());

    }
    /*
    @Test
    void interpret_JSON() {
        Gson gson = new Gson();
        String g = gson.toJson("");
        Game t = new Game();
        assertNotEquals(null,t.interpret_JSON("HelloWorld"));
    }*/
    @Test
    void getModel(){
        Game t = new Game();
        assertNotEquals(null,t.getModel());
    }
    @Test
    void setModel(){
        Game t = new Game();
        t.setModel(myModel);
        assertNotEquals(null,t.getModel());
    }

//    public String generate_JSON(BattleshipModel myModel){
}

