package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName NewAttResLmBaseHTable
 * @Description: 大中型水库基础表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class NewAttResLmBaseHTable implements HTableInterface {

    private String id;
    private String resName;
    private String conProCode;
    private String manLev;
    private String adCode;
    private double resLong;
    private double resLat;
    private String waterSystemName;
    private String engScal;
    private String engGrad;
    private double rcdsArea;
    private String mstreamLen;
    private int dsflst;
    private int chflst;
    private String dwchsfds;
    private int dswlel;
    private String wlel;
    private int dsemel;
    private String emel;
    private int totCap;
    private double chfllv;
    private double desFlStag;
    private double uppLevFlco;
    private int flcoCap;
    private double normWatLev;
    private String floodWatLev;
    private String reservoirStorageCurve;
    private double hshgwtlv;
    private String hisMaxOutFlow;
    private String mainFunc;
    private String cnflMjob;
    private int dsirar;
    private int annWain;
    private int avanenot;
    private String resLoc;
    private Date tongTime;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (id).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
