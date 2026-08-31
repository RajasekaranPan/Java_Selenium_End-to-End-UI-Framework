package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestDataReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream =
                     TestDataReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "testdata/login-data.properties")) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "Test data file not found."
                );
            }

            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load test data.",
                    e
            );
        }
    }

    private TestDataReader() {
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}