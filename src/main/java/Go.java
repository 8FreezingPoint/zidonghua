import testcase.*;

import java.util.HashMap;
import java.util.Map;

public class Go {
    public static void main(String[] args) throws Exception {
        Map<String,String> mapA = new HashMap<>();
        mapA.put("adminUserName","kefu");
        mapA.put("adminPassword","180301");
        mapA.put("userAUserName","zidonghuacesi1");
        mapA.put("userAPassword","test");
        mapA.put("userBUserName","zidonghuacesi2");
        mapA.put("userBPassword","test");
        mapA.put("userBName","biedong");
        mapA.put("money","100");
        mapA.put("code","666666");
        mapA.put("txPassword","123456");
        mapA.put("newPassword","Bb123456");
        mapA.put("newTxPassword","Aa123456");
        mapA.put("addMoney","150");
        mapA.put("iccid","8986031744203489997");
        WithdrawDeposit.withdrawDeposit(mapA);
        CustomerManagement.customerChange(mapA);
        BalanceManagement.customerChange(mapA);
        ProfessionalWorkManagement.add(mapA);
        PackageManagement.ShareProfit(mapA);
    }
}
