package ocean.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

/**
 * 初始化工作
 * @author xieyi
 */
@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
       log.info("do ocean.init work");
    }

    private static void writeSourceToTargetWithCover(String sourcePath, String targetPath) {
        File file1 = new File(sourcePath);
        File file2 = new File(targetPath);
        if(!file1.exists()){
            try {
                file1.createNewFile();
                if(!file2.exists()){
                    file2.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //step1:创建缓冲流对象：它是过滤流，是对节点流的包装
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath),"utf-8"));
            bw = new BufferedWriter(new FileWriter(targetPath));
            String str = null;
            log.info(">>>>>>>>sql拷贝开始");
            while ((str = br.readLine()) != null) {
                //一次读取字符文本文件的一行字符
                bw.write(str);
                if(Objects.equals(str.trim(),"")){
                    bw.write("******************分割线****************");
                }
                /*System.out.println(str);*/
                //一次写入一行字符串
                bw.newLine();
                //写入行分隔符
            }
            log.info(">>>>>>>>sql拷贝完成");
            bw.flush(); //step2:刷新缓冲区
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // step3: 关闭IO流对象
            try {
                if (bw != null) {
                    bw.close(); //关闭过滤流时,会自动关闭它所包装的底层节点流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
