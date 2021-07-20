package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName SupPunEmerManagementHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class SupPunEmerManagementHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String bus_id;
    private String cd_id;
    private String record_unique_identity;
    private String supervise_item_code;
    private String punish_action_name;
    private String punish_action_code;
    private String implement_org_name;
    private String implement_org_code;
    private String supervise_object;
    private String check_action_code;
    private String admin_cp;
    private String admin_cp_type;
    private String admin_cp_cert_type;
    private String admin_cp_social_code;
    private String operate_addr;
    private String district_code_old;
    private String district_code;
    private String district_name;
    private String city_code;
    private String city_name;
    private String punish_accord;
    private String punish_procedure;
    private String punish_file_num;
    private String major_case;
    private String legal_review;
    private long punish_set_date;
    private String punish_type;
    private String punish_result;
    private long submit_date;
    private String dept_code;
    private String dept_name;
    private String source_table;
    private String source_province;
    private String cd_operation;
    private long cd_time;
    private long modify_time;
    private long clean_time;
    private long swap_time;
    private String cd_source;
    private String hash_unique;
    private String illegal_fact;
    private String case_source;
    private long belong_safe_produce;

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
