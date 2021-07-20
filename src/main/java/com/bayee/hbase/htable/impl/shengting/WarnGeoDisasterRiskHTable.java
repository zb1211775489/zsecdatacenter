package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WarnGeoDisasterRiskHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class WarnGeoDisasterRiskHTable implements HTableInterface {
    private long id;
    private long update_time;
    private long fore_time;
    private String warning_grade;
    private long warning_time;
    private String warning_period;
    private String city_name;
    private String county_name;
    private String town_name;
    private String district_code;
    private String district_name;
    private String district_level;
    private int affected_family_count;
    private int affected_count;
    private double g1_affected_area;
    private double g2_affected_area;
    private double g3_affected_area;
    private int g1_risk_area_count;
    private int g2_risk_area_count;
    private int g3_risk_area_count;
    private long cd_time;
    private long modify_time;
    private long clean_time;
    private long swap_time;
    private String cd_operation;
    private String source_table;
    private String hash_unique;
    private String cd_source;

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
