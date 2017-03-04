import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    private static Game game;

    public static void main(String[] args) {
        //Create the game object
        game = new Game();

        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req, res));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req, res));
    }

    //This function should return a new model
    public Boolean createdNewModel() { return newModel() != null; }
    //For testing purposes

    private static String newModel() {
        return game.generate_JSON();
    }

    //This function should accept an HTTP request and deseralize it into an actual Java object.

    //public Boolean createdGetModelFromReq(Request req) { return getModelFromReq(req); }
    //For testing purposes

    private static BattleshipModel getModelFromReq(Request req){
        for( String attribute : req.attributes()){
            System.out.println(attribute);
        }
        return game.getModel();
    }

    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.


    //public Boolean createdPlaceShip(Request req) {
    //return placeShip(req);
    //}
    //For testing purposes

    private static Boolean placeShip(Request req, Response res) {
        //collect info to put this ship into the model
        BattleshipModel model = game.getModel();
        //get the name
        String name = req.attribute("name");
        //get the start
        Coordinate start = new Coordinate(req.attribute("Across"), req.attribute("Down"));
        //get orientation
        String orientation = req.attribute("orientation");
        //set the scan boolean variable
        boolean canScan = (name.equals("submarine"));
        //get length
        int length = req.attribute("size");
        //get end
        Coordinate end;
        if(orientation.equals("horizontal")){
            end = new Coordinate(start.getAcross()+length, start.getDown());
        }
        else{
            end =  new Coordinate(start.getAcross(), start.getDown()+length);
        }
        if(name.equals("dinghy") || name.equals("clipper")){
            model.placeShip(new Civillianship(name, length, start, end, orientation, canScan)); //TODO spelling lol
        }
        else{
            model.placeShip(new Battleship(name, length, start, end, orientation, canScan));
        }
        //update the game's model
        game.setModel(model);
        //return that response
        res.body(game.generate_JSON());
        return true;
    }


    //public Boolean createdFireAt(Request req) { return fireAt(req); }
    //For testing purposes

    //Similar to placeShip, but with firing.
    private static Boolean fireAt(Request req, Response res) {
        //get the coordinate from the request object
        Coordinate shot = new Coordinate(req.attribute("Across"), req.attribute("Down"));
        //get the current model
        BattleshipModel model = game.getModel();
        //update model
        model.fire(model.getAI(), shot);
        game.setModel(model);
        //return response
        res.body(game.generate_JSON());
        return true;
    }

}