package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName DutRecEmerEventHTable
 * @Description: 值班值守应急事件
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class DutRecEmerEventHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String event_id;
    private String event_parent_id;
    private String event_type;
    private String release_status;
    private String event_headline;
    private String event_code;
    private String province_sign;
    private String city_sign;
    private String event_source;
    private String event_source_channel;
    private String reporter_name;
    private String reporter_tel;
    private String case_scope;
    private String case_type;
    private String case_level;
    private long case_starttime;
    private String case_endtime;
    private String case_addr;
    private String case_object;
    private int case_count;
    private String death_count;
    private String missing_count;
    private String serious_wound_count;
    private String minor_wound_count;
    private String trapped_count;
    private String poisoned_count;
    private String case_situation_brief;
    private String case_rescue_power;
    private String case_measure_progress;
    private String case_measure_trend;
    private String case_longitude;
    private String case_latitude;
    private String case_location_point;
    private String is_deleted;
    private String reprot_org_id;
    private String reprot_org_name;
    private String reprot_org_level;
    private String reprot_org_level_code;
    private long create_time;
    private String creator_id;
    private String creator_name;
    private long modify_time;
    private String case_times_comments;
    private String is_recent;
    private String leader_instruction;
    private String is_read;
    private long read_time;
    private String event_issuer;
    private String event_group_id;
    private String fire_type;
    private String fire_reason;
    private long fire_time;
    private int fire_lost_area_total;
    private int fire_lost_area_forest;
    private int fire_lost_area_forest_affected;
    private long death_time;
    private String is_send_bak;
    private String case_top_class;
    private String case_mid_class;
    private String case_sub_class;
    private String case_reason;
    private long case_reprot_time;
    private String cd_operation;
    private String position;
    private String district_code;
    private String cd_time;
    private String clean_time;
    private String swap_time;
    private String source_table;
    private String hash_unique;
    private String cd_source;
    private int case_death_count;
    private int case_lose_count;
    private int case_serious_count;
    private int case_minor_count;
    private int case_trapped_count;
    private int case_poisoning_count;
    private String source_id;
    private String city_code;
    private String city;
    private String handle_org_code;

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
