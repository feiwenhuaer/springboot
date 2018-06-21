package ocean.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 用于redis序列化和反序列化
 * @author xieyi
 */
@Slf4j
public class SerializeUtil {
    /**
     * 序列化对象
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj){
        ObjectOutputStream objectOutputStream ;
        ByteArrayOutputStream baos ;
        byte[] byteArray ;
        try{
            baos = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(obj);
            byteArray = baos.toByteArray();
            return byteArray;
        }catch(IOException e){
            log.debug("something wrong when serializing Object:{},info:{}", obj, e.getMessage());
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param byteArray
     * @return
     */
    public static Object unSerialize(byte[] byteArray){
        ByteArrayInputStream byteArrayInputStream ;
        Object object ;
        try {
            byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            object =ois.readObject();
            return object;
        } catch (Exception e) {
            log.debug("something wrong when unSerializing byte[] to Object,info:{}", e.getMessage());
        }
        return null;
    }
}
