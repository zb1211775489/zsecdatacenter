package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConEntDangerChemicalOperateHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConEntDangerChemicalOperateHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String source_id;
    private String ent_name;
    private long setup_date;
    private String regist_addr;
    private String storage_addr;
    private String social_code;
    private String business_lic_num;
    private String org_code;
    private String legal_repr;
    private String safety_operate_cert_num;
    private long safety_operate_cert_startdate;
    private long safety_operate_cert_enddate;
    private String audit_operate_scope;
    private String safety_use_cert_num;
    private long safety_use_cert_startdate;
    private long safety_use_cert_enddate;
    private String safety_use_cert_permit_scope;
    private String manager_name;
    private String manager_tel;
    private String safety_manager_name;
    private String safety_manager_tel;
    private int staff_count;
    private String chemic_industry_class;
    private int tank_count;
    private String tank_cubage;
    private int storage_count;
    private int storage_cubage;
    private String facility_around1000;
    private int major_danger_count;
    private String national_econ_category_code;
    private String national_econ_class_code1;
    private String national_econ_class_code2;
    private String national_econ_class_code3;
    private String ent_type;
    private String operate_ent_type;
    private String operate_type;
    private String governing_org;
    private long promise_start_time;
    private String industry_park_code;
    private String ent_user_id;
    private String audit_status_name;
    private String user_status;
    private String province_code;
    private String city_code;
    private String county_code;
    private String town_code;
    private String province_name;
    private String city_name;
    private String county_name;
    private String town_name;
    private double longitude;
    private double latitude;
    private String position;
    private String modify_time;
    private String cd_time;
    private String clean_time;
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
