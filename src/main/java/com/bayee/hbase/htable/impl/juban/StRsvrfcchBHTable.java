package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName StRsvrfcchBHTable
 * @Description: 库(湖)站防洪指标表
 * @Author Lyb
 * @Date 2021/3/31
 **/

@Getter
@Setter
@ToString
public class StRsvrfcchBHTable implements HTableInterface {

    private String stcd;
    private String rsvrtp;
    private double damel;
    private double ckflz;
    private double dsflz;
    private double normz;
    private int ddz;
    private double actz;
    private String ttcp;
    private String fldcp;
    private String actcp;
    private int ddcp;
    private String hhrz;
    private String hmxw;
    private String hhrztm;
    private String hmxinq;
    private String rstdr;
    private String hmxinqtm;
    private String hmxotq;
    private String hmxotqtm;
    private String hlrz;
    private String hlrztm;
    private String hmninq;
    private String hmninqtm;
    private String laz;
    private String sfq;
    private String moditime;
    private Date tongTime;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (stcd+"_"+tongTime.getTime()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
