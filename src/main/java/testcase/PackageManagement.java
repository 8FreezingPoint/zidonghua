package testcase;

import modules.caiwuguanli.CashWithdrawal;
import modules.caiwuguanli.WithdrawalManagement;
import modules.common.Login;
import modules.taocanguanli.ShareProfit;
import modules.yonghuguanli.BalanceChange;
import org.openqa.selenium.WebDriver;
import uitls.TestFile;
import uitls.Text;

import java.io.IOException;
import java.util.Map;

public class PackageManagement {
    public static void ShareProfit(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("分润查看");
        WebDriver admin = null;
        WebDriver userA = null;
        WebDriver gzh = null;
        try {
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            userA = Login.login(mapA.get("userAUserName"),mapA.get("userAPassword"),text);
            gzh = Login.loginGzh(mapA.get("iccid"),text);
            for (int i = 1;i <=2;i++) {
                if (i == 1) {
                    CashWithdrawal.getInfo(userA,text,1,mapA);
                    ShareProfit.get(userA,text,i,mapA);
                } else {
                    ShareProfit.get(admin,text,i,mapA);
                }
            }
            ShareProfit.gzhOrder(gzh,text,mapA);
            CashWithdrawal.getInfo(userA,text,2,mapA);
            WithdrawalManagement.registUserBalanceCheck(admin,text,mapA);
            WithdrawalManagement.checkShareProfit(admin,text,mapA);
            text.append(">>>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
            userA.quit();
            gzh.quit();
        }
    }
}
