package de.norbjert.popcatclicker.config;


import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){


    }


    @Bean(name = "ChromiumDriver")
    public ChromiumDriver chromiumDriver(){
        WebDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--mute-audio");
        return new ChromeDriver(options);
    }

    @Bean(name = "ChromeDriver")
    public ChromeDriver chromeDriver(){
        WebDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--mute-audio");
        return new ChromeDriver(options);
    }

    @Bean
    public FirefoxDriver firefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        final FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--mute-audio");
        return new FirefoxDriver(options);
    }
}
