package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatFlashFloodObjectHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatFlashFloodObjectHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String pre_object_code;
    private String district_name;
    private String district_code;
    private int govern_area;
    private int households;
    private int population;
    private double longitude;
    private double latitude;
    private String pre_object_type;
    private String risk_type;
    private String comments;
    private long modify_time;
    private String location_point;
    private String admin_village;
    private String natural_village;
    private String position;
    private long swap_time;
    private String cd_time;
    private String cd_operation;
    private String hash_unique;
    private String affected_count;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (id+"_"+update_time).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
