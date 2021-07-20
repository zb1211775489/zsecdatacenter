package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatGeoDangerPointHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatGeoDangerPointHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String source_id;
    private String danger_name;
    private String feature_code;
    private String district_code;
    private String city;
    private String county;
    private String town;
    private String location_type;
    private String address;
    private String postcode;
    private String telephone;
    private String risk_area_id;
    private String risk_area_code;
    private String unified_code;
    private String data_source;
    private double danger_length;
    private String rain_id;
    private long swap_time;
    private String load_time;
    private String cd_time;
    private String cd_operation;
    private String hash_unique;
    private String position;
    private String location_polygon_json;

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
