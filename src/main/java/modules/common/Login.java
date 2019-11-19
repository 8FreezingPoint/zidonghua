package modules.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import uitls.Text;
import uitls.WaitUtil;

import java.util.Map;

public class Login {

    public static WebDriver login(String userName, String password,Text text) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type", "--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://admin.test.nbm2m.com/#/login");
        //输入账号
        driver.findElement(By.xpath("/html/body/div/div/div/form/div[2]/div/div/input")).sendKeys(userName);
        //输入密码
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[3]/div/div[1]/input")).sendKeys(password);
        //输入验证码
        driver.findElement(By.xpath("/html/body/div/div/div[3]/form/div[4]/div/div[1]/div[1]/div/input")).sendKeys("1234");
        //点击登录
        driver.findElement(By.xpath("/html/body/div/div/div/form/div[5]/div/button")).click();
        text.append("登入账号："+userName+"   密码："+password);
        //点掉修改密码
//        Thread.sleep(8000);
        text.append("----------------------------------------------------------");
        Thread.sleep(1000);
        String url = driver.getCurrentUrl();
        if ("http://admin.test.nbm2m.com/#/login".equals(url)) {
            try {
                String alert = driver.findElement(By.xpath("/html/body/div[2]/p")).getText();
                if (alert != null) {
                    text.append("页面提示：" + alert);
                    return driver;
                }
            } catch (Exception e) {
            }
        }
        WaitUtil.waitClick(driver,"/html/body/div[3]/div/div[1]/button/i");
        return driver;
    }

    public static void checkLogin(Text text, Integer type, Map<String,String> map) throws InterruptedException {
        WebDriver check = Login.login(map.get("userBUserName"),map.get("userBPassword"),text);
        String url = check.getCurrentUrl();
        if ("http://admin.test.nbm2m.com/#/login".equals(url)) {
            if (type == 1) {
                text.append("***验证成功***");
            } else {
                text.append("***验证失败!!!***");
            }
        } else {
            text.append("登录成功");
            if (type == 2) {
                text.append("***验证成功***");
            } else {
                text.append("***验证失败!!!***");
            }
        }
        text.append("----------------------------------------------------------").append();
        check.quit();
    }

    public static WebDriver loginGzh(String iccid,Text text) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type", "--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://web.test.nbm2m.com/");
        //输入账号
        driver.findElement(By.xpath("//*[@id=\"iccid\"]")).sendKeys(iccid);
        //点击登录
        driver.findElement(By.xpath("//*[@id=\"view\"]/div[2]/button")).click();
        text.append("登入iccid：" + iccid);
        text.append("----------------------------------------------------------");
        Thread.sleep(5000);
        return driver;
    }
}
