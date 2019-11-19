package testcase;

import modules.common.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Fr23 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type", "--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        Login login = new Login();
        driver.get("http://admin.test.nbm2m.com/#/login");
//        login.login(driver,"admin","test");

        driver.get("http://admin.test.nbm2m.com/#/sys-zypackage");
        Thread.sleep(1100);
        driver.findElement(By.xpath("//*[@id=\"pane-2\"]/form/div[1]/div/div/div/input")).click();
        Thread.sleep(2500);
//        driver.findElement(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li.el-select-dropdown__item.hover")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"pane-2\"]/form/div[6]/div/button/i")).click();
        String tdName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div/div/div[2]")).getText();
        String tcName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
        System.out.println("通道名称: "+tdName);
        System.out.println("套餐名称: "+tcName);

        //修改公众号售价
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[13]/div/i[1]")).click();
        String gzhPrice = "160";
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[4]/div/div[2]/form/div/div/div[7]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[4]/div/div[2]/form/div/div/div[7]/div/div/input")).sendKeys(gzhPrice);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[4]/div/div[3]/span/button[2]/span")).click();
        System.out.println("管理员修改的公众号售价: "+gzhPrice);
        Thread.sleep(1500);
        //前往流量卡页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycard");
        //点击高级查询
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[3]/div/button/span")).click();
        //下拉框输入通道名字
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/span/div[7]/div/div/div/input")).sendKeys(tdName);
        //选择通道
        Thread.sleep(4000);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[27]/span")).click();
        //点击搜索
        driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[4]/div/button/span")).click();
        //记录卡片所属客户
        Thread.sleep(1000);
        String cardStatus = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr/td[7]/div/span")).getText();
        System.out.println("卡片所属客户: "+cardStatus);
        System.out.println("未分配".equals(cardStatus));
        if ("未分配".equals(cardStatus)) {
            //点击卡片的选择框
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr/td[1]/div/label/span/span")).click();
            //点击分配卡片
            driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[8]/div/button/span")).click();
            //点开用户下拉列表框
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[2]/form/span/span/div[2]/div/div/div/input")).sendKeys("xiaochu");
            //选择用户
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li[25]/span[1]")).click();
            //点击确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[3]/span/span/button[2]/span")).click();
        } else {
            //点击卡片的选择框
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr/td[1]/div/label/span/span")).click();
            //点击回收卡片
            driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[9]/div/button/span")).click();
            //点击确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[3]/span/span/button[2]/span")).click();
            //点击卡片的选择框
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr/td[1]/div/label/span/span")).click();
            //点击分配卡片
            driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[8]/div/button/span")).click();
            //点开用户下拉列表框
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[2]/form/span/span/div[2]/div/div/div/input")).sendKeys("xiaochu");
            //选择用户
            Thread.sleep(3500);
            driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/ul/li[25]")).click();
            //点击确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[3]/span/span/button[2]/span")).click();
        }

        //退出当前账号
        WebDriver driver2 = new ChromeDriver();
        driver2.get("http://admin.test.nbm2m.com/#/login");
//        login.login(driver2,"xiaochu","test");
        driver2.get("http://admin.test.nbm2m.com/#/sys-zypackage");

        Thread.sleep(1000);
        driver2.findElement(By.xpath("//*[@id=\"pane-2\"]/form/div[2]/div/div/input")).sendKeys(tcName);

        driver2.findElement(By.xpath("//*[@id=\"pane-2\"]/form/div[6]/div/button")).click();

        String str = driver2.findElement(By.xpath("//*[@id=\"pane-sys-zypackage\"]/div/div/div/div[2]/div[3]/table/tbody/tr/td[5]/div/span")).getText();
        String dlPrice = str.substring(0,str.indexOf("/"));
        System.out.println("代理的成本价: "+dlPrice);
        System.out.println("代理的成本价是否等于管理员修改的公众号售价: "+gzhPrice.equals(dlPrice));
    }
}
