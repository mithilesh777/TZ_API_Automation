package common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

    /**
     * This method is user to get project properties, it read properties from the
     * file which is passed as param name "CONFIG" If property is not found or found
     * empty it will read it from glogal.properties file, which contains default
     * properties
     *
     * @param name : name of the property
     * @return : value of the property
     */

    public static final String AUTOMATION_NAME = "automation_name";
    public static final String ENV = "env";
    public static final String BROWSER = "browser";
    public static final String TAGS = "tags";
    public static final String FEATURES = "features";
    public static final String TESTSTEPS = "testSteps";
    public static final String HANDLESALERTS = "handlesAlerts";
    public static final String LOCATOR_DIR = "locator_dir";
    public static final String TEST_DATA_DIR = "test_data_path";

    private static Map<String, String> configProp = new HashMap<>();

    private static final String tagName = "Config";

    public static void setConfigPropValue(String name, String value) {
        configProp.put(name, value);
    }

    public static String getConfigPropValues(String name) {
        InputStream inputStream = null;
        String value = "";
        try {
            value = configProp.get(name);
            if (value == null) {
                value = System.getProperty(name);
                if (value == null || value.equalsIgnoreCase("")) {

//                    LoggerUtils.getLogger(tagName).debug(name + " property not passes as mvn command");
                    Properties prop = new Properties();
                    String propFileName = System.getProperty("CONFIG");
                    if (propFileName != null && !propFileName.equals("")) {
                        inputStream = new FileInputStream(propFileName);
                        prop.load(inputStream);
                        // get the property value and print it out
                        value = prop.getProperty(name);
                    }
                    if (value == null || value.equalsIgnoreCase("")) {
//                        LoggerUtils.getLogger(tagName)
//                                .debug(name + " property not found in passed file getting it from default properties");
                        inputStream = Config.class.getClassLoader().getResourceAsStream("global.properties");
                        prop.load(inputStream);
                        value = prop.getProperty(name);
                    }
                    if (value == null || value.equalsIgnoreCase("")) {
//                        LoggerUtils.getLogger(tagName).debug(name + " property not found");
                    } else {
                        setConfigPropValue(name, value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return value;
    }

}
