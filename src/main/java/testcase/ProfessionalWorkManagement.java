package testcase;

import modules.common.Login;
import modules.yonghuguanli.CustomerChange;
import modules.yonghuguanli.ProfessionalWork;
import org.openqa.selenium.WebDriver;
import uitls.TestFile;
import uitls.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfessionalWorkManagement {
    public static void add(Map<String,String> mapA) throws IOException {
        Text text = new Text();
        TestFile file = new TestFile("业务管理");
        WebDriver admin = null;
        try {
            //登录管理员
            admin = Login.login(mapA.get("adminUserName"),mapA.get("adminPassword"),text);
            ProfessionalWork.addProfessionalWork(admin,text,mapA);
            ProfessionalWork.find(admin,text,mapA);
            ProfessionalWork.update(admin,text,mapA);
        } catch (Exception e) {
            e.printStackTrace();
            text.append().append(">>>>>>>>>>>>>>>>>>>>>>测试过程中出错<<<<<<<<<<<<<<<<<<<<<<").append().append(e.getLocalizedMessage());
        } finally {
            file.Write(text.getBuilder());
            admin.quit();
        }
    }
}
