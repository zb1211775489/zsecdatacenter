package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatFlashFloodDistrictHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatFlashFloodDistrictHTable implements HTableInterface {
    private int id;
    private long update_time;
    private String district_id;
    private String district_name;
    private String district_code;
    private String executive_level_code;
    private String parent_code;
    private String is_develop_area;
    private String is_enbaled;
    private String is_;
    private String from_date;
    private String to_date;
    private long load_time;
    private String cd_operation;
    private long swap_time;
    private String cd_time;

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
