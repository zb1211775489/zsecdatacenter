package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName LegEnterpriseInfoHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class LegEnterpriseInfoHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String regist_no;
    private String ent_name;
    private String ent_type_code;
    private String currency_code;
    private String address;
    private String operate_addr;
    private long operate_startdate;
    private long operate_enddate;
    private String operate_scope;
    private String business_scope_code;
    private String regist_auth_code;
    private String regist_authority;
    private String regist_status_code;
    private long approval_date;
    private String revocation_date;
    private String revocation_reason;
    private String contacts;
    private String contacts_id_num;
    private String contacts_tel;
    private String legal_repr;
    private String regist_funds;
    private long establish_date;
    private String social_code;
    private String entity_id_code;
    private String industry_class_code;
    private String org_class_code;
    private String national_econ_class_code;
    private String operate_longitude;
    private String operate_latitude;
    private String operate_position_json;
    private String swap_time;
    private long load_time;
    private long cd_time;
    private String cd_operation;
    private String hash_unique;
    private String district_code;
    private String operate_position;
    private String industry_class_name;
    private String national_econ_top_class;
    private String national_econ_mid_class;
    private String national_econ_sub_class;
    private String province_name;
    private String city_name;
    private String county_name;
    private String town_name;
    private String ent_type_name;
    private String currency_name;
    private String business_scope_name;
    private String regist_status_name;

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