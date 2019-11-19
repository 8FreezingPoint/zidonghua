package testcase;

import modules.caiwuguanli.CashWithdrawal;
import modules.common.Login;
import modules.yonghuguanli.CustomerChange;
import org.openqa.selenium.WebDriver;
import uitls.TestFile;
import uitls.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomerManagement {
    public static void changeStatus() throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("代理状态");
        WebDriver admin = null;
        //测试数据
        Map<String,String> mapA = new HashMap<>();
        mapA.put("adminUserName","kefu");
        mapA.put("adminPassword","180301");
        mapA.put("userAUserName","zidonghuacesi2");
        mapA.put("userAPassword","test");
        try {
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            for (int i = 1;i <=2;i++) {
                CustomerChange.changeStatus(admin,text,i,mapA);
                Login.checkLogin(text,i,mapA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
        }
    }

    public static void changePassword() throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("修改密码");
        WebDriver userA = null;
        //测试数据
        Map<String,String> mapA = new HashMap<>();
        mapA.put("adminUserName","kefu");
        mapA.put("adminPassword","180301");
        mapA.put("userAUserName","zidonghuacesi2");
        mapA.put("userAPassword","test");
        mapA.put("txPassword","123456");
        mapA.put("newTxPassword","Aa123456");
        mapA.put("newPassword","Bb123456");

        try {
            //登录管理员
            userA = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            for (int i = 1;i <= 2;i++) {
                CustomerChange.changePassword(userA,text,i,mapA);
            }
            text.append().append().append(">>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            userA.quit();
        }
    }

    public static void updatePassword() throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("修改密码");
        WebDriver userA = null;
        //测试数据
        Map<String,String> mapA = new HashMap<>();
        mapA.put("userAUserName","zidonghuacesi2");
        mapA.put("userAPassword","test");
        mapA.put("newPassword","Bb123456");
        mapA.put("newTxPassword","Aa123456");
        mapA.put("code","666666");
        mapA.put("txPassword","123456");

        try {
            //登录一级代理
            userA = Login.login(mapA.get("userAUserName"),mapA.get("userAPassword"),text);
            for (int i = 1;i <= 2;i++) {
                CustomerChange.updatePassword(userA,text,i,mapA);
            }
            text.append().append().append(">>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            userA.quit();
        }
    }

    public static void customerChange(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("冻结重置");
        WebDriver admin = null;
        WebDriver userWithdrawal = null;
        try {
            text.append("                      代理账号状态测试");
            text.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append();
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            for (int j = 1;j <=2;j++) {
                CustomerChange.changePassword(admin,text,j,mapA);
            }
            for (int i = 1;i <=2;i++) {
                CustomerChange.changeStatus(admin,text,i,mapA);
                Login.checkLogin(text,i,mapA);
            }
            text.append().append("                      代理密码重置测试");
            text.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append();
            for (int i = 1;i <=2;i++) {
                //登录一级代理
                if (i == 1) {
                    userWithdrawal = Login.login(mapA.get("userBUserName"),mapA.get("userBPassword"),text);
                } else if (i == 2) {
                    userWithdrawal = Login.login(mapA.get("userBUserName"),mapA.get("newPassword"),text);
                }
                CustomerChange.checkPassword(text,i,mapA);
                CashWithdrawal.checkWithdrawal(userWithdrawal,text,i,mapA);
                if (i == 1) {
                    WebDriver userUpdate = Login.login(mapA.get("userBUserName"),mapA.get("userBPassword"),text);
                    for (int j = 1;j <=2;j++) {
                        CustomerChange.updatePassword(userUpdate,text,j,mapA);
                    }
                    userUpdate.quit();
                }
                userWithdrawal.close();
            }
            text.append(">>>>>>>>>>>>>>>>>>>>>>>> 测试完成 <<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            text.append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
            userWithdrawal.quit();
        }
    }

}
