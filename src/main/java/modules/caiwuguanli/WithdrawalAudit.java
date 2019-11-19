package modules.caiwuguanli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;
import uitls.WaitUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WithdrawalAudit {

    //type 1审核 2驳回
    public static void audit(WebDriver driver, Text text, Integer type, Map<String,String> map) throws Exception {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + map.get("adminUserName") + "  在【提现管理页面】 审核提现申请 ...");
        if (type == 1) {
            text.append("本次操作：【审核】").append();
        } else if (type == 2) {
            text.append("本次操作：【驳回】").append();
        }
        text.append("预期审核的用户：" + map.get("userAUserName"));
        text.append("预期审核的单号：" + map.get("orderNo"));
        text.append("预期审核的提现金额：" + map.get("money") + " 元").append();
        //输入用户名
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //获取当前数据
        Thread.sleep(1500);
        String beforeTotalMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String beforeAudit = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String beforeAudited = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        text.append("操作前提现总额：" + beforeTotalMoney +" 元");
        text.append("操作前待审核：" + beforeAudit +" 元");
        text.append("操作前审核通过：" + beforeAudited +" 元").append();
        //订单提现状态下拉列表
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[4]/div/div/div[1]/input")).click();
        //选择待审核
        Thread.sleep(300);
        if (type == 1) {
            driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[1]/span")).click();
        } else if (type == 2) {
            WaitUtil.waitClick(driver,"/html/body/div[3]/div[1]/div[1]/ul/li[1]/span");
        }
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //首条记录数据
        Thread.sleep(3000);
        String beforeCount = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String findOrderNo = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        String findUserName = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
        String findMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[10]/div")).getText();
        String findTime = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[12]/div")).getText();
        text.append("当前页面共有待审核数据：" + beforeCount +" 项");
        text.append("查询到的首条记录订单号：" + findOrderNo);
        text.append("查询到的首条记录用户名：" + findUserName);
        text.append("查询到的首条记录提现金额：" + findMoney +" 元");
        text.append("查询到的首条记录提现时间：" + findTime).append();

        if (Integer.valueOf(beforeCount) == 0 ||
            !findOrderNo.equals(map.get("orderNo"))) {
            throw new Exception("测试数据错误，测试中断!");
        }
        //审核
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[4]/div[2]/table/tbody/tr/td[15]/div/i[1]")).click();
        //要操作的数据信息
        Thread.sleep(3000);
        String willMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[5]/div/div[2]/div[2]/div[3]/table/tbody/tr[" + beforeCount + "]/td[4]/div")).getText();
        String willTime = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[5]/div/div[2]/div[2]/div[3]/table/tbody/tr[" + beforeCount + "]/td[6]/div")).getText();
        text.append("要操作的记录提现金额：" + willMoney +" 元");
        text.append("要操作的记录提现时间：" + willTime).append();
        if (!willMoney.equals(map.get("money"))) {
            text.append("********************************************************");
            text.append("              要操作的记录与预期审核记录不一致!!!");
            text.append("********************************************************").append();
            throw new Exception("测试数据错误，测试中断!");
        }
        if (type == 1) {
            //审核
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[5]/div/div[2]/div[2]/div[3]/table/tbody/tr[" + beforeCount + "]/td[7]/div/i[1]")).click();
            //确定
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[2]")).click();
        } else if (type == 2) {
            //驳回
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[5]/div/div[2]/div[2]/div[3]/table/tbody/tr[" + beforeCount + "]/td[7]/div/i[2]")).click();
            //备注
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div/div/div[1]/textarea")).sendKeys("驳回自动化测试");
            //确定
            driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/div[1]/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        }
        text.append("***操作成功***");
        Thread.sleep(5000);
        String afterCount = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        text.append("当前页面共有待审核数据：" + afterCount +" 项");
        //刷新页面
        driver.navigate().refresh();
        //输入用户名
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        } catch (Exception e) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("userAUserName"));
        }
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //获取操作后页面数据
        Thread.sleep(1500);
        String afterTotalMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String afterAudit = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String afterAudited = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        text.append("操作后提现总额：" + afterTotalMoney +" 元");
        text.append("操作后待审核：" + afterAudit +" 元");
        text.append("操作后审核通过：" + afterAudited +" 元").append();
        text.append("********************************************************");
        if (type == 1) {
            if (
                    !((Double.valueOf(beforeTotalMoney).equals(Double.valueOf(afterTotalMoney)))
                            ||((Double.valueOf(beforeAudit) - Double.valueOf(map.get("money"))) != Double.valueOf(afterAudit))
                            ||((Double.valueOf(beforeAudited) + Double.valueOf(map.get("money"))) != Double.valueOf(afterAudited)))
                            ||(Integer.valueOf(beforeCount) -1 != (Integer.valueOf(afterCount)))
            ) {
                text.append("              审核通过后数据结果不一致!!!");
            } else {
                text.append("              审核通过后数据结果一致");
            }
        } else if (type == 2) {
            if (
                    !((Double.valueOf(beforeTotalMoney).equals(Double.valueOf(afterTotalMoney)))
                            ||((Double.valueOf(beforeAudit) - Double.valueOf(map.get("money"))) != Double.valueOf(afterAudit))
                            ||!(Double.valueOf(beforeAudited)).equals(Double.valueOf(afterAudited)))
                            ||(Integer.valueOf(beforeCount) -1 != (Integer.valueOf(afterCount)))
            ) {
                text.append("              驳回后数据结果不一致!!!");
            } else {
                text.append("              驳回后数据结果一致");
            }
        }
        text.append("********************************************************");
    }


    //type 1审核 2驳回
    public static void batchAudit(WebDriver driver, Text text, Integer type, Map<String,String> mapA,Map<String,String> mapB) throws Exception {
        //进入提现管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zycashin");
        text.append().append("账号：" + mapA.get("adminUserName") + "  在【提现管理页面】 批量审核提现申请 ...");
        if (type == 1) {
            text.append("本次操作：【批量审核】").append();
        } else if (type == 2) {
            text.append("本次操作：【批量驳回】").append();
        }
        text.append("预期审核的用户：" + mapA.get("userAUserName"));
        text.append("预期审核的单号A：" + mapA.get("orderNo"));
        text.append("预期审核的单号B：" + mapB.get("orderNo"));
        text.append("预期审核的提现金额A：" + mapA.get("money") + " 元").append();
        text.append("预期审核的提现金额B：" + mapB.get("money") + " 元").append();
        //输入用户名
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(mapA.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //获取当前数据
        Thread.sleep(1500);
        String beforeTotalMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String beforeAudit = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String beforeAudited = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        text.append("操作前提现总额：" + beforeTotalMoney +" 元");
        text.append("操作前待审核：" + beforeAudit +" 元");
        text.append("操作前审核通过：" + beforeAudited +" 元").append();
        //订单提现状态下拉列表
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[4]/div/div/div[1]/input")).click();
        //选择待审核
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (type == 1) {
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[1]/span")).click();
            driver.findElement(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li:nth-child(1)")).click();
        } else if (type == 2) {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li:nth-child(1)")).click();
        }
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //首条记录数据
        Thread.sleep(1500);
        String beforeCount = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        String findOrderNoA = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div")).getText();
        String findUserNameA = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div")).getText();
        String findMoneyA = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[10]/div")).getText();
        String findTimeA = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[12]/div")).getText();
        String findOrderNoB = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td[2]/div")).getText();
        String findUserNameB = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td[3]/div")).getText();
        String findMoneyB = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td[10]/div")).getText();
        String findTimeB = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td[12]/div")).getText();
        text.append("操作前当前页面共有待审核数据：" + beforeCount +" 项").append();
        text.append("查询到的首条记录订单号A：" + findOrderNoA);
        text.append("查询到的首条记录用户名A：" + findUserNameA);
        text.append("查询到的首条记录提现金额A：" + findMoneyA +" 元");
        text.append("查询到的首条记录提现时间A：" + findTimeA).append();
        text.append("查询到的首条记录订单号B：" + findOrderNoB);
        text.append("查询到的首条记录用户名B：" + findUserNameB);
        text.append("查询到的首条记录提现金额B：" + findMoneyB +" 元");
        text.append("查询到的首条记录提现时间B：" + findTimeB).append();

        if (Integer.valueOf(beforeCount) == 0 ||
                !findOrderNoA.equals(mapA.get("orderNo"))||
                !findOrderNoA.equals(mapA.get("orderNo")))
        {
            throw new Exception("测试数据错误，测试中断!");
        }
        //要操作的两条记录
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div/label/span/span")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td[1]/div/label/span/span")).click();
        if (type == 1) {
            //批量审核
            Thread.sleep(300);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[8]/div/button")).click();
            //确定
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
            //备注
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div/div/div[1]/textarea")).sendKeys("批量审核自动化测试");
            //确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        } else if (type == 2) {
            //批量驳回
            Thread.sleep(300);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[9]/div/button")).click();
            //确定
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
            //备注
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[2]/form/div/div/div[1]/textarea")).sendKeys("驳回自动化测试");
            //确定
            driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[4]/div/div[3]/span/button[2]")).click();
        }
        text.append("***操作成功***");
        Thread.sleep(5000);
        String afterCount = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[1]/span/font[3]")).getText();
        text.append("操作后当前页面共有待审核数据：" + afterCount +" 项");
        //刷新页面
        driver.navigate().refresh();
        //输入用户名
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(mapA.get("userAUserName"));
        //点击查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/form/div[6]/div/button")).click();
        //获取操作后页面数据
        Thread.sleep(1500);
        String afterTotalMoney = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[1]")).getText();
        String afterAudit = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[2]")).getText();
        String afterAudited = driver.findElement(By.xpath("//*[@id=\"pane-sys-zycashin\"]/div/div/div/div[1]/div/div/div/span[2]/span/font[3]")).getText();
        text.append("操作后提现总额：" + afterTotalMoney +" 元");
        text.append("操作后待审核：" + afterAudit +" 元");
        text.append("操作后审核通过：" + afterAudited +" 元").append();
        text.append("********************************************************");
        if (type == 1) {
            if (
                    !((Double.valueOf(beforeTotalMoney).equals(Double.valueOf(afterTotalMoney)))
                            ||((Double.valueOf(beforeAudit) - Double.valueOf(mapB.get("money")) - Double.valueOf(mapB.get("money"))) != Double.valueOf(afterAudit))
                            ||((Double.valueOf(beforeAudited) + Double.valueOf(mapA.get("money")) + Double.valueOf(mapB.get("money"))) != Double.valueOf(afterAudited)))
                            ||(Integer.valueOf(beforeCount) -2 != (Integer.valueOf(afterCount)))
            ) {
                text.append("              批量审核通过后数据结果不一致!!!");
            } else {
                text.append("              批量审核通过后数据结果一致");
            }
        } else if (type == 2) {
            if (
                    !((Double.valueOf(beforeTotalMoney).equals(Double.valueOf(afterTotalMoney)))
                            ||((Double.valueOf(beforeAudit) - Double.valueOf(mapA.get("money")) - Double.valueOf(mapB.get("money"))) != Double.valueOf(afterAudit))
                            ||!(Double.valueOf(beforeAudited)).equals(Double.valueOf(afterAudited)))
                            ||(Integer.valueOf(beforeCount) -2 != (Integer.valueOf(afterCount)))
            ) {
                text.append("              批量驳回后数据结果不一致!!!");
            } else {
                text.append("              批量驳回后数据结果一致");
            }
        }
        text.append("********************************************************");
    }
}
