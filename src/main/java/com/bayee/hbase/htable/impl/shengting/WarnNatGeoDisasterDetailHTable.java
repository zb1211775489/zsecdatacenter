package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WarnGeoDisasterRiskHTable
 * @Description: 地质灾害风险等级预警数据
 * @Author Lyb
 * @Date 2021/4/8
 **/


@Getter
@Setter
@ToString
public class WarnNatGeoDisasterDetailHTable implements HTableInterface {
    private long id;
    private long update_time;
    private long fore_time;
    private String warning_grade;
    private String warning_time;
    private String city_name;
    private String county_name;
    private String town_name;
    private String district_code;
    private String risk_area_name;
    private String risk_area_code;
    private int risk_area_size;
    private int affected_family_count;
    private int affected_count;
    private int living_count;
    private int returned_count;
    private int evacuated_count;
    private int unreturned_count;
    private String manager;
    private String manager_tel;
    private String grid_manager;
    private String grid_manager_tel;
    private String position;
    private double longitude;
    private double latitude;
    private String position_json;
    private String cd_time;
    private String modify_time;
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
