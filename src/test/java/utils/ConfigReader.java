package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // This static block runs exactly once when the framework starts
    static {
        try {
            // Find the file
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            // Load it into Java's Properties memory
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not find the config.properties file!");
        }
    }

    // A simple method to grab a specific value by its key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}