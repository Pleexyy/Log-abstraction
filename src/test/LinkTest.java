import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {

    @Test
    @DisplayName("Ensure URL exists using the HEAD HTTP method")
    void testLink() throws IOException {
        URL url = new URL("https://github.com/sasa27/ConversationExtraction/tree/theNewOngoing/RESULTS/Final");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");

        int responseCode = huc.getResponseCode();

        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
    }

}
