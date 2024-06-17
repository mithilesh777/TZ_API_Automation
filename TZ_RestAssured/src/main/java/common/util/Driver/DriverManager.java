package common.util.Driver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static ThreadLocal<AppiumDriverLocalService> appiumService = new ThreadLocal<>();
    private static ThreadLocal<String> tagName = new ThreadLocal<>();
}

