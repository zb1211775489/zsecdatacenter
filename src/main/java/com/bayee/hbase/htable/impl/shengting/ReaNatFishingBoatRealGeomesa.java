package com.bayee.hbase.htable.impl.shengting;

import com.bayee.hbase.htable.HTableInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ReaNatFishingBoat
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/4/8
 **/

@Getter
@Setter
@ToString
public class ReaNatFishingBoatRealGeomesa {

    private String mmsi;
    private String boat_name;
    private String type;
    private String subType;
    private String areaid;
    private String registration;
    private int is_registration;
    private float boat_speed;
    private double longitude;
    private double latitude;
    private int is_has_mmsi;
    private String source;
    private String location_point;
    private long update_time;

}