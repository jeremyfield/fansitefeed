import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import java.io.IOException;
import java.io.InputStream;

public class Config {

    private static final JsonReaderFactory READER_FACTORY = Json.createReaderFactory(null);

    public static JsonObject getConfig(String path) throws IOException {
        try(InputStream inputStream = Config.class.getResourceAsStream(path);
            JsonReader reader = READER_FACTORY.createReader(inputStream)) {
            return reader.readObject();
        }
    }
}
