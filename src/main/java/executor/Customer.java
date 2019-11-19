package executor;

import testcase.CustomerManagement;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Customer {
    private ExecutorService executor = Executors.newCachedThreadPool() ;
    public void fun(Map<String,String> map) throws Exception {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行用户管理测试代码......");
                try {
                    CustomerManagement.customerChange(map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("用户管理测试代码执行完成......");
            }
        });
    }
}
