package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName PttPerDisasterToTransferHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class PttPerDisasterToTransferHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String transfer_person_name;
    private String transfer_person_id_num;
    private String transfer_person_tel;
    private String is_smart_tel;
    private String city;
    private String county;
    private String town;
    private String village;
    private String householder_relation;
    private String householder_name;
    private String householder_tel;
    private long household_count;
    private String householder_id_num;
    private String area_leader_name;
    private String area_leader_tel;
    private String village_leader_name;
    private String village_leader_tel;
    private String village_cadres_name;
    private String village_cadres_tel;
    private String village_manager_name;
    private String village_manager_tel;
    private String transfer_place_name;
    private String place_manager_name;
    private String place_manager_tel;
    private String danger_point_type;
    private String danger_point_name;
    private String danger_point_position;
    private String response_grade;
    private String is_selected;
    private String is_transfered;
    private String response_id;
    private String transfer_manager_name;
    private String transfer_manager_tel;
    private String transfer_manager_id_num;
    private String transfer_person_type;
    private long cd_time;
    private long modify_time;
    private String cd_operation;
    private String transfer_person_id;
    private String transfer_relation_id;
    private long response_start_time;
    private long response_stop_time;
    private String event_id;
    private String event_name;
    private String transfer_type;
    private String transfer_district_code;
    private String reporter_name;

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