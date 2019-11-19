package testcase;

import modules.common.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import uitls.TestFile;
import uitls.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fr2 {

    public static void main(String[] args) throws InterruptedException, IOException {

        Text text = new Text();
        TestFile file = new TestFile("冻结重置");

        //新建文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        date+="分润测试结果报告";
        File f= new File("D:"+date+".txt");
        Writer out = null;
        out = new FileWriter(f,false);
        String str = "开始测试分润\n测试账号:admin\n测试账号:xiaochu\n卡号:8986031741206923598\n\n---------------------------------------------------\n\n";
        Boolean flag = true;
        try {
            //登录代理xiaochu账号，密码test
            WebDriver driver = Login.login("zidonghuacesi1","test",text);

            //进入代理号xiaochu的提现管理页面
            driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
            //获取代理号xiaochu的账户余额
            Thread.sleep(1000);
            String dlBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
            dlBalance = String.valueOf(Double.valueOf(dlBalance));
            //进入分润管理
            driver.get("http://admin.test.nbm2m.com/#/sys-zyshareprofit");
            //打开套餐列表
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[1]/div[4]/div[2]/table/tbody/tr/td[5]/div/i[1]")).click();
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

            str+="  代理测试套餐名:"+tcName+"\n\n";
            str+="  代理的账户余额:"+dlBalance+"\n\n";
            str+="  代理的套餐成本价:"+dlCost+"\n\n";
            str+="  代理的套餐代理价:"+dlPrice+"\n\n";
            str+="  代理的套餐利润:"+dlProfit+"\n\n---------------------------------------------\n\n";

            //登录admin账号
            WebDriver driver1 = Login.login("kefu","180301",text);
            //进入分润管理页面
            Thread.sleep(1000);
            driver1.get("http://admin.test.nbm2m.com/?#/sys-zyshareprofit");
            //选择每页显示100条
            Thread.sleep(2000);
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[2]/span[2]/div/div/input")).click();
            Thread.sleep(2000);
            driver1.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[4]")).click();
            //打开对xiaochu的套餐列表
            Thread.sleep(2000);
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[1]/div[4]/div[2]/table/tbody/tr[21]/td[5]/div/i[1]")).click();
            //获取管理员的套餐名
            Thread.sleep(1000);
            String tcName1 = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
            //管理员成本价
            String adCost = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[8]/div")).getText();
            adCost = String.valueOf(Double.valueOf(adCost));
            //管理员对下级的代理价
            String adPrice = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[9]/div")).getText();
            adPrice = String.valueOf(Double.valueOf(adPrice));
            //管理员利润
            String adProfit = String.valueOf(Double.valueOf(adPrice) - Double.valueOf(adCost));

            str+="  管理员测试套餐名:"+tcName1+"\n\n";
            str+="  管理员的套餐成本价:"+adCost+"\n\n";
            str+="  管理员的套餐代理价:"+adPrice+"\n\n";
            str+="  管理员的套餐利润:"+adProfit+"\n\n---------------------------------------------\n\n";

            //打开公众号
            WebDriver driver2 = new ChromeDriver();
            driver2.get("http://xiaoming-web.iot.nbm2m.com/");
            //输入卡号8986031741206923598
            driver2.findElement(By.xpath("//*[@id=\"iccid\"]")).sendKeys("8986031741206923598");
            //点击查询按钮
            driver2.findElement(By.xpath("//*[@id=\"view\"]/div[2]/button")).click();
            //获取充值前的用户余额
            Thread.sleep(1000);
            String beforeBalance = driver2.findElement(By.xpath("//*[@id=\"view\"]/div[1]/div[3]/span")).getText();
            beforeBalance = beforeBalance.substring(beforeBalance.indexOf("￥")+1);
            beforeBalance = String.valueOf(Double.valueOf(beforeBalance));
            str+="  订购套餐前用户卡余额:"+beforeBalance+"\n\n";
            //进入充值管理
            driver2.findElement(By.xpath("//*[@id=\"view\"]/ul/li[1]/a")).click();
            //获取要订购的套餐名字
            Thread.sleep(1000);
            String tcName2 = driver2.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div/ul/li[1]/div[1]/p")).getText();
            str+="  订购的的套餐名称:"+tcName2+"\n\n";
            //获取要订购的套餐金额
            String tcPrice = driver2.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div/ul/li[1]/div[2]")).getText();
            tcPrice = tcPrice.substring(0,tcPrice.indexOf("元"));
            tcPrice = String.valueOf(Double.valueOf(tcPrice));
            str+="  订购的的套餐售价:"+tcPrice+"\n\n";
            //选中套餐
            Thread.sleep(1000);
            driver2.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/div/div/ul/li[1]/div[2]")).click();
            //点击立即充值
            driver2.findElement(By.xpath("//*[@id=\"view\"]/div[3]/div/div[1]/button")).click();
            Thread.sleep(5000);

            //关闭代理号下分润管理中的套餐列表页面
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[1]/button/i")).click();
            //进入代理号xiaochu的充值订单页面
            driver.get("http://admin.test.nbm2m.com/#/sys-zyorders");
            //点击搜索按钮更新数据
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zyorders\"]/div/div/div/form/div[10]/div/button")).click();
            //获取刚刚订单的佣金
            Thread.sleep(2000);
            String dlNewProfit = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyorders\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[12]/div")).getText();
            dlNewProfit = String.valueOf(Double.valueOf(dlNewProfit));
            str+="  购买的套餐该代理获取到佣金:"+dlNewProfit+"\n";
            if (dlProfit.equals(dlNewProfit)) {
                str+="  代理xiaochu的[财务管理]-[充值订单页面]分润情况正常"+"\n\n";
            } else {
                str+="  代理xiaochu的[财务管理]-[充值订单页面]分润情况不正常"+"\r\n\r\n";
            }
            //进入代理号xiaochu的提现管理页面
            driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
            //点击刷新按钮更新数据
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
            Thread.sleep(1000*60);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
            //获取代理号xiaochu获取分润后的账户余额
            Thread.sleep(1000);
            String dlNewBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
            dlNewBalance = String.valueOf(Double.valueOf(dlNewBalance));
            dlBalance = String.valueOf(Double.valueOf(dlBalance)+Double.valueOf(dlProfit));
            dlBalance = String.valueOf(Double.valueOf(dlBalance));
            str+="  获取分润后该代理的账户余额:"+dlNewBalance+"\n";
            if (dlBalance.equals(dlNewBalance)) {
                str+="  代理xiaochu的[财务管理]-[提现管理页面]余额情况正常"+"\n\n";
            } else {
                str+="  代理xiaochu的[财务管理]-[提现管理页面]余额情况不正常"+"\r\n\r\n";
            }

            //关闭管理员下分润管理中的套餐列表页面
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyshareprofit\"]/div/div/div/div[3]/div[1]/div/div[1]/button/i")).click();
            //进入管理员的充值订单页面
            driver1.get("http://admin.test.nbm2m.com/?#/sys-zyorders");
            //点击搜索按钮更新数据
            Thread.sleep(1000);
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyorders\"]/div/div/div/form/div[11]/div/button")).click();
            //获取刚刚订单的佣金
            Thread.sleep(2000);
            String adNewProfit = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyorders\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[12]/div")).getText();
            adNewProfit = String.valueOf(Double.valueOf(adNewProfit));
            str+="  购买的套餐管理员获取到佣金:"+adNewProfit+"\n";
            if (adProfit.equals(adNewProfit)) {
                str+="  管理员admin的[财务管理]-[充值订单页面]分润情况正常"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[充值订单页面]分润情况不正常"+"\r\n\r\n";
            }
            //进入管理员的注册客户余额管理页面
            driver1.get("http://admin.test.nbm2m.com/?#/sys-zyuserrecharge");
            //输入卡号
            Thread.sleep(1000);
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/form/div[3]/div/div/input")).sendKeys("8986031741206923598");
            //点击查询
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/form/div[6]/div/button")).click();
            //获取该页用户交易前余额
            Thread.sleep(1500);
            String pageBeforeBalance = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr[1]/td[4]/div/span")).getText();
            pageBeforeBalance = String.valueOf(Double.valueOf(pageBeforeBalance));
            str+="  客户余额管理页面用户交易前余额:"+pageBeforeBalance+"\n\n";
            //获取该页用户交易金额
            String pagePrice = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr[1]/td[5]/div")).getText();
            pagePrice = pagePrice.substring(pagePrice.indexOf("-")+1);
            pagePrice = String.valueOf(Double.valueOf(pagePrice));
            str+="  客户余额管理页面用户交易金额:"+pagePrice+"\n\n";
            //获取该页用户交易后余额
            String pageAfterBalance = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr[1]/td[6]/div")).getText();
            pageAfterBalance = String.valueOf(Double.valueOf(pageAfterBalance));
            str+="  客户余额管理页面用户交易后余额:"+pageAfterBalance+"\n\n";
            if (adProfit.equals(adNewProfit)) {
                str+="  管理员admin的[财务管理]-[充值订单页面]分润情况正常"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[充值订单页面]分润情况不正常"+"\r\n\r\n";
            }
            if (tcPrice.equals(pagePrice)) {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]交易金额与公众号价格一致"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]交易金额与公众号价格不一致"+"\n\n";
            }
            if (beforeBalance.equals(pageBeforeBalance)) {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]用户交易前余额与公众号用户交易前余额一致"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]用户交易前余额与公众号用户交易前余额不一致"+"\n\n";
            }
            String afterBalance = String.valueOf(Double.valueOf(beforeBalance)-Double.valueOf(tcPrice));
            if (afterBalance.equals(pageAfterBalance)) {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]用户交易前余额与公众号用户交易后余额一致"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[注册客户余额管理页面]用户交易前余额与公众号用户交易后余额不一致"+"\n\n";
            }
            //进入用户余额管理页面
            driver1.get("http://admin.test.nbm2m.com/?#/sys-zyagentmoneyhistory");
            //在用户名中输入xiaochu
            Thread.sleep(1500);
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyagentmoneyhistory\"]/div/div/div/form/div[1]/div/div/input")).sendKeys("xiaochu");
            //点击查询按钮
            driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyagentmoneyhistory\"]/div/div/div/form/div[7]/div/button")).click();
            //获取用户余额管理页面的交易后余额
            Thread.sleep(1000);
            String pageBalance = driver1.findElement(By.xpath("//*[@id=\"pane-sys-zyagentmoneyhistory\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/div/span")).getText();
            pageBalance = String.valueOf(Double.valueOf(pageBalance));
            str+="  用户余额管理页面用户交易后余额:"+pageBalance+"\n\n";
            if (dlNewBalance.equals(pageBalance)) {
                str+="  管理员admin的[财务管理]-[用户余额管理页面]用户获得佣金后余额与该页面显示的余额一致"+"\n\n";
            } else {
                str+="  管理员admin的[财务管理]-[用户余额管理页面]用户获得佣金后余额与该页面显示的余额不一致"+"\n\n----------------------------------------------\n\n";
            }
            flag = false;
            str+="\n\n\n****************测试流程完毕****************"+"\n\n";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag == true) {
                str+="\n\n\n****************测试过程中出错****************"+"\n\n";
            }
            out.write(str);
            out.close();
        }

    }
}
