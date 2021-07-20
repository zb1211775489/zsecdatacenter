package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatGeoRiskAreaHTable
 * @Description: 地质灾害风险预防区
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatGeoRiskAreaHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String risk_area_code;
    private String risk_area_name;
    private String feature_code;
    private String district_code;
    private String city;
    private String county;
    private String town;
    private String location_polygon_json;
    private String location_type;
    private String risk_area_addr;
    private String risk_area_postcode;
    private String risk_area_tel;
    private String risk_area_type;
    private String risk_area_size;
    private int affected_family;
    private int affected_people;
    private int affected_asset;
    private String hazard_cause_type;
    private String stability;
    private String rain_id;
    private String conty_leader;
    private String conty_leader_tel;
    private String town_leader;
    private String town_leader_tel;
    private String station_leader;
    private String station_leader_tel;
    private String grid_leader;
    private String grid_leader_tel;
    private String temp_risk_point;
    private String source_id;
    private long swap_time;
    private String load_time;
    private String cd_time;
    private String cd_operation;
    private String hash_unique;
    private double longitude;
    private double latitude;
    private String position;
    private String affected_count;
    private int data_type;
    private String geometry_text;
    private String position_district_code;

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
