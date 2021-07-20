package com.bayee.utils;

import com.bayee.hbase.htable.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * @ClassName HbaseUtils
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/18
 **/
public class HbaseUtils{
    private static final Logger log = Logger.getLogger(HbaseUtils.class);

    /**
     * 将对象转化成Put集合
     * @param o
     * @return
     */
    public static <T extends HTableInterface> Put getPut(T o){
        Put put = new Put(o.rowKey());
        Class clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(o);
                if(value != null){
                    put.addColumn("info".getBytes(),field.getName().getBytes(),value.toString().getBytes());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return put;
    }
}
