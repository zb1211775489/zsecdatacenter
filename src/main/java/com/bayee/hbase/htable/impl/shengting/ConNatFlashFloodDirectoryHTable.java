package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ConNatFlashFloodDirectoryHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ConNatFlashFloodDirectoryHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String pre_object_code;
    private String district_id;
    private String admin_village;
    private String natural_village;
    private String is_added;
    private long from_time;
    private String to_time;
    private String orig_code;
    private String geo_code;
    private String sync_time;
    private String cd_operation;
    private long modify_time;
    private String is_locked;
    private String check_code;
    private String is_audited;
    private long audit_time;
    private String audit_status;
    private String audit_reason;
    private String district_code;
    private long swap_time;
    private String load_time;
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
