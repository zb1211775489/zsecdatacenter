package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WaterStationHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/29
 **/
@Getter
@Setter
@ToString
public class AllWaterInfoHTable implements HTableInterface {
    private int id;
    private String dt;
    private String data;
    private String stationCode;
    private String startDate;
    private String endDate;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (stationCode+"_"+dt+"_"+id).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
