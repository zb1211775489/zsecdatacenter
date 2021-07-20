package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName ConEntDangerSourceEntInfoHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/
@Getter
@Setter
@ToString
public class ConEntDangerSourceEntInfoHTable implements HTableInterface {
    private int id;
    private long update_time;
    private String sourcce_id;
    private String ent_code;
    private String ent_name;
    private String district_code;
    private String district_name;
    private int level;
    private String danger_source_grage_code;
    private String danger_source_grage_name;
    private String linked_name;
    private String is_linked;
    private String is_linked_dic_value;
    private String is_online;
    private String is_online_dic_value;
    private String is_commit;
    private String regist_addr;
    private String produce_operate_addr;
    private String social_code;
    private double longitude;
    private double latitude;
    private String dynamic_danger_grade;
    private String dynamic_danger_grade_dic;
    private String ent_status_name;
    private String ent_status_dic;
    private String establish_date;
    private String legal_repr;
    private String ent_manager_name;
    private String ent_manager_tel;
    private String safe_produce_manager;
    private String safe_produce_manager_tel;
    private String safety_duty_tel;
    private String post_code;
    private String ent_web_site;
    private String operate_scope;
    private String safety_produce_stand_grade;
    private String safety_produce_cert_num;
    private Date safety_produce_cert_begin_date;
    private Date safety_produce_cert_end_date;
    private int staff_count;
    private String worker_count;
    private String toxic_chemical_worker_count;
    private String danger_chemical_worker_count;
    private int special_worker_count;
    private String factory_geo;
    private Date online_updatetime;
    private int alarm_count;
    private int hi_alarm_count;
    private int lo_alarm_count;
    private int tank_count;
    private int plant_count;
    private int target_count;
    private int video_count;
    private int warning_count;
    private int hazard_count;
    private int hazard_count1;
    private int hazard_count2;
    private int hazard_count3;
    private int hazard_count4;
    private int hazard_count9;
    private String cd_time;
    private String modify_time;
    private String clean_time;
    private long swap_time;
    private String cd_operation;
    private String source_table;
    private String hash_unique;
    private String cd_source;
    private int gas_count;
    private String ent_type;

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
