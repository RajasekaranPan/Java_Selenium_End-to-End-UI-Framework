package config;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

	private static final Properties properties = new Properties();

	static {
		loadProperties();
	}

	private ConfigReader() {
	}

	private static void loadProperties() {

		String environment = System.getProperty("env", FrameworkConstants.DEFAULT_ENVIRONMENT);

		String filePath = FrameworkConstants.CONFIG_DIRECTORY + FrameworkConstants.CONFIG_FILE_PREFIX + environment
				+ FrameworkConstants.CONFIG_FILE_EXTENSION;

		try (InputStream inputStream = new FileInputStream(filePath)) {

			properties.load(inputStream);

		} catch (IOException e) {

			throw new RuntimeException("Unable to load configuration file: " + filePath, e);
		}
	}

	public static String get(String key) {

		String value = properties.getProperty(key);

		if (value == null) {

			throw new RuntimeException("Configuration property not found: " + key);
		}

		return value.trim();
	}

	public static String getEnvironment() {
		return get("environment");
	}

	public static String getBrowser() {
		return System.getProperty("browser", get("browser"));
	}

	public static boolean isHeadless() {
		return Boolean.parseBoolean(System.getProperty("headless", get("headless")));
	}

	public static String getBaseUrl() {
		return get("baseUrl");
	}

	public static int getExplicitWait() {
		return Integer.parseInt(get("explicitWait"));
	}

	public static int getPageLoadTimeout() {
		return Integer.parseInt(get("pageLoadTimeout"));
	}

	public static boolean isWindowMaximize() {
		return Boolean.parseBoolean(get("windowMaximize"));
	}

	public static int getImplicitWait() {
		return Integer.parseInt(get("implicitWait"));
	}

	public static String getExecution() {
		System.out.println("Execution Mode: " + get("execution"));
		return System.getProperty("execution", get("execution"));
		
	}
	
	
	public static boolean isParallel() {
	    return Boolean.parseBoolean(
	            System.getProperty("parallel", get("parallel"))
	    );
	}

	public static int getThreadCount() {
	    return Integer.parseInt(
	            System.getProperty("threadCount", get("threadCount"))
	    );
	}
	
	public static String getDbUrl() {
	    return System.getProperty("db.url", get("db.url"));
	}

	public static String getDbUsername() {
	    return  System.getProperty("db.username", get("db.username"));
	}

	public static String getDbPassword() {
	    return  System.getProperty("db.password", get("db.password"));
	}

	public static int getDbMaximumPoolSize() {
	    return Integer.parseInt(
	    		 System.getProperty("db.pool.maximum-size", get("db.pool.maximum-size"))
	    );
	}

	public static int getDbMinimumIdle() {
	    return Integer.parseInt(
	    		 System.getProperty("db.pool.minimum-idle", get("db.pool.minimum-idle"))
	    );
	}

	public static long getDbConnectionTimeout() {
	    return Long.parseLong(
	    		 System.getProperty("db.pool.connection-timeout", get("db.pool.connection-timeout"))
	    );
	}

	public static long getDbIdleTimeout() {
	    return Long.parseLong(
	    		 System.getProperty("db.pool.idle-timeout", get("db.pool.idle-timeout"))
	    );
	}

	public static long getDbMaxLifetime() {
	    return Long.parseLong(
	    		System.getProperty("db.pool.max-lifetime", get("db.pool.max-lifetime"))
	    );
	}

}