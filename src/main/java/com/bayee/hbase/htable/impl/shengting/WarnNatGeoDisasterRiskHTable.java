package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WarnNatGeoFisasterRiskHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class WarnNatGeoDisasterRiskHTable implements HTableInterface {
    private int id;
    private long update_time;
    private long fore_time;
    private String warning_grade;
    private String warning_time;
    private String city_name;
    private String county_name;
    private String town_name;
    private String district_code;
    private String district_name;
    private String district_level;
    private int affected_family_count;
    private int affected_count;
    private int g1_affected_area;
    private int g2_affected_area;
    private int g3_affected_area;
    private int g1_risk_area_count;
    private int g2_risk_area_count;
    private int g3_risk_area_count;
    private long swap_time;
    private String cd_time;
    private String cd_operation;
    private String clean_time;

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
