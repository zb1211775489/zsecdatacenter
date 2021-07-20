package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName NewAttResSBaseHTable
 * @Description: 小型水库基础表
 * @Author Lyb
 * @Date 2021/3/31
 **/
@Getter
@Setter
@ToString
public class NewAttResSBaseHTable implements HTableInterface {
    private String id;
    private String resName;
    private String conProCode;
    private String manLev;
    private String adCode;
    private double resLong;
    private double resLat;
    private String engScal;
    private double rcdsArea;
    private int dsflst;
    private int chflst;
    private int totCap;
    private double chfllv;
    private double desFlStag;
    private double normWatLev;
    private String elevSys;
    private String mainFunc;
    private int dsirar;
    private String wsupobj;
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
