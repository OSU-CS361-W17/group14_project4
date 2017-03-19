
import com.google.gson.Gson;
import spark.Request;

import java.io.UnsupportedEncodingException;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    private static Game game;

    public static void main(String[] args) {


        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //Listn for a POST request that expects the ai difficulty
        post("/setDifficulty/:difficulty", (req, res) -> setDifficulty(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function sets the difficulty boolean in the game

    private static String setDifficulty(Request req){
        game.setDifficulty(req.params("difficulty"));
        return game.generate_JSON();
    };

    //This function should return a new model

    private static String newModel() {
        game = new Game();
        return game.generate_JSON();
    }



    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.


    //public Boolean createdPlaceShip(Request req) {
        //return placeShip(req);
    //}
    //For testing purposes

    private static String placeShip(Request req) {
        //collect info to put this ship into the model
        BattleshipModel model = game.getModel();
        //get the name
        String name = req.params("id");

        //get the start
        Coordinate start = new Coordinate(Integer.parseInt(req.params("col")), Integer.parseInt(req.params("row")));

        //get orientation
        String orientation = req.params("orientation");

        //set the scan boolean variable
        boolean canScan = true;

        //get length
        int length=0;
        if(name.equals("aircraftCarrier")){
            length=5;
        }
        if(name.equals("clipper")){
            length=3;
        }
        if(name.equals("submarine")){
            canScan=false;
            length=3;
        }
        if(name.equals("dinghy")){
            length=1;
        }
        if(name.equals("battleship")) {
            length = 4;
        }
        //get end
        Coordinate end;
        if(orientation.equals("horizontal")){
            end = new Coordinate(start.getAcross()+length-1, start.getDown());
        }
        else{
            end =  new Coordinate(start.getAcross(), start.getDown()+length-1);
        }
        System.out.println("It is the place ship's fault");
        if(name.equals("dinghy") || name.equals("clipper")){
            model.placeShip(new Civillianship(name, length, start, end, orientation, canScan)); //TODO spelling lol
        }
        else{
            model.placeShip(new Battleship(name, length, start, end, orientation, canScan));
        }
        System.out.println("Got past ship placement");
        //update the game's model
        game.setModel(model);
        //return that response
        System.out.print(game.generate_JSON());
        return game.generate_JSON();
    }


    //public Boolean createdFireAt(Request req) { return fireAt(req); }
    //For testing purposes

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {
        //get the coordinate from the request object
        Coordinate shot = new Coordinate(Integer.parseInt(req.params("col")), Integer.parseInt(req.params("row")));
        //get the current model
        BattleshipModel model = game.getModel();
        //update model
        System.out.println(game.getDifficulty());
        if(game.getDifficulty().equals("easy")){
            model.aiFireEasy();
        }
        if(game.getDifficulty().equals("hard")){
            model.aiFireHard();
        }
        model.fire(shot);
        game.setModel(model);
        //return response
        return game.generate_JSON();
    }

}
