package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName NewFhgcResRwacdr
 * @Description: 大中型水库水位、面积、库容、泄 量关系表 & 小型水库水位、面积、库容、泄量 关系表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class NewFhgcResRwacdrHTable implements HTableInterface {
    private String resId;
    private int wtlv;
    private String ar;
    private double rscp;
    private String nrsottds;
    private String exsottds;
    private String hlppttds;
    private String gnqufl;
    private String statCode;
    private String adcd;
    private Date initTime;
    private Date tongTime;
    private String bizTime;
    private String loadTime;
    private String isDelete;
    private String op;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (resId+"_"+ UUID.randomUUID().toString()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
