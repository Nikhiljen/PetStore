package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class propertiesFile {
	private static Properties prop = new Properties();

	// Static block to load the properties only once when the class is loaded
	static {
		try {
			File filePath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "routes.properties");
			try (FileInputStream file = new FileInputStream(filePath)) {
				prop.load(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load properties file.");
		}
	}

	// Getter to access properties
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
