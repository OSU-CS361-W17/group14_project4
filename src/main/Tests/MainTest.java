import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.awaitInitialization;


/**
 * Created by michaelhilton on 1/26/17.
 */
class MainTest {


    @BeforeAll
    public static void beforeClass() {
        Main.main(null);
        awaitInitialization();
    }

    @AfterAll
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void testGetModel() {
        Game game = new Game();
        TestResponse res = request("GET", "/model");
        assertNotEquals(null, res.body);
    }

    @Test
    public void testPlaceShip() {
        TestResponse res = request("POST", "/placeShip/aircraftCarrier/1/1/horizontal");
        TestResponse res2 = request("POST", "/placeShip/dinghy/1/2/horizontal");
        TestResponse res3 = request("POST", "/placeShip/submarine/1/3/horizontal");
        TestResponse res4 = request("POST", "/placeShip/battleship/1/4/horizontal");
        TestResponse res5 = request("POST", "/placeShip/clipper/5/5/vertical");
        assertNotEquals(null,res.body);
    }

    @Test
    public void testSetDifficulty() {
        TestResponse res = request("POST", "/setDifficulty/hard");
        assertNotEquals(null, res.body);
    }

    @Test
    public void testFire() {
        TestResponse res = request("POST", "/fire/2/2");
        TestResponse changeDiff = request("POST", "/setDifficulty/easy");
        TestResponse res2 = request("POST", "/fire/3/4");
        assertNotEquals(null, res.body);
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }


}
