import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class UnitTests {
    @BeforeClass
    public static void beforeClass(){
        Main.main(null);
    }

    @AfterClass
    public static void afterClass(){
        Spark.stop();
    }

    @Test
    public void newContactTest(){
        TestRes result = request("POST", "/contacts?name=JaneDoe&address=1234MainStreet&email=jdoe@gmail.com&phoneNumber=1234567890");
        Map<String, String> json = result.json();
        assertEquals(200, result.status);
        assertEquals("JaneDoe", json.get("name"));
        assertEquals("jdoe@gmail.com", json.get("email"));
        assertEquals("1234MainStreet", json.get("address"));
        assertEquals("1234567890", json.get("phoneNumber"));

    }

    private TestRes request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestRes(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestRes {

        public final String body;
        public final int status;

        public TestRes(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }
}
