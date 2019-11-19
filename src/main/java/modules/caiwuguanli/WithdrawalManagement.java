package modules.caiwuguanli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class WithdrawalManagement {

    public static void check(WebDriver driver, Text text,Integer type,Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zyagentmoneyhistory");
        text.append().append("账号：" + map.get("adminUserName") + "  在【用户余额管理页面】 查看用户余额流水 ...");
        if (type == 1) {
            text.append("本次操作：【提现成功流水】").append();
        } else if (type == 2) {
            text.append("本次操作：【提现驳回流水】").append();
        } else if (type == 0) {
            text.append("本次操作：【申请提现流水】").append();
        }
        text.append("预期审核的用户：" + map.get("userAUserName"));
        text.append("预期审核的单号：" + map.get("orderNo"));
        text.append("预期审核的提现金额：" + map.get("money") + " 元").append();

        //输入用户名
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[7]/div/button")).click();
        //获取订单号
        Thread.sleep(2000);
        String findOrderNo = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div/div/span")).getText();
        //获取用户名
        String findUserName = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        //获取交易金额
        String findMoney = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[4]/div")).getText();
        //获取交易后余额
        String findAfterBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/div/span")).getText();
        //获取交易类型
        String dealType = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/span")).getText();
        //获取备注
        String remark = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[9]/div")).getText();

        text.append("查到的订单号: " + findOrderNo);
        text.append("查到的用户名: " + findUserName);
        text.append("查到的交易金额: " + findMoney +" 元");
        text.append("查到的交易后余额: " + findAfterBalance +" 元");
        text.append("交易类型: " + dealType);
        text.append("备注: " + remark).append();
        text.append("**********************************************************");
        if (type == 0 || type == 1) {
            if (
                    !(findOrderNo.equals(map.get("orderNo")))||
                            !(findUserName.equals(map.get("userAUserName")))||
                            !((findMoney.replace("-","")).equals(map.get("money")))||
                            !(Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("afterBalance"))))
            ) {
                text.append("              查到的流水记录不一致!!!");
            } else {
                text.append("              查到的流水记录一致");
            }
        } else if (type == 2) {
            if (
                    !(findOrderNo.equals(map.get("orderNo")))||
                            !(findUserName.equals(map.get("userAUserName")))||
                            !((findMoney.replace("-","")).equals(map.get("money")))||
                            !(Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("beforeBalance"))))
            ) {
                text.append("                   查到的流水记录不一致!!!");
            } else {
                text.append("                   查到的流水记录一致");
            }
        }
        text.append("**********************************************************");
    }

    public static void checkBalanceChange(WebDriver driver, Text text,Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zyagentmoneyhistory");
        text.append().append("账号：" + map.get("adminUserName") + "  在【用户余额管理页面】 查看用户余额流水 ...");
        text.append("本次操作：【查看加减款流水】").append();

        text.append("预期审核的用户：" + map.get("userAUserName"));
        text.append("预期审核的操作金额：" + map.get("addMoney") + " 元").append();
        text.append("操作前代理查询的余额：" + map.get("beforeFindAddBalance") + " 元");
        text.append("操作后代理查询的余额：" + map.get("afterFindAddBalance") + " 元");
        text.append("操作前页面显示的余额：" + map.get("beforeAddBalance") + " 元");
        text.append("操作后页面显示的余额：" + map.get("afterAddBalance") + " 元").append();

        //输入用户名
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[7]/div/button")).click();
        Thread.sleep(2000);
        //获取用户名
        String findUserName = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        //获取交易金额
        String findMoney = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[4]/div")).getText();
        //获取交易后余额
        String findAfterBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/div/span")).getText();
        //获取交易类型
        String dealType = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/span")).getText();
        //获取备注
        String remark = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[9]/div")).getText();

        text.append("查到的用户名: " + findUserName);
        text.append("查到的交易金额: " + findMoney + " 元");
        text.append("查到的交易后余额: " + findAfterBalance + " 元");
        text.append("交易类型: " + dealType);
        text.append("备注: " + remark).append();
        text.append("**********************************************************");
        if (
                            !(findUserName.equals(map.get("userAUserName")))||
                            !((findMoney.replace("-","")).equals(map.get("addMoney")))||
                            !(Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("afterFindAddBalance")))&&Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("afterAddBalance"))))
            ) {
                text.append("                   查到的流水记录不一致!!!");
            } else {
                text.append("                   查到的流水记录一致");
        }
        text.append("**********************************************************");
    }

    public static void checkTopUp(WebDriver driver, Text text,Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zyagentmoneyhistory");
        text.append().append("账号：" + map.get("adminUserName") + "  在【用户余额管理页面】 查看用户余额流水 ...");
        text.append("本次操作：【查看充值续费流水】").append();

        text.append("预期审核的用户：" + map.get("userAUserName"));
        text.append("预期审核的操作金额：" + map.get("totalMoney") + " 元").append();
        text.append("操作前代理查询的余额：" + map.get("beforeFindAddBalance") + " 元");
        text.append("操作后代理查询的余额：" + map.get("afterFindAddBalance") + " 元");
        text.append("操作前操作页面的用户余额：" + map.get("beforeAddBalance") + " 元").append();
        //输入用户名
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[7]/div/button")).click();
        Thread.sleep(2000);
        //获取用户名
        String findUserName = "";
        //获取交易金额
        String findMoney = "";
        //获取交易后余额
        String findAfterBalance = "";
        //获取交易类型
        String dealType = "";
        //获取备注
        String remark = "";
        for (int i = 1;i <= 2;i++) {
            dealType = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr["+ i +"]/td[7]/div")).getText();
            if ("批量充值".equals(dealType)) {
                findUserName = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr["+ i +"]/td[2]/div")).getText();
                findMoney = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr["+ i +"]/td[4]/div")).getText();
                findAfterBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr["+ i +"]/td[5]/div/span")).getText();
                remark = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr["+ i +"]/td[9]/div")).getText();
                break;
            }
        }
        text.append("查到的用户名: " + findUserName);
        text.append("查到的交易金额: " + findMoney + " 元");
        text.append("查到的交易后余额: " + findAfterBalance + " 元");
        text.append("交易类型: " + dealType);
        text.append("备注: " + remark).append();
        text.append("**********************************************************");
        if (
                !(findUserName.equals(map.get("userAUserName")))||
                        !((findMoney.replace("-","")).equals(map.get("totalMoney")))||
                        !(Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("afterFindAddBalance"))))
        ) {
            text.append("                   查到的流水记录不一致!!!");
        } else {
            text.append("                   查到的流水记录一致");
        }
        text.append("**********************************************************");
    }



    public static void checkShareProfit(WebDriver driver, Text text,Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zyagentmoneyhistory");
        text.append().append("账号：" + map.get("adminUserName") + "  在【用户余额管理页面】 查看用户余额流水 ...");
        text.append("本次操作：【查看分润流水】").append();

        text.append("要查看的用户：" + map.get("userAUserName"));
        text.append("预期获得分润：" + map.get("userProfit") + " 元").append();
        text.append("分润前代理查询的余额：" + map.get("beforeFindAddBalance") + " 元");
        text.append("分润后代理查询的余额：" + map.get("afterFindAddBalance") + " 元");
        //输入用户名
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[7]/div/button")).click();
        Thread.sleep(2000);
        //获取用户名
        String findUserName = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        //获取交易金额
        String findMoney = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[4]/div")).getText();
        //获取交易后余额
        String findAfterBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/div/span")).getText();
        //获取交易类型
        String dealType = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/span")).getText();
        //获取备注
        String remark = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[9]/div")).getText();
        text.append("查到的用户名: " + findUserName);
        text.append("查到的交易金额: " + findMoney + " 元");
        text.append("查到的交易后余额: " + findAfterBalance + " 元");
        text.append("交易类型: " + dealType);
        text.append("备注: " + remark).append();
        text.append("**********************************************************");
        if (
                !(findUserName.equals(map.get("userAUserName")))||
                        !(Double.valueOf(findMoney).equals(map.get("userProfit")))||
                        !(Double.valueOf(findAfterBalance).equals(Double.valueOf(map.get("afterFindAddBalance"))))
        ) {
            text.append("                   查到的流水记录不一致!!!");
        } else {
            text.append("                   查到的流水记录一致");
        }
        text.append("**********************************************************");
    }

    public static void registUserBalanceCheck(WebDriver driver, Text text, Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/?#/sys-zyuserrecharge");
        text.append("账号：" + map.get("adminUserName") + "  在【注册用户余额管理页面】 查看交易记录 ...");
        text.append("卡号ICCID：" + map.get("iccid")).append();
        Thread.sleep(3000);
        //输入iccid
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/form/div[3]/div/div/input")).sendKeys(map.get("iccid"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/form/div[6]/div/button")).click();
        //获取查到的首条数据
        Thread.sleep(2000);
        //iccid
        String findIccid = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[1]/div")).getText();
        //交易前余额
        String findBeforeBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[4]/div/span")).getText();
        //交易金额
        String findPrice = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[5]/div")).getText();
        //交易后余额
        String findAfterBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[6]/div")).getText();
        //交易类型
        String findType = driver.findElement(By.xpath("//*[@id=\"pane-sys-zyuserrecharge\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[7]/div/span")).getText();
        text.append("查询到的ICCID：" + findIccid);
        text.append("查询到的交易前余额：" + findBeforeBalance);
        text.append("查询到的交易金额：" + findPrice);
        text.append("查询到的交易后余额：" + findAfterBalance);
        text.append("查询到的交易类型：" + findType);
        if (!findBeforeBalance.equals(map.get("beforeCardBalance")) || !findAfterBalance.equals(map.get("afterCardBalance"))) {
            text.append().append("【注册用户余额管理页面】数据错误!!!");
        } else {
            text.append().append("【注册用户余额管理页面】数据正确");
        }
        text.append("----------------------------------------------------------");
    }
}
