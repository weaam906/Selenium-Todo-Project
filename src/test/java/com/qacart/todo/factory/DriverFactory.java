/* ------------------------------------------------------------------------
  - Date: 20th.Dec.23                                                     -
  - Auth: We'am Othman                                                    -
  - Desc: Driver factory is for setup and initializing driver environment -
  ------------------------------------------------------------------------- */

package com.qacart.todo.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {
    //Here we made the browser dynamic and determine dby the user through the terminal
    //mvn command "mvn clean test -Dbrowser = value"
    //and by System.getProperty method, the system would add the value of the property and run the selected browser
    //in case of not entering any value, the system will run the default value
    //In case of "SAFARI" you need first to open safari browser, click on develop, check Allow Remote Automation

    private WebDriver driver;
    String browser = System.getProperty("browser","CHROME");
    @Step
    public WebDriver initializeDriver(){
        switch (browser){
            case "CHROME":
                WebDriverManager.chromedriver().setup(); //Select the driver {Chrome,eagle,Firefox...}
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup(); //Select the driver {Chrome,eagle,Firefox...}
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "SAFARI":
                driver = new SafariDriver();
                break;
            default: throw new RuntimeException("This browser isn't supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000)); //Give it implicit wait to load
        driver.manage().window().maximize(); //Maximize the screen (Optional)
        return driver;
    }
}
