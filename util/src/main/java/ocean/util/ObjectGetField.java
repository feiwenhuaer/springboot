package ocean.util;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 用于获取对象属性和值
 * @author xieyi
 */
@Slf4j
public class ObjectGetField {
    /**
     * 获取所有属性和值
     * @param object
     * @return
     */
    public static Map<Object,Object> getObjectFieldAndValue(Object object){
        Class<?> clz = object.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();
        Map<Object, Object> map = Maps.newLinkedHashMap();
        for (Field field : fields) {
            /**
             * 根据拼凑的字符来找你写的getter方法的
             * 如果出现NoSuchMethod异常 说明找不到get方法
             */
            Method m ;
            try {
                m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                // 如果type是包装类型，则前面包含"class "，后面跟类名，基本数据类型则类型为自身
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.String")) {
                    String val = (String) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.Integer")) {
                    Integer val = (Integer) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.Double")) {
                    Double val = (Double) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.Long")) {
                    Long val = (Long) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.Boolean")) {
                    Boolean val = (Boolean) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.util.Date")) {
                    Date val = (Date) m.invoke(object);
                    map.put(field.getName(), val);
                }
                if (Objects.equals(field.getGenericType().toString(),"class java.lang.Short")) {
                    Short val = (Short) m.invoke(object);
                    map.put(field.getName(), val);
                }
            } catch (Exception e) {
                throw new RuntimeException(Throwables.getStackTraceAsString(e));
            }
        }
        return map;
    }
    /**
     * 把属性的第一个字母大写、用于拼凑属性的get方法
     */
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 获取对象id属性的值
     * @param object
     * @return
     */
    public static Map<Object,Object> getObjectId(Object object){
        Class<?> clz = object.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();
        Map<Object, Object> map = Maps.newLinkedHashMap();
        for (Field field : fields) {
            Method m ;
            try {
                m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                // 如果type是包装类型，则前面包含"class "，后面跟类名，基本数据类型则类型为自身
                if(Objects.equals(field.getName(),"id")){
                    if (Objects.equals(field.getGenericType().toString(),"class java.lang.String")) {
                        String val = (String) m.invoke(object);
                        map.put(field.getName(), val);
                    }
                    if (Objects.equals(field.getGenericType().toString(),"class java.lang.Long")) {
                        Long val = (Long) m.invoke(object);
                        map.put(field.getName(), val);
                    }
                    if (Objects.equals(field.getGenericType().toString(),"class java.lang.Integer")) {
                        Integer val = (Integer) m.invoke(object);
                        map.put(field.getName(), val);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(Throwables.getStackTraceAsString(e));
            }
        }
        return map;
    }

}
