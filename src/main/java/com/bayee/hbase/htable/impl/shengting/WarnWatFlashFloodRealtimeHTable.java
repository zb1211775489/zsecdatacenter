package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WarnWatFlashFloodRealtimeHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class WarnWatFlashFloodRealtimeHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String pre_object_code;
    private String district_id;
    private String district_name;
    private String district_code;
    private String district_level;
    private String warning_type;
    private String future_period;
    private long warning_time;
    private String forcast_value;
    private String warning_grade;
    private long modify_time;
    private long upload_time;
    private long swap_time;
    private long load_time;
    private long cd_time;
    private String cd_operation;
    private String hash_unique;
    private long longitude;
    private long latitude;
    private String position;
    private String position_json;
    private String admin_village;
    private String natural_village;
    private int affected_count;

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
