package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName StRvfcchBHTable
 * @Description: 河道站防洪指标表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class StRvfcchBHTable implements HTableInterface {
    private String stcd;
    private String ldkel;
    private String rdkel;
    private double wrz;
    private String wrq;
    private double grz;
    private String grq;
    private String flpq;
    private String obhtz;
    private String obhtztm;
    private String ivhz;
    private String ivhztm;
    private String obmxq;
    private String obmxqtm;
    private String ivmxq;
    private String ivmxqtm;
    private String hmxs;
    private String hmxstm;
    private String hmxavv;
    private String hmxavvtm;
    private String hlz;
    private String hlztm;
    private String hmnq;
    private String hmnqtm;
    private String taz;
    private String taq;
    private String laz;
    private String laq;
    private String sfz;
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
