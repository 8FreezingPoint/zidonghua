package modules.yonghuguanli;

import modules.common.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import uitls.Text;
import uitls.Utils;

import java.util.HashMap;
import java.util.Map;

public class CustomerChange {

    public static void changeStatus(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-agentuser");
        text.append("账号：" + map.get("adminUserName") + "  在【客户管理页面】 准备开启/冻结代理账号 ...");
        if (type == 1) {
            text.append("本次操作：【冻结】代理：" + map.get("userBUserName")).append();
        } else if (type == 2) {
            text.append("本次操作：【开启】代理：" + map.get("userBUserName")).append();
        }
        //输入代理用户名
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).sendKeys(map.get("userBUserName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[4]/div/button")).click();
        //获取第一条数据用户名
        Thread.sleep(1500);
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        text.append("查询到的用户名：" + findUserName).append();
        //点击状态开关
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr/td[10]/div/div")).click();
        } catch (Exception e) {
            System.out.println("状态开关出错了");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr/td[10]/div/div")).click();
        }
        //获取当前状态
        Thread.sleep(1000);
        String str = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/p")).getText();
        String status = Utils.subString(str,"[","]");
        if ("禁用".equals(status) && type == 1) {
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
        } else if ("开启".equals(status) && type == 1) {
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[1]")).click();
        } else if ("开启".equals(status) && type == 2) {
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
        } else if ("禁用".equals(status) && type == 2) {
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[1]")).click();
        } else {
            return;
        }
        text.append("操作成功");
    }

    public static void changePassword(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-agentuser");
        text.append("账号：" + map.get("adminUserName") + "  在【客户管理页面】 准备重置代理登录/提现密码 ...");
        if (type == 1) {
            text.append("本次操作：重置代理：" + map.get("userBUserName") + "登录密码为：" + map.get("userBPassword")).append();
        } else if (type == 2) {
            text.append("本次操作：重置代理：" + map.get("userBUserName") + "提现密码为：" + map.get("txPassword")).append();
        }
        //输入代理用户名
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).sendKeys(map.get("userBUserName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[4]/div/button")).click();
        //获取第一条数据用户名
        Thread.sleep(2000);
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        text.append("查询到的用户名：" + findUserName);
        if (type == 1) {
            //点击重置密码
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[4]/div[2]/table/tbody/tr/td[12]/div/i[1]")).click();
            //输入密码
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[3]/div/div[2]/form/div[1]/div/div/input")).clear();
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[3]/div/div[2]/form/div[1]/div/div/input")).sendKeys(map.get("userBPassword"));
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[3]/div/div[2]/form/div[2]/div/div[1]/input")).clear();
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[3]/div/div[2]/form/div[2]/div/div[1]/input")).sendKeys(map.get("userBPassword"));
            //确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[3]/div/div[3]/span/button[2]")).click();
            text.append("重置登录密码成功");
        } else if (type == 2) {
            //点击重置提现密码
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[4]/div[2]/table/tbody/tr/td[12]/div/i[2]")).click();
            Thread.sleep(500);
            //确定
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
            text.append("重置提现密码成功");
        }
        text.append("----------------------------------------------------------").append();
    }

    public static void updatePassword(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        text.append().append("账号：" + map.get("userBUserName") + "准备【修改密码】......");
        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[4]/li/div/span"))).perform();
        if (type == 1) {
            text.append("本次操作：修改提现密码：" + map.get("newTxPassword")).append();
            //点击修改提现密码
            Thread.sleep(1000);
            action.moveToElement(driver.findElement(By.cssSelector("li.el-dropdown-menu__item:nth-child(2)"))).perform();
            driver.findElement(By.cssSelector("li.el-dropdown-menu__item:nth-child(2)")).click();
            //输入新提现密码
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div[1]/input")).sendKeys(map.get("newTxPassword"));
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[3]/div/div[1]/input")).sendKeys(map.get("newTxPassword"));
            //验证码
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[5]/div/div[1]/input")).sendKeys(map.get("code"));
            //确定
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/span/button[2]")).click();
            Thread.sleep(1000);
            text.append("修改提现密码成功");
        } else if (type == 2) {
            text.append("本次操作：修改登录密码：" + map.get("newPassword")).append();
            //点击修改登录密码
            Thread.sleep(1000);
            action.moveToElement(driver.findElement(By.cssSelector("li.el-dropdown-menu__item:nth-child(3)"))).perform();
            driver.findElement(By.cssSelector("li.el-dropdown-menu__item:nth-child(3)")).click();
            //输入新登录密码
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div[1]/input")).sendKeys(map.get("newPassword"));
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[3]/div/div[1]/input")).sendKeys(map.get("newPassword"));
            //验证码
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[5]/div/div[1]/input")).sendKeys(map.get("code"));
            //确定
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/span/button[2]")).click();
            Thread.sleep(1000);
            text.append("修改登录密码成功").append();
        }
    }

    public static void checkPassword(Text text, Integer type, Map<String,String> map) throws Exception {
        text.append().append("账号：" + map.get("userBUserName") + "在【登录页面】准备验证登录......");
        Map<String,String> params = new HashMap<>();
        params.put("userBUserName",map.get("userBUserName"));
        if (type == 2) {
            text.append("本次操作：验证【原密码】"+ map.get("newPassword") + "可用");
            params.put("userBPassword",map.get("newPassword"));
            Login.checkLogin(text,2,params);
        } else if (type == 1) {
            text.append("本次操作：验证【被重置的密码】" + map.get("userBPassword") + "可用");
            params.put("userBPassword",map.get("userBPassword"));
            Login.checkLogin(text,2,params);
        }
    }
}
