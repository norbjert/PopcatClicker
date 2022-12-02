package de.norbjert.popcatclicker.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service("ClickerService")
@AllArgsConstructor
public class ClickerServiceFirefox {

    private final FirefoxDriver driver;
    private static long totalClicks;


    public static void main(String[] args) {

        WebDriverManager.firefoxdriver().setup();


        //System.setProperty("webdriver.firefox.driver", "C:\\Users\\Anwender\\drivers\\chromedriver.exe");
        final FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--headless");
        options.addArguments("window-size=900,1080");
        FirefoxDriver d = new FirefoxDriver(options);

        d.setLogLevel(Level.OFF);

        ClickerServiceFirefox s = new ClickerServiceFirefox(d);



        s.spamClick();

    }


    public void spamClick() {

        String targetURI = "https://popcat.click/";

        driver.get(targetURI);


        WebElement element = driver.findElement(By.className("title"));


        int clickCounter = 0;
        while (true) {
            try {
                element.click();
                totalClicks++;
                //System.out.println("CPS: "+ currentClicksPerSec());
                System.out.println("total clicks: "+totalClicks);
            }
            catch (ElementClickInterceptedException ignored){

                /*WebElement searchBtn = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                        //ExpectedConditions.elementToBeClickable(By.xpath("//button/span[contains(., 'Reject')]"))
                        ExpectedConditions.elementToBeClickable(By.xpath("//button/span[normalize-space()='Reject']"))
                );*/
                //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Reject' and @title='Reject']"))).click();

                //WebElement e1 = driver.findElement(By.xpath("//button[normalize-space()=\"Reject\"]"));
                //driver.findElements(By.tagName("button"));
                //WebElement rejectButton = e1.stream().filter(webElement -> System.out.println(webElement.getText()));


                WebElement eeeeeeeeeeee = driver.findElement(By.xpath("//button[text()='Reject']"));
                System.out.println("mebis penis");
                //searchBtn.click();
            }
        }
    }
}
