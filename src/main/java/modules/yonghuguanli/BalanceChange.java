package modules.yonghuguanli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class BalanceChange {

    public static void addBalance(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-agentuser");
        text.append().append("账号：" + map.get("adminUserName") + "  在【客户管理页面】 准备对用户余额进行加减款 ...");
        if (type == 1) {
            text.append("本次操作：代理：" + map.get("userAUserName") + "【加款】" + map.get("addMoney") + "元").append();
        } else if (type == 2) {
            text.append("本次操作：代理：" + map.get("userAUserName") + "【减款】" + map.get("addMoney") + "元").append();
        }
        //输入代理用户名
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/form/div[4]/div/button")).click();
        //获取第一条数据用户名
        Thread.sleep(2000);
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        //余额
        String beforeAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr/td[6]/div/span")).getText();
        text.append("查询到的用户名：" + findUserName);
        text.append("查询到的用户余额：" + beforeAddBalance).append();
        map.put("beforeAddBalance",beforeAddBalance);
        if (!findUserName.equals(map.get("userAUserName"))) {
            text.append("要操作的用户与查询到的用户不一致！！！");
            throw new Exception("要操作的用户与查询到的用户不一致！！！");
        }
        //点击加减款
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[4]/div[2]/table/tbody/tr/td[12]/div/i[4]")).click();
        //输入验证码
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[2]/div/div[2]/form/div[3]/div/div/input")).sendKeys(map.get("code"));
        //加减款金额
        if (type == 1) {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[2]/div/div[2]/form/div[4]/div/div[1]/input")).sendKeys(map.get("addMoney"));
        } else {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[2]/div/div[2]/form/div[4]/div/div[1]/input")).sendKeys("-" + map.get("addMoney"));
        }
        //备注
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[2]/div/div[2]/form/div[5]/div/div[1]/textarea")).sendKeys("加减款测试");
        //确定
        driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[2]/div/div[3]/span/button[2]")).click();
        Thread.sleep(8000);
        //操作后的金额
        String afterAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-agentuser\"]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr/td[6]/div/span")).getText();
        text.append("操作后的用户余额：" + afterAddBalance).append();
        map.put("afterAddBalance",afterAddBalance);
        if (type == 1) {
            if (((Double.valueOf(beforeAddBalance) + Double.valueOf(map.get("addMoney"))) == Double.valueOf(afterAddBalance))) {
                text.append(">>>加款后用余额正确");
            } else {
                text.append("***加款后用余额不正确***");
            }
        } else if (type == 2) {
            if (((Double.valueOf(beforeAddBalance) - Double.valueOf(map.get("addMoney"))) == Double.valueOf(afterAddBalance))) {
                text.append(">>>减款后用余额正确");
            } else {
                text.append("***减款后用余额不正确***");
            }
        }
        text.append("----------------------------------------------------------");
    }

    public static void topUp(WebDriver driver, Text text, Map<String,String> map) throws Exception {
        //进入流量卡页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycard");
        text.append("账号：" + map.get("userAUserName") + "  在【流量卡页面】 准备对卡片进行充值 ...").append();
        //获取第一条数据
        Thread.sleep(3000);
        //iccid
        String iccid = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div/a")).getText();
        //所属客户
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/span")).getText();
        text.append("要操作的iccid：" + iccid);
        text.append("该卡所属客户：" + findUserName).append();
        //选择该卡
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div/label/span/span")).click();
        //充值续费
        driver.findElement(By.xpath("//*[@id=\"pane-1\"]/form/div[10]/div/button")).click();
        //选择套餐
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div[2]/div[3]/table/tbody/tr[1]/td[1]/div/label/span[1]/span")).click();
        //获取套餐价格
        String price = driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div[2]/div[3]/table/tbody/tr[1]/td[6]/div")).getText();
        text.append("要充值的套餐价格：" + price).append();
        //获取账户余额和订单金额
        String beforeAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div[1]/div[2]/div/div")).getText();
        String totalMoney = driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div[1]/div[3]/div/div")).getText();
        map.put("beforeAddBalance",beforeAddBalance);
        map.put("totalMoney",totalMoney);
        text.append("当前账户余额：" + beforeAddBalance).append();
        text.append("该订单金额：" + totalMoney);
        //下一步
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[3]/span/span/div/button")).click();
        //输入提现密码
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[2]/form/span/div/form/div[2]/div/div[1]/input")).sendKeys(map.get("txPassword"));
        //验证码
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[2]/form/span/div/form/div[4]/div/div[1]/input")).sendKeys(map.get("code"));
        //提交
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycard\"]/div/div/div/div[4]/div/div[3]/span/span/div/button[2]")).click();
        //点掉知道了
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button")).click();
        text.append().append(">>>充值完成").append("----------------------------------------------------------");

    }
}
