package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName PermCertSafeManageWorkEmerHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class PermCertSafeManageWorkEmerHTable implements HTableInterface {
    private long id;
    private long update_time;
    private String source_id;
    private String cert_name;
    private String operation_type;
    private String cert_qual_type;
    private String job_class;
    private String job_item;
    private String cert_code;
    private String issue_authority;
    private String exam_authority;
    private String apply_id;
    private String student_code;
    private String student_name;
    private String student_id_type;
    private String student_id_num;
    private String student_sex;
    private String student_education;
    private String student_work_org;
    private String student_org_class;
    private String student_work_addr;
    private String student_tel;
    private String student_health;
    private String student_duty;
    private String student_title;
    private String total_class_hours;
    private String exam_room_id;
    private String exam_room_name;
    private String score;
    private String operation_score;
    private long cert_startdate;
    private long cert_enddate;
    private long cert_get_date;
    private long issue_date;
    private long next_review_date;
    private String cert_review_date;
    private String cert_status;
    private String train_org_id;
    private String train_org_name;
    private String exam_authority_id;
    private String exam_authority_name;
    private String creater_id;
    private String create_org;
    private long create_time;
    private String last_modifier_id;
    private long last_modifier_time;
    private String is_delete;
    private String cert_comment;
    private String is_report_nation;
    private String error_reason_reply;
    private String approval_id;
    private long approval_time;
    private String is_print;
    private String outer_system_id;
    private String interface_user_id;
    private String image_url;
    private String cert_make_way;
    private long cert_make_time;
    private String cert_make_style;
    private String cert_file_code;
    private String sign_authority_id;
    private String sign_authority;
    private String sign_authority_nation_id;
    private String paper_track_code;
    private String cert_oss_url;
    private String is_report_province;
    private long swap_time;
    private String cd_time;
    private String cd_operation;
    private String hash_unique;
    private String clean_time;
    private String load_time;
    private String delete_handler_id;
    private String delete_handler_time;
    private String qrcode_text;
    private String district_code;
    private String social_code;

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