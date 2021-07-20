package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatFlashFloodDangerAreaHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatFlashFloodDangerAreaHTable implements HTableInterface {
    private int id;
    private long update_time;
    private String danger_area_code;
    private String danger_area_name;
    private String prev_code;
    private String ad_code;
    private String district_code;
    private String admin_village;
    private String natural_village;
    private int households;
    private int house_count;
    private int population;
    private String danger_area_level;
    private String longitude;
    private String position;
    private long modify_time;
    private String latitude;
    private String position_json;
    private String cd_time;
    private String clean_time;
    private String swap_time;
    private String cd_operation;
    private String hash_unique;

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
