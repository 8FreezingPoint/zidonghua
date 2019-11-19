package testcase;

import modules.common.Login;
import modules.caiwuguanli.WithdrawalManagement;
import modules.caiwuguanli.CashWithdrawal;
import modules.caiwuguanli.WithdrawalAudit;
import org.openqa.selenium.WebDriver;
import uitls.TestFile;
import uitls.Text;

import java.io.IOException;
import java.util.Map;

public class WithdrawDeposit {
    public static void withdrawDeposit(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("提现");
        WebDriver admin = null;
        WebDriver userA = null;

        //测试数据
        Map<String,String> mapB = mapA;
        try {
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            //登录一级代理
            userA = Login.login(mapA.get("userAUserName"),mapA.get("userAPassword"),text);
            //循环 第一次支付宝提现,第二次银行提现
            for (int i = 1;i <=2;i++) {
                //一级代理申请提现
                if (i == 1) {
                    CashWithdrawal.apply(userA,text,i,mapA);
                    WithdrawalManagement.check(admin,text,0,mapA);
                    WithdrawalAudit.audit(admin,text,i,mapA);
                    WithdrawalManagement.check(admin,text,i,mapA);
                } else if(i == 2) {
                    CashWithdrawal.apply(userA,text,i,mapB);
                    WithdrawalManagement.check(admin,text,0,mapB);
                    WithdrawalAudit.audit(admin,text,i,mapB);
                    WithdrawalManagement.check(admin,text,i,mapB);
                    CashWithdrawal.reApply(userA,text,i,mapB);
                }
            }
            for (int i = 1;i <= 2;i++) {
                CashWithdrawal.apply(userA,text,i,mapA);
                CashWithdrawal.apply(userA,text,i,mapB);
                WithdrawalAudit.batchAudit(admin,text,i,mapA,mapB);
            }
            text.append().append().append(">>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
            userA.quit();
        }
    }

    public static void batchWithdrawDeposit(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("批量审核");
        WebDriver admin = null;
        WebDriver userA = null;

        //测试数据
        Map<String,String> mapB = mapA;
        try {
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            //登录一级代理
            userA = Login.login(mapA.get("userAUserName"),mapA.get("userAPassword"),text);
            for (int i = 1;i <= 2;i++) {
                CashWithdrawal.apply(userA,text,1,mapA);
                CashWithdrawal.apply(userA,text,1,mapB);
                WithdrawalAudit.batchAudit(admin,text,i,mapA,mapB);
            }
            text.append().append().append(">>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
//            admin.quit();
            userA.quit();
        }
    }

}
