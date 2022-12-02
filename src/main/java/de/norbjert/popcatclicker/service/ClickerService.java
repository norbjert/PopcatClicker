package de.norbjert.popcatclicker.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service("ClickerService")
@AllArgsConstructor
public class ClickerService {

    private final ChromeDriver driver;
    private static long totalClicks;


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anwender\\drivers\\chromedriver.exe");
        final ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("window-size=900,1080");
        options.setLogLevel(ChromeDriverLogLevel.OFF);
        ChromeDriver d = new ChromeDriver(options);

        d.setLogLevel(Level.OFF);

        ClickerService s = new ClickerService(d);



        s.spamClick();

    }


    @SneakyThrows
    public void spamClick() {

        String targetURI = "https://popcat.click/";

        driver.get(targetURI);


        WebElement element = driver.findElement(By.className("title"));


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

                Thread.sleep(2000);




                try{
                    driver.findElement(By.tagName("button")).click();
                }
                catch (ElementClickInterceptedException e){

                    String rejectbuttonurl = e.getMessage().split("<iframe src=\"")[1].split("id=sp_message_iframe_")[0];

                    driver.findElements(By.tagName("iframe")).stream()
                            .filter(webElement -> webElement.getAttribute("src").equals(rejectbuttonurl))
                            .toList().get(0).click();
                }


                System.out.println("mebis penis");
                //searchBtn.click();
            }
        }
    }
}
