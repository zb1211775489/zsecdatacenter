package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConEntPromiseInfoHTable
 * @Description: 每天企业承诺数据
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConEntPromiseInfoHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String source_id;
    private String ent_user_id;
    private String province_code;
    private String city_code;
    private String county_code;
    private String town_code;
    private String province_name;
    private String city_name;
    private String county_name;
    private String town_name;
    private String governing_org;
    private String ent_type;
    private long promise_date;
    private int result_seriousness_score;
    private String possibility_comments;
    private String possibility_grade;
    private String result_seriousness_grade;
    private String risk_grade_name;
    private String super_order_fire_count;
    private String first_order_fire_count;
    private String second_order_fire_count;
    private String enter_restrict_space_count;
    private String blind_plate_wall_count;
    private String high_operation_count;
    private String lifting_count;
    private String temp_electro_count;
    private String break_ground_count;
    private String cut_road_count;
    private String maintenance_count;
    private int possibility_score;
    private String danger_source_grade_name;
    private int has_key_supervise_process;
    private String signature;
    private int product_set_count;
    private int operate_set_num;
    private int stop_product_set_num;
    private String run_set_num;
    private String stop_set_num;
    private String total_set_num;
    private String tank_warning_index_count;
    private String tank_warning_count;
    private String tank_count;
    private String tank_operate_count;
    private String storage_count;
    private String run_storage_count;
    private long promise_time;
    private String promise_tips;
    private int is_promised;
    private String danger_process_count;
    private String key_supervise_chemical_reserve;
    private String is_person_manage_safety;
    private String is_set_chemical_storage;
    private String is_escape_way_required;
    private String has_safety_use_instruction;
    private String is_paste_safety_known;
    private String is_make_safety_operate_rule;
    private String is_safety_skill_education;
    private String is_use_nonexplosive_electrical;
    private String is_prepare_fire_equip;
    private String ent_produce_status;
    private String chemical_store_area_count;
    private String chemical_use_area_count;
    private String cd_time;
    private String modify_time;
    private String clean_time;
    private long swap_time;
    private String cd_operation;
    private String source_table;
    private String hash_unique;
    private String cd_source;
    private String ent_name;
    private String social_code;

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
