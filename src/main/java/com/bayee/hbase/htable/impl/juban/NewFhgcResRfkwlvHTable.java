package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName NewFhgcResRfkwlv
 * @Description: 大中型水库汛期主要特征值运用表 & 小型水库汛期主要特征值运用表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class NewFhgcResRfkwlvHTable implements HTableInterface {
    private String resId;
    private String fslwactyr;
    private String fslwbdtm;
    private String fslwedtm;
    private String cfhwlfst;
    private double flLowLimLev;
    private String flLowLimVol;
    private String flSeasTp;
    private String note;
    private String adcd;
    private Date initTime;
    private Date tongTime;
    private Date bizTime;
    private Date loadTime;
    private String isDelete;
    private String op;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (resId+"_"+UUID.randomUUID().toString()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
