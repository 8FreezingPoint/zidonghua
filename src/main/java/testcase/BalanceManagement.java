package testcase;

import modules.caiwuguanli.CashWithdrawal;
import modules.caiwuguanli.WithdrawalManagement;
import modules.common.Login;
import modules.yonghuguanli.BalanceChange;
import org.openqa.selenium.WebDriver;
import uitls.TestFile;
import uitls.Text;

import java.io.IOException;
import java.util.Map;

public class BalanceManagement {

    public static void customerChange(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("加减款");
        WebDriver admin = null;
        WebDriver userA = null;
        try {
            text.append("                      管理员加减款测试");
            text.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append();
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            userA = Login.login(mapA.get("userAUserName"),mapA.get("userAPassword"),text);
            for (int i = 1;i <=2;i++) {
                CashWithdrawal.getInfo(userA,text,1,mapA);
                BalanceChange.addBalance(admin,text,i,mapA);
                CashWithdrawal.getInfo(userA,text,2,mapA);
                WithdrawalManagement.checkBalanceChange(admin,text,mapA);
            }
            text.append().append("                      给代理H5充值测试");
            text.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append();
            CashWithdrawal.getInfo(userA,text,1,mapA);
            BalanceChange.topUp(userA,text,mapA);
            CashWithdrawal.getInfo(userA,text,2,mapA);
            WithdrawalManagement.checkTopUp(admin,text,mapA);
            text.append(">>>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
            userA.quit();
        }
    }
}
