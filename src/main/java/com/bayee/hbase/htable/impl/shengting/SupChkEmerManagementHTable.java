package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName SupChkEmerManagementHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class SupChkEmerManagementHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String bus_id;
    private String source_id;
    private String record_unique_identity;
    private String supervise_item_code;
    private String supervise_item_check_code;
    private String check_action_name;
    private String check_action_code;
    private String implement_org_name;
    private String implement_org_code;
    private String entrust_dept_name;
    private String entrust_dept_social_code;
    private String supervise_object_social_code;
    private String industry_top_category_code;
    private String industry_top_category_name;
    private String industry_sub_category_code;
    private String industry_sub_category_name;
    private String admin_cp;
    private String admin_cp_type;
    private String admin_cp_cert_type;
    private String admin_cp_social_code;
    private String regist_addr;
    private String operate_addr;
    private String district_code_old;
    private String district_code;
    private String district_name;
    private String city_code;
    private String city_name;
    private String check_form;
    private String check_type;
    private String check_mode;
    private String check_result;
    private String plan_id;
    private long submit_date;
    private String submit_person;
    private String check_person;
    private String check_person_code;
    private String subitem_name;
    private String subitem_status;
    private String supervise_object_name;
    private String is_plan;
    private String dept_code;
    private String dept_name;
    private long check_date;
    private String is_dup_check;
    private String enterprise_score;
    private String enterprise_class;
    private String source_table;
    private String cd_operation;
    private String swap_time;
    private long cd_time;
    private String hash_unique;
    private String clean_time;
    private String load_time;
    private String belong_safe_produce;

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
