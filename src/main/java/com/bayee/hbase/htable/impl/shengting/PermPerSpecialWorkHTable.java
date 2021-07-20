package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName PermPerSpecialWorkHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class PermPerSpecialWorkHTable implements HTableInterface {
    private int id;
    private long update_time;
    private String address;
    private String allow_handle_item;
    private String approval_opinions;
    private String audit_opinions;
    private String birthday;
    private String card_type;
    private long cert_enddate;
    private long cert_get_date;
    private String cert_name;
    private String cert_num;
    private String cert_qual_type;
    private long cert_startdate;
    private String department_opinions;
    private String education;
    private String health_condition;
    private String id_num;
    private String issue_authority;
    private long issue_date;
    private String name;
    private long next_review_date;
    private String post_code;
    private String practise_date;
    private String practise_retest;
    private String practise_score;
    private String review_date;
    private String sex;
    private String source_id;
    private String special_work_class;
    private String telephone;
    private String theory_exam_score;
    private String theory_resit_score;
    private String theory_train_date;
    private String window_opinions;
    private String work_unit;
    private String swap_time;
    private long load_time;
    private long cd_time;
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