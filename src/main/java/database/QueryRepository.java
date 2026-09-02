package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class QueryRepository {

    private static final String QUERY_FILE =
            "queries.properties";

    private static final Properties QUERIES =
            new Properties();

    static {
        loadQueries();
    }

    private QueryRepository() {
    }

    private static void loadQueries() {

        try (InputStream inputStream =
                     QueryRepository.class
                             .getClassLoader()
                             .getResourceAsStream(QUERY_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        QUERY_FILE + " not found in classpath"
                );
            }

            QUERIES.load(inputStream);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load " + QUERY_FILE,
                    e
            );
        }
    }

    public static String getQuery(String key) {

        String query = QUERIES.getProperty(key);

        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException(
                    "SQL query not found for key: " + key
            );
        }

        return query.trim();
    }
}