package common.util;

import org.apache.log4j.PropertyConfigurator;


public class LoggerUtils {
    public static String getLogger(String className) {
        PropertyConfigurator.configure(BaseTest.class.getClassLoader().getResourceAsStream(""));
   return "";
    }
}
