package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName StationHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/30
 **/
@Getter
@Setter
@ToString
public class StationInfoHTable implements HTableInterface {
    private String stcd;
    private String otherStcd;
    private String stnm;
    private String rvnm;
    private String hnnm;
    private String bsnm;
    private double lgtd;
    private double lttd;
    private String stlc;
    private String addvcd;
    private String dtmnm;
    private String dtmel;
    private int dtpr;
    private String sttp;
    private String item;
    private String frgrd;
    private String esstym;
    private String bgfrym;
    private String atcunit;
    private String admauth;
    private String locality;
    private String stbk;
    private String stazt;
    private String dstrvm;
    private int drna;
    private String phcd;
    private String usfl;
    private String comments;
    private String moditime;
    private String townCode;
    private String orderId;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (stcd+"_"+stnm).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
