package de.norbjert.popcatclicker.config;


import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){

        /*if(System.getProperty("os.name").toLowerCase().contains("win")){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anwender\\drivers\\chromedriver.exe");
        }
        else if(System.getProperty("os.name").toLowerCase().contains("linux")){
            System.setProperty("webdriver.chrome.driver", "~/drivers/chromedriver");
        }
        else{
            //Logging.getLogger().log(Level.SEVERE, "YOUR OS IS NOT SUPPORTED");
            System.exit(-1);
        }*/
    }

    @Bean
    public ChromeDriver driver(){

        WebDriverManager.chromedriver().setup();

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--mute-audio");
        return new ChromeDriver(options);
    }
}
