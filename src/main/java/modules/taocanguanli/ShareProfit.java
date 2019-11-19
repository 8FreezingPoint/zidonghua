package modules.taocanguanli;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class ShareProfit {
    public static void get(WebDriver driver, Text text,Integer type, Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zyshareprofit");
        String userName = "";
        if (type == 1) {
            text.append("账号：" + map.get("userAUserName") + "  在【分润管理页面】 获取对下级代理" + map.get("userBUserName") + "的分润信息 ...");
            userName = map.get("userAUserName");
        } else if (type == 2) {
            text.append("账号：" + map.get("adminUserName") + "  在【分润管理页面】 获取对下级代理" + map.get("userAUserName") + "的分润信息 ...");
            userName = map.get("adminUserName");
        }
        //输入用户名
        Thread.sleep(1500);
        if (type == 1) {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userBUserName"));
        } else if (type == 2) {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        }
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/form/div[2]/div/button")).click();
        //打开套餐列表
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[1]/div[4]/div[2]/table/tbody/tr/td[5]/div/i[1]")).click();
        //运营商下拉列表
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/form/div[1]/div/div/div/input")).click();
        //电信
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
        //通道下拉列表
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/form/div[2]/div/div/div/input")).click();
        //分润专用
        Thread.sleep(500);
        if (type == 1) {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li[3]")).click();
        } else if (type == 2) {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li[98]")).click();
        }
        //套餐名列表
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/form/div[3]/div/div/div/input")).click();
        //第一个
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/ul/li[1]")).click();
        //搜索
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/form/div[6]/div/button")).click();
        //获取套餐名
        Thread.sleep(1000);
        String tcName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
        //该代理成本价
        String dlCost = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[8]/div")).getText();
        dlCost = String.valueOf(Double.valueOf(dlCost));
        //该代理对下级的代理价
        String dlPrice = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[9]/div")).getText();
        dlPrice = String.valueOf(Double.valueOf(dlPrice));
        //该代理利润
        String dlProfit = String.valueOf(Double.valueOf(dlPrice) - Double.valueOf(dlCost));
        if (type == 1) {
            map.put("userCost",dlCost);
            map.put("userPrice",dlPrice);
            map.put("userProfit",dlProfit);
        } else if (type == 2) {
            if (!tcName.equals(map.get("tcName"))) {
                throw new Exception("管理员与代理获取的套餐不一致!");
            }
            map.put("adminCost",dlCost);
            map.put("adminPrice",dlPrice);
            map.put("adminProfit",dlProfit);
        }
        map.put("tcName",tcName);
        //关闭窗口
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[1]/button")).click();
        text.append("获取到的套餐名：" + tcName);
        text.append(userName + "的套餐成本价：" + dlCost);
        text.append(userName + "的套餐代理价：" + dlPrice);
        text.append(userName + "的套餐利润：" + dlProfit);
        text.append("----------------------------------------------------------").append();
    }

    public static void gzhOrder(WebDriver driver, Text text, Map<String,String> map) throws Exception {
        text.append().append("iccid：" + map.get("iccid") + "  在【公众号】 准备订购套餐 ...");
        //获取订购前余额
        String beforeCardBalance = driver.findElement(By.xpath("//*[@id=\"view\"]/div[1]/div[4]/div[2]/div/span")).getText();
        beforeCardBalance = beforeCardBalance.substring(beforeCardBalance.indexOf("/")+1);
        beforeCardBalance = String.valueOf(Double.valueOf(beforeCardBalance));
        text.append("订购套餐前卡余额：" + beforeCardBalance);
        map.put("beforeCardBalance",beforeCardBalance);
        //点击充值中心
        driver.findElement(By.xpath("//*[@id=\"view\"]/ul/li[1]")).click();
        //套餐名
        Thread.sleep(3000);
        String willTcName = driver.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div[1]/p")).getText();
        //套餐价格
        String orderPrice = driver.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div[1]/span")).getText();
        orderPrice = orderPrice.substring(orderPrice.indexOf("￥")+1);
        orderPrice = String.valueOf(Double.valueOf(orderPrice));
        text.append().append("要订购的套餐名称：" + willTcName);
        text.append("要订购的套餐价格：" + orderPrice).append();
        if (!willTcName.equals(map.get("tcName"))) {
            throw new Exception("要订购的套餐与分润套餐不一致!");
        }
        map.put("orderPrice",orderPrice);
        //选择套餐
        driver.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div[1]")).click();
        //立即充值
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"view\"]/div[4]/div[2]/div/div/ul/button")).click();
        //获取首页的卡余额
        Thread.sleep(6000);
        String afterCardBalance = driver.findElement(By.xpath("//*[@id=\"view\"]/div[1]/div[4]/div[2]/div/span")).getText();
        afterCardBalance = afterCardBalance.substring(afterCardBalance.indexOf("/")+1);
        afterCardBalance = String.valueOf(Double.valueOf(afterCardBalance));
        text.append("订购套餐后卡余额：" + afterCardBalance);
        map.put("afterCardBalance",afterCardBalance);
        text.append("----------------------------------------------------------").append();
    }
}
