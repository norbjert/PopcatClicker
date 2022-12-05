package de.norbjert.popcatclicker.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

@Service("ClickerService")
public class ClickerService {


    private final RemoteWebDriver driver;
    private static long totalClicks = 0;
    private static long startTime = 0;


    @Autowired
    public ClickerService(ChromeDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    public void spamClick() {

        startTime = System.currentTimeMillis();

        String targetURI = "https://popcat.click/";

        driver.get(targetURI);

        WebElement element = driver.findElement(By.className("title"));

        while (!Thread.currentThread().isInterrupted()) {
            try {
                element.click();
                totalClicks++;
                printClicks();
            } catch (ElementClickInterceptedException ignored) {

                new WebDriverWait(driver, Duration.ofSeconds(5));

                WebElement iframe = driver.findElement(By.id("sp_message_iframe_681957"));

                driver.switchTo().frame(iframe);

                driver.findElements(By.tagName("button")).stream().filter(webElement -> webElement.getText().equals("Reject")).toList().get(0).click();

            }
        }
    }
    private static void printClicks(){
        if(totalClicks > 10000){
            if(totalClicks % 1000 == 0){
            System.out.println("Total clicks: " + totalClicks + " with an average of " + calcClicksPerSec() + " clicks per second");}

        }
        else if(totalClicks > 500){
            if(totalClicks % 100 == 0) {
                System.out.println("Total clicks: " + totalClicks + " with an average of " + calcClicksPerSec() + " clicks per second");
            }
        }
        else {
            System.out.println("Total clicks: " + totalClicks + " with an average of " + calcClicksPerSec() + " clicks per second");
        }
    }

    private static long calcClicksPerSec(){
        long totalSeconds = (System.currentTimeMillis() - startTime) / 1000;
        return totalClicks / (totalSeconds+1);
    }

}
