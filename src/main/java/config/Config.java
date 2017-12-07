package config;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;

public class Config {

    private static final JsonReaderFactory READER_FACTORY = Json.createReaderFactory(null);

    public static JsonObject getConfig(String path) {
        try(InputStream inputStream = Config.class.getResourceAsStream(path);
            JsonReader reader = READER_FACTORY.createReader(inputStream)) {
            return reader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
