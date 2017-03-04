/**
 * Created by root on 2/2/17.
 */
import com.google.gson.Gson;

public class Game {

    private BattleshipModel model;

    Game(){
        model = new BattleshipModel();
        model.placeShipAI();
    }

    BattleshipModel getModel(){
        return model;
    }

    void setModel(BattleshipModel current){
        model = current;
    }

    public void interpret_JSON(String json){
        Gson gson = new Gson();
        model = gson.fromJson(json, BattleshipModel.class);
    }

    public String generate_JSON(){
        Gson gson = new Gson();
        String json = gson.toJson(model);
        return json;
    }
}
