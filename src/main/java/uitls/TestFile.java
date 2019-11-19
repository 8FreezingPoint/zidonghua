package uitls;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFile {

    private SimpleDateFormat sdf;
    private String fileName;
    private java.io.File file;
    private Writer out;

    public TestFile(String name) throws IOException {
        this.sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        this.fileName = sdf.format(new Date())+ name +"测试结果";
        this.file = new java.io.File("D:"+fileName+".txt");
        this.out = new FileWriter(file,false);
    }

    public void Write(StringBuilder text) throws IOException {
        this.out.write(String.valueOf(text));
        this.out.close();
    }
}
