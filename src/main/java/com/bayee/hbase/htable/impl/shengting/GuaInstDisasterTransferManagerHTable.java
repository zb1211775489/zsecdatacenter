package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName GuaInstDisasterTransferManagerHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class GuaInstDisasterTransferManagerHTable implements HTableInterface {
    private long ID;
    private long update_time;
    private String transfer_manager_id;
    private String transfer_manager_name;
    private String transfer_manager_tel;
    private String transfer_manager_id_num;
    private String district_id;
    private long modify_time;
    private String city;
    private String county;
    private String town;
    private String village;
    private String district_code;
    private String cd_time;
    private String cd_operation;
    private String cd_source;
    private String source_table;

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (ID+"_"+update_time).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }

}
