package com.bayee.hbase.htable.impl.juban;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName StationRainInfoHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/30
 **/
@Getter
@Setter
@ToString
public class StationWaterInfoHTable implements HTableInterface {
    private String stcd;
    private String sttp;
    private Date tm;
    private double z;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (stcd+"_"+sttp+"_"+tm.getTime()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
