package modules.caiwuguanli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class OrderCheck {
    public static void topUpOrder(WebDriver driver, Text text, Integer type, Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + map.get("userAUserName") + "  在【提现管理页面】 查看当前余额 ...");
        //查询
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        }
        Thread.sleep(2000);
        //获取当前数据
        if (type == 1) {
            String beforeAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("beforeFindAddBalance", beforeAddBalance);
            text.append().append("加减款前账户余额: " + beforeAddBalance + " 元");
        } else if (type == 2) {
            String afterAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("afterFindAddBalance", afterAddBalance);
            text.append().append("加减款后账户余额: " + afterAddBalance + " 元");
        } else if (type == 0) {
            String dlBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("dlBalance", dlBalance);
            text.append().append("代理的账户余额: " + dlBalance + " 元");
        }
        text.append("----------------------------------------------------------");
    }

    public static void getInfo(WebDriver driver, Text text, Integer type, Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + map.get("userAUserName") + "  在【提现管理页面】 查看当前余额 ...");
        //查询
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        }
        Thread.sleep(2000);
        //获取当前数据
        if (type == 1) {
            String beforeAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("beforeFindAddBalance", beforeAddBalance);
            text.append().append("加减款前账户余额: " + beforeAddBalance + " 元");
        } else if (type == 2) {
            String afterAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("afterFindAddBalance", afterAddBalance);
            text.append().append("加减款后账户余额: " + afterAddBalance + " 元");
        } else if (type == 0) {
            String dlBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("dlBalance", dlBalance);
            text.append().append("代理的账户余额: " + dlBalance + " 元");
        }
        text.append("----------------------------------------------------------");
    }
}
