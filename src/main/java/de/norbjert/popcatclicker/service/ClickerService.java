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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

@Service("ClickerService")
@AllArgsConstructor
public class ClickerService {

    private final ChromeDriver driver;
    private static long totalClicks;
    private static long startTime;


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anwender\\drivers\\chromedriver.exe");
        final ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.setLogLevel(ChromeDriverLogLevel.OFF);
        ChromeDriver d = new ChromeDriver(options);

        d.setLogLevel(Level.OFF);

        ClickerService s = new ClickerService(d);

        s.spamClick();
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
