package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConEntDangerChemicalTotalHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/
@Getter
@Setter
@ToString
public class ConEntDangerChemicalTotalHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String source_id;
    private String ent_uesr_id;
    private String audit_status_name;
    private double ent_longitude;
    private double ent_latitude;
    private String ent_name;
    private String province_code;
    private String city_code;
    private String county_code;
    private String town_code;
    private String ent_addr;
    private String social_code;
    private String ent_type;
    private String governing_org;
    private String user_status;
    private String national_econ_category_code;
    private String national_econ_class_code1;
    private String national_econ_class_code2;
    private String national_econ_class_code3;
    private String postcode;
    private String ent_fax;
    private String ent_email;
    private long setup_date;
    private String legal_repr;
    private String ent_manager;
    private String ent_manager_tel;
    private String ent_safety_manager;
    private String ent_safety_manager_tel;
    private String operate_scope;
    private String cd_time;
    private String modify_time;
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
