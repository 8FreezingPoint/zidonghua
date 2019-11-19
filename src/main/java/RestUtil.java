import org.openqa.selenium.json.Json;

import com.alibaba.fastjson.JSON;
import uitls.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author 包福平
 * @QQ:1140913970
 */
public class RestUtil {
    /**
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static Map interfaceUtil(String path,String data,String token,String method) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
            conn.setRequestMethod(method);
//           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("token",token);
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                Map map = (Map)JSON.parse(str);
                return map;
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List check(Map map, Text text) {
        if (!"success".equals(map.get("msg"))) {
            if ("500".equals(map.get("code"))) {
                text.append("后台服务器内部错误");
                System.out.println("后台服务器内部错误");
            } else {
                text.append("code：" + map.get("code"));
                System.out.println(map.get("code"));
                text.append("msg：" + map.get("msg"));
                System.out.println(map.get("msg"));
            }
            return null;
        } else {
            text.append("功能正常");
            return (List)map.get("data");
        }
    }
//"agentID=6356&operators=1&passagewayId=435&packageName=&category=&orSoStatus="
    public static void main(String[] args) {
        Map map = interfaceUtil("http://47.112.144.153:8899/iot-platform/sys/zypackageprice/update",
                "agentID=6356&id=72518&orSoStatus=1&packageId=1331",
                "c5afc36434f6cdbaa1d3b3751c1d4f07","PUT");
        List list = check(map,new Text());
        System.out.println(list);
//        for (int i = 0; i < list.size() ; i++) {
//            Map map1 = (Map)list.get(i);
//            if ("88".equals(map1.get("packageName"))) {
//                System.out.println(map1.get("cost"));
//                System.out.println(map1.get("price"));
//                break;
//            }
//        }
//        List list = (List)map.get("data");
//        System.out.println(list);
//        interfaceUtil("http://192.168.10.89:8080/eoffice-restful/resources/sys/oadata", "usercode=10012");
//        interfaceUtil("http://192.168.10.89:8080/eoffice-restful/resources/sys/oaholiday",
//                    "floor=first&year=2017&month=9&isLeader=N");
    }
}
