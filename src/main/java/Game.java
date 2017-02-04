/**
 * Created by root on 2/2/17.
 */
import com.google.gson.Gson;

public class Game {

    private BattleshipModel model;

    public BattleshipModel interpret_JSON(){return null;} //Just here to make it compile.

    public String generate_JSON(BattleshipModel myModel){
        Gson gson = new Gson();
        String json = gson.toJson(myModel);
        return json;
    }
}
