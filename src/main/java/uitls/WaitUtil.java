package uitls;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    public static void waitClick(WebDriver driver,String xpath) {
        WebDriverWait wait = new WebDriverWait(driver,15,100);
        wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver driver) {
                return driver.findElement(By.xpath(xpath));
            }
        }).click();
    }

    public static String waitText(WebDriver driver,String xpath) {
        WebDriverWait wait = new WebDriverWait(driver,10,100);
        String body = wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver driver) {
                return driver.findElement(By.xpath(xpath));
            }
        }).getText();
        return body;
    }
}
