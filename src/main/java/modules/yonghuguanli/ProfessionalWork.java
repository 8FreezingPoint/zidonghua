package modules.yonghuguanli;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitls.Text;

import java.util.Map;

public class ProfessionalWork {
    public static void addProfessionalWork(WebDriver driver, Text text, Map<String,String> map) throws Exception {
        String professionalWorkName = RandomString.make(10);
        map.put("professionalWorkName",professionalWorkName);
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zybusiness");
        text.append("账号：" + map.get("adminUserName") + "  在【业务管理页面】 准备新增业务 ...");
        text.append("随机生成业务名称：" + professionalWorkName);
        //新增
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[3]/div/button")).click();
        //输入业务名称
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[2]/form/div/div/div[1]/input")).sendKeys(professionalWorkName);
        //确定
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[3]/span/button[2]")).click();
        Thread.sleep(1000);
        text.append("操作成功");
        text.append("----------------------------------------------------------").append();
    }

    public static void find(WebDriver driver, Text text, Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zybusiness");
        text.append("账号：" + map.get("adminUserName") + "  在【业务管理页面】 准备查询业务 ...");
        text.append("要查询的业务名称：" + map.get("professionalWorkName"));
        //输入业务名称
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("professionalWorkName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[2]/div/button")).click();
        Thread.sleep(1500);
        //获取查询结果条数
        String total = driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[2]/span[1]")).getText();
        if (!"共 0 条".equals(total)) {
            text.append("查询成功，查询结果：" + total);
        } else {
            text.append("查询失败，查询结果：" + total);
        }
        text.append("----------------------------------------------------------").append();
    }

    public static void update(WebDriver driver, Text text, Map<String,String> map) throws Exception {
        String newProfessionalWorkName = RandomString.make(10);
        map.put("newProfessionalWorkName",newProfessionalWorkName);
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zybusiness");
        text.append("账号：" + map.get("adminUserName") + "  在【业务管理页面】 准备修改业务 ...");
        text.append("原业务名称：" + map.get("professionalWorkName"));
        text.append("随机生成新业务名称：" + newProfessionalWorkName);
        text.append("业务添加代理：" + map.get("userBUserName"));
        text.append("业务添加代理名称：" + map.get("userBName"));
        //输入业务名称
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("professionalWorkName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[2]/div/button")).click();
        //修改
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[6]/div/i")).click();
        //输入业务名称
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[2]/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[2]/form/div[1]/div/div/input")).sendKeys(newProfessionalWorkName);
        //添加代理
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/div[1]/input")).sendKeys(map.get("userBName"));
        //选择代理
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[24]")).click();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[2]/form/div[1]/div/div/input")).click();
        //确定
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[3]/div/div[3]/span/button[2]")).click();
        Thread.sleep(1000);
        text.append("操作成功");
        text.append("----------------------------------------------------------").append();
    }

    public static void changeStatus(WebDriver driver, Text text, Integer type ,Map<String,String> map) throws Exception {
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zybusiness");
        text.append("账号：" + map.get("adminUserName") + "  在【业务管理页面】 准备启用/停用业务 ...");
        if (type == 1) {
            text.append("本次操作：【停用】业务：" + map.get("newProfessionalWorkName")).append();
        } else if (type == 2) {
            text.append("本次操作：【启用】业务：" + map.get("newProfessionalWorkName")).append();
        }
        //输入业务名称
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("professionalWorkName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[2]/div/button")).click();
        Thread.sleep(1000);
        //点击状态按钮
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[4]/div/div/span")).click();
        text.append("操作成功");
        text.append("----------------------------------------------------------").append();
    }

    public static void check(WebDriver driver, Text text, Integer type ,Map<String,String> map) throws Exception {
        String professionalWorkName = RandomString.make(10);
        map.put("professionalWorkName",professionalWorkName);
        //进入客户管理页面
        driver.get("http://admin.test.nbm2m.com/#/sys-zybusiness");
        text.append("账号：" + map.get("adminUserName") + "  在【业务管理页面】 准备启用/停用业务 ...");
        if (type == 1) {
            text.append("本次操作：【停用】业务：" + map.get("newProfessionalWorkName")).append();
        } else if (type == 2) {
            text.append("本次操作：【启用】业务：" + map.get("newProfessionalWorkName")).append();
        }
        //输入业务名称
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[1]/div/div/input")).sendKeys(map.get("professionalWorkName"));
        //查询
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/form/div[2]/div/button")).click();
        Thread.sleep(1000);
        //点击状态按钮
        driver.findElement(By.xpath("//*[@id=\"pane-sys-zybusiness\"]/div/div/div/div[1]/div[3]/table/tbody/tr/td[4]/div/div/span")).click();
        text.append("操作成功");
        text.append("----------------------------------------------------------").append();
    }

}
