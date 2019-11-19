package modules.caiwuguanli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class CashWithdrawal {

    //type 1支付宝提现 2银行卡提现
    public static void apply(WebDriver driver, Text text, Integer type, Map<String,String> map) throws InterruptedException {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + map.get("userAUserName") + "  在【提现管理页面】 准备提现 ...");
        if (type == 1) {
            text.append("本次提现方式：提现到【支付宝】").append();
        } else if (type == 2) {
            text.append("本次提现方式：提现到【银行卡】").append();
        }
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
        String beforeBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
        String beforeBlockedBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[2]")).getText();
        String beforeCount = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String beforeWithdraw = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String beforeAudit = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String beforeAudited = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        map.put("beforeBalance",beforeBalance);
        map.put("beforeBlockedBalance",beforeBlockedBalance);
        map.put("beforeCount",beforeCount);
        map.put("beforeWithdraw",beforeWithdraw);
        map.put("beforeAudit",beforeAudit);
        map.put("beforeAudited",beforeAudited);
        text.append("提现前账户余额: " + beforeBalance +" 元");
        text.append("提现前账户冻结金额: " + beforeBlockedBalance +" 元");
        text.append("提现前共有数据: " + beforeCount +" 项");
        text.append("提现前提现总额: " + beforeWithdraw +" 元");
        text.append("提现前待审核: " + beforeAudit +" 元");
        text.append("提现前审核通过: " + beforeAudited +" 元").append();
        String money = map.get("money");
        //点击申请提现
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/form/div[7]/div/button")).click();
        //点击收款类型下拉列表
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[2]/div/div/div[1]/input")).click();
        }catch (Exception e) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[2]/div/div/div[1]/input")).click();
        }
        if (type == 1) {
            //点击支付宝转账
            Thread.sleep(1000);
            try {
                driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
            } catch (Exception e) {
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
            }
            //点击支付宝账号下拉列表
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[3]/div/div/div")).click();
            //选择支付宝账号
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li")).click();
            //输入提现金额
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[5]/div/div[1]/input")).sendKeys(money);
            //输入提现密码
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[6]/div/div[1]/input")).sendKeys(map.get("txPassword"));
            //输入验证码
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[9]/div/div[1]/input")).sendKeys(map.get("code"));
            //点击确定提现
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        } else if (type == 2) {
            //点击银行卡转账
            Thread.sleep(1000);
            try {
                driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[2]")).click();
            } catch (Exception e) {
                Thread.sleep(2000);
                driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[2]")).click();
            }
            //点击银行卡号下拉列表
            Thread.sleep(500);
            try {
                driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[3]/div/div/div/input")).click();
            } catch (Exception e) {
                Thread.sleep(2000);
                driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[3]/div/div/div/input")).click();
            }
            //选择银行卡号
            Thread.sleep(500);
            try {
                driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li")).click();
            } catch (Exception e) {
                Thread.sleep(2000);
                driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li")).click();
            }
            //输入提现金额
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[6]/div/div[1]/input")).sendKeys(money);
            //输入提现密码
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[7]/div/div[1]/input")).sendKeys(map.get("txPassword"));
            //输入验证码
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[10]/div/div[1]/input")).sendKeys(map.get("code"));
            //点击确定提现
            Thread.sleep(100);
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        }
        text.append("本次提现金额：" + money + " 元");
        text.append("***提现成功***").append();
        Thread.sleep(7000);
        String afterBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
        String afterBlockedBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[2]")).getText();
        String afterCount = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String afterWithdraw = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String afterAudit = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String afterAudited = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        String orderNo = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        map.put("afterBalance",afterBalance);
        map.put("afterBlockedBalance",afterBlockedBalance);
        map.put("afterCount",afterCount);
        map.put("afterWithdraw",afterWithdraw);
        map.put("afterAudit",afterAudit);
        map.put("afterAudited",afterAudited);
        map.put("orderNo",orderNo);
        text.append("提现后账户余额: " + afterBalance +" 元");
        text.append("提现后账户冻结金额: " + afterBlockedBalance +" 元");
        text.append("提现后共有数据: " + afterCount +" 项");
        text.append("提现后提现总额: " + afterWithdraw +" 元");
        text.append("提现后待审核: " + afterAudit +" 元");
        text.append("提现后审核通过: " + afterAudited +" 元");
        text.append("***  订单号：" + orderNo + "  ***").append();
        text.append("********************************************************");
        if (
                ((Double.valueOf(beforeBalance) - Double.valueOf(money)) != Double.valueOf(afterBalance))
                ||((Double.valueOf(beforeBlockedBalance) + Double.valueOf(money)) != Double.valueOf(afterBlockedBalance))
                ||((Integer.valueOf(beforeCount) + 1) != Integer.valueOf(afterCount))
                ||(Double.valueOf(beforeWithdraw) + Double.valueOf(money) != Double.valueOf(afterWithdraw))
                ||(Double.valueOf(beforeAudit) + Double.valueOf(money) != Double.valueOf(afterAudit))
                ||!(Double.valueOf(beforeAudited).equals(Double.valueOf(afterAudited)))
        ) {
            text.append("              申请提现后数据结果不一致!!!");
        } else {
            text.append("              申请提现后数据结果一致");
        }
        text.append("********************************************************");
    }

    public static void reApply(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + map.get("userAUserName") + "  在【提现管理页面】 准备重新提交提现申请 ...");
        if (type == 1) {
            text.append("本次提现方式：【支付宝】重新提交").append();
        } else if (type == 2) {
            text.append("本次提现方式：【银行卡】重新提交").append();
        }
        text.append("预期重新提交的单号：" + map.get("orderNo"));
        text.append("预期重新提交的申请用户名：" + map.get("userAUserName"));
        text.append("预期重新提交的提现金额：" + map.get("money") + " 元").append();
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        Thread.sleep(2000);
        //获取首条记录数据
        String findOrderNo = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
        String findMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[10]/div")).getText();
        String findStatus = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[11]/div/span")).getText();
        String findRemark = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[14]/div")).getText();
        text.append("获取到的记录单号: " + findOrderNo);
        text.append("获取到的记录用户名: " + findUserName);
        text.append("获取到的记录提现金额: " + findMoney +" 元");
        text.append("获取到的记录提现状态: " + findStatus);
        text.append("获取到的记录备注: " + findRemark).append();
        if (
                !findOrderNo.equals(map.get("orderNo"))||
                        !findUserName.equals(map.get("userAUserName"))||
                        !findMoney.equals(map.get("money"))
        ) {
            throw new Exception("测试数据错误，测试中断!");
        }
        //获取当前数据
        String beforeBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
        String beforeBlockedBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[2]")).getText();
        String beforeCount = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String beforeWithdraw = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String beforeAudit = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String beforeAudited = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        text.append("提现前账户余额: " + beforeBalance +" 元");
        text.append("提现前账户冻结金额: " + beforeBlockedBalance +" 元");
        text.append("提现前共有数据: " + beforeCount +" 项");
        text.append("提现前提现总额: " + beforeWithdraw +" 元");
        text.append("提现前待审核: " + beforeAudit +" 元");
        text.append("提现前审核通过: " + beforeAudited +" 元").append();
        //点击重新提交
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[15]/div/i")).click();
        //输入提现密码验证码
        Thread.sleep(2000);
        if (type == 1) {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[6]/div/div[1]/input")).sendKeys(map.get("txPassword"));
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[9]/div/div[1]/input")).sendKeys(map.get("code"));
        } else if (type == 2) {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[7]/div/div[1]/input")).sendKeys(map.get("txPassword"));
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[10]/div/div[1]/input")).sendKeys(map.get("code"));
        }
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        text.append("***重新提交成功***").append();
        //获取提交后数据
        Thread.sleep(4000);
        String afterBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
        String afterBlockedBalance = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[2]")).getText();
        String afterCount = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String afterWithdraw = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String afterAudit = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String afterAudited = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        String orderNo = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        text.append("提现后账户余额: " + afterBalance +" 元");
        text.append("提现后账户冻结金额: " + afterBlockedBalance +" 元");
        text.append("提现后共有数据: " + afterCount +" 项");
        text.append("提现后提现总额: " + afterWithdraw +" 元");
        text.append("提现后待审核: " + afterAudit +" 元");
        text.append("提现后审核通过: " + afterAudited +" 元");
        text.append("***  订单号：" + orderNo + "  ***").append();
        text.append("********************************************************");
        if (
                ((Double.valueOf(beforeBalance) - Double.valueOf(map.get("money"))) != Double.valueOf(afterBalance))
                        ||((Double.valueOf(beforeBlockedBalance) + Double.valueOf(map.get("money"))) != Double.valueOf(afterBlockedBalance))
                        ||!(beforeCount.equals(afterCount))
                        ||!(beforeWithdraw.equals(afterWithdraw))
                        ||(Double.valueOf(beforeAudit) + Double.valueOf(map.get("money")) != Double.valueOf(afterAudit))
                        ||!(Double.valueOf(beforeAudited).equals(Double.valueOf(afterAudited)))
        ) {
            text.append("              重新提交后数据结果不一致!!!");
        } else {
            text.append("              重新提交后数据结果一致");
        }
        text.append("********************************************************");
    }

    public static void checkWithdrawal(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append("账号：" + map.get("userBUserName") + "  在【提现管理页面】 准备提现 ...");
        if (type == 2) {
            text.append("本次操作：验证【原提现密码】"+ map.get("newTxPassword") + "可用").append();
        } else if (type == 1) {
            text.append("本次操作：验证【被重置的提现密码】"+ map.get("txPassword") + "可用").append();
        }
        Thread.sleep(1500);
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        Thread.sleep(1500);
        //点击申请提现
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[7]/div/button")).click();
        //点击收款类型下拉列表
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[2]/div/div/div[1]/input")).click();
        } catch (Exception e) {
            System.out.println("点击收款类型下拉列表出错了");
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[2]/div/div/div[1]/input")).click();
        }
        //点击支付宝转账
        Thread.sleep(500);
        if (type == 1) {
            driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
        } else if (type == 2) {
            driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
        }
        //点击支付宝账号下拉列表
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[3]/div/div/div")).click();
        //选择支付宝账号
        Thread.sleep(500);
        if (type == 1) {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li")).click();
        } else if (type == 2) {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/ul/li")).click();
        }
        //输入提现金额
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[5]/div/div[1]/input")).sendKeys("100");
        //输入提现密码
        if (type == 2) {
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[6]/div/div[1]/input")).sendKeys(map.get("newTxPassword"));
        } else if (type == 1) {
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[2]/form/div[6]/div/div[1]/input")).sendKeys(map.get("txPassword"));
        }
        //输入验证码
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div[9]/div/div[1]/input")).sendKeys(map.get("code"));
        //点击确定提现
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        Boolean flag = true;
        Thread.sleep(6000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        } catch (Exception e) {
            flag = false;
        }
        if (flag) {
            text.append("提现成功");
            text.append("***验证成功***");
        } else {
            text.append("提现失败");
            text.append("***验证失败***");
        }
        text.append("----------------------------------------------------------").append();
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
            text.append().append("操作前账户余额: " + beforeAddBalance + " 元");
        } else if (type == 2) {
            String afterAddBalance = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[1]")).getText();
            map.put("afterFindAddBalance", afterAddBalance);
            text.append().append("操作后账户余额: " + afterAddBalance + " 元");
        }
        text.append("----------------------------------------------------------");
    }


}
