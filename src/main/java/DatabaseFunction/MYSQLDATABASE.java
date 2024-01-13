package DatabaseFunction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MYSQLDATABASE {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = MYSQLDATABASE.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw  new RuntimeException("Unable to find config file: " + PROPERTIES_FILE);

            }
            // load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseURL() {
        return properties.getProperty("db.url");
    }

    public static String getDatabaseUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDatabasePassword() {
        return properties.getProperty("db.password");
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
