package ocean.util;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.Objects;
/**
 * @author xieyi
 */
@Slf4j
public class FileUtil {
    /**
     * 字符串追加到文件中
     * @param stringPath
     * @param string
     */
    public static void writeStringToFile(String stringPath, String string) {
        File file = new File(stringPath);
        BufferedWriter writer = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(stringPath,true),"utf-8"));
            writer.newLine();
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.debug("something wrong in the methos:{},info:{}", "writeStringToFile", e.getMessage());
                }
            }
        }
    }

    /**
     * 读取资源文件写入目标文件
     * @param target
     * @param source
     * @param cover 是否覆盖 默认false覆盖
     * @param charset 读写的编码
     */
    public static void writeSourceToTarget(String target, String source, Boolean cover,String charset) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        File targetFile = new File(target);
        File sourceFile = new File(source);
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
                if (!sourceFile.exists()) {
                    sourceFile.createNewFile();
                }
                /**
                 * 这种写法可能涉及到乱码的问题
                 */
                /*br = new BufferedReader(new FileReader(sourceFile));*/
                br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile),charset));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile,cover),charset));
                /**
                 * FileWriter(String file,Boolean cover)
                 * cover=true 原文件追加
                 * cover=false 覆盖原文件
                 */
                /*bw = new BufferedWriter(new FileWriter(targetFile,true));
                bw = new BufferedWriter(new FileWriter(targetFile,false));*/
                String str = null;
                while ((str = br.readLine()) != null) {
                    bw.write(str);
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (IOException e) {
            log.debug("something wrong in the methos:{},info:{}", "writeSourceToTarget", e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(br)) {
                    br.close();
                }
                if (Objects.nonNull(bw)) {
                    bw.close();
                }
            } catch (IOException e) {
                log.debug("something wrong in the methos:{},info:{}", "writeSourceToTarget", e.getMessage());
            }
        }
    }
}
