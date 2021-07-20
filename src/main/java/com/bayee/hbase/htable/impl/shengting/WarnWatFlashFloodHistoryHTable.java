package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName WarnWatFlashFloodHistoryHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/


@Getter
@Setter
@ToString
public class WarnWatFlashFloodHistoryHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String pre_object_code;
    private String district_id;
    private String district_name;
    private String district_code;
    private String district_level;
    private String warning_type;
    private int future_period;
    private long warning_time;
    private double forcast_value;
    private String warning_grade;
    private String is_sent;
    private long modify_time;
    private String upload_time;
    private String swap_time;
    private String load_time;
    private String cd_time;
    private String cd_operation;
    private String hash_unique;
    private String longitude;
    private String latitude;
    private String position;
    private String position_json;
    private String natural_village;
    private String admin_village;

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
