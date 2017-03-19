/**
 * Created by julianweisbord on 2/3/17.
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
    @Test
    void interpret_JSON() {
        Gson gson = new Gson();
        g = gson.toJson("test");
        Game t = new Game();
    }
    @Test
    void getModel(){
        Game t = new Game();
        assertNotEquals(null,t.getModel());
    }
    @Test
    void testGetDiff(){
        Game t = new Game();
        t.setDifficulty("easy");
        String diff = t.getDifficulty();
        assertNotEquals(null, diff);
    }
    @Test
    void setModel(){
        Game t = new Game();
        t.setModel(myModel);
        assertNotEquals(null,t.getModel());
    }

//    public String generate_JSON(BattleshipModel myModel){
}

