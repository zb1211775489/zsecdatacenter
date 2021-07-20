package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName PttPerCodeOperationLocationHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class PttPerCodeOperationLocationHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String event_id;
    private String event_name;
    private String operator_type;
    private String operate_type;
    private String operator_id_num;
    private String operator_tel;
    private long operate_time;
    private String operate_longitude;
    private String operate_latitude;
    private String operate_addr;
    private String operate_province;
    private String operate_city;
    private String operate_county;
    private String operate_town;
    private String operate_district_code;
    private String user_id_num;
    private String user_tel;
    private String user_code_type;
    private String user_city;
    private String user_county;
    private String user_town;
    private String user_village;
    private String user_district_code;
    private String is_census;
    private String user_name;
    private String cd_time;
    private String modify_time;
    private String cd_operation;

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