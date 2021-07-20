package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName PttPerDisasterTransferHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class PttPerDisasterTransferHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String transfer_relation_id;
    private String transfer_district_id;
    private String transfer_district_code;
    private String transfer_person_id;
    private String transfer_person_name;
    private String transfer_person_tel;
    private String transfer_person_id_num;
    private String natural_village;
    private String is_smart_tel;
    private String transfer_manager_name;
    private String transfer_manager_tel;
    private String transfer_manager_id_num;
    private String transfer_person_type;
    private String transfer_breed_code;
    private String transfer_mariculture_comments;
    private String decrepit_building_sub_type;
    private String basin_sub_type;
    private String risk_area_name;
    private String agritainment_name;
    private String business_owner;
    private String business_owner_tel;
    private int bed_count;
    private int staff_count;
    private String agritainment_comments;
    private String basin_flood_comments;
    private String geo_disaster_risk_comments;
    private String decrepit_building_comments;
    private String response_grade;
    private String geo_disaster_sub_type;
    private String village_name;
    private String town_name;
    private String town_id;
    private String county_name;
    private String county_id;
    private String city_name;
    private String city_id;
    private long cd_time;
    private long modify_time;
    private String cd_operation;
    private String response_id;
    private String other_risk_sub_type;
    private String other_risk_comments;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (id + "_" + update_time).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }

}