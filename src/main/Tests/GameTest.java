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
        Gson gson = new Gson();
        String json = gson.toJson(myModel);
        json = g;
        assertEquals("testjson", json);

    }

//    public String generate_JSON(BattleshipModel myModel){
}

