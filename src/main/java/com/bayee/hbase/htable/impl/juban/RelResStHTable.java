package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName RelResStHTable
 * @Description: 水库测站表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class RelResStHTable implements HTableInterface {
    private String resCode;
    private String stcd;
    private String fromDate;
    private String toDate;
    private Date tongTime;
    private String op;
    private String adcd;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (resCode).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
