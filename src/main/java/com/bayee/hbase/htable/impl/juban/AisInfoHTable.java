package com.bayee.hbase.htable.impl.juban;

import com.alibaba.fastjson.JSONObject;
import com.bayee.hbase.htable.HTableInterface;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName AisInfoPojo
 * @Description: AIS数据
 * @Author Lyb
 * @Date 2021/3/17
 **/
public class AisInfoHTable implements HTableInterface {
    private String callsign;
    private String cnName;
    private String color;
    private double courseOverGround;
    private int delayState;
    private String destination;
    private double drought;
    private String eta;
    private int headDirector;
    private int iState;
    private long imo;
    private double lat;
    private int length;
    private double lon;
    private long mmsi;
    private String name;
    private String navigationStatus;
    private boolean netMark;
    private int source;
    private int speedOverGround;
    private long time;
    private int trueHeadOverGround;
    private String type;
    private int typeValue;
    private long updateDate;
    private int width;

    @Override
    public String toString() {
        return "AisInfoHTable{" +
                "callsign='" + callsign + '\'' +
                ", cnName='" + cnName + '\'' +
                ", color='" + color + '\'' +
                ", courseOverGround=" + courseOverGround +
                ", delayState=" + delayState +
                ", destination='" + destination + '\'' +
                ", drought=" + drought +
                ", eta='" + eta + '\'' +
                ", headDirector=" + headDirector +
                ", iState=" + iState +
                ", imo=" + imo +
                ", lat=" + lat +
                ", length=" + length +
                ", lon=" + lon +
                ", mmsi=" + mmsi +
                ", name='" + name + '\'' +
                ", navigationStatus='" + navigationStatus + '\'' +
                ", netMark=" + netMark +
                ", source=" + source +
                ", speedOverGround=" + speedOverGround +
                ", time=" + time +
                ", trueHeadOverGround=" + trueHeadOverGround +
                ", type='" + type + '\'' +
                ", typeValue=" + typeValue +
                ", updateDate=" + updateDate +
                ", width=" + width +
                '}';
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCourseOverGround() {
        return courseOverGround;
    }

    public void setCourseOverGround(double courseOverGround) {
        this.courseOverGround = courseOverGround;
    }

    public int getDelayState() {
        return delayState;
    }

    public void setDelayState(int delayState) {
        this.delayState = delayState;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDrought() {
        return drought;
    }

    public void setDrought(double drought) {
        this.drought = drought;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getHeadDirector() {
        return headDirector;
    }

    public void setHeadDirector(int headDirector) {
        this.headDirector = headDirector;
    }

    public int getiState() {
        return iState;
    }

    public void setiState(int iState) {
        this.iState = iState;
    }

    public long getImo() {
        return imo;
    }

    public void setImo(long imo) {
        this.imo = imo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getMmsi() {
        return mmsi;
    }

    public void setMmsi(long mmsi) {
        this.mmsi = mmsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNavigationStatus() {
        return navigationStatus;
    }

    public void setNavigationStatus(String navigationStatus) {
        this.navigationStatus = navigationStatus;
    }

    public boolean isNetMark() {
        return netMark;
    }

    public void setNetMark(boolean netMark) {
        this.netMark = netMark;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSpeedOverGround() {
        return speedOverGround;
    }

    public void setSpeedOverGround(int speedOverGround) {
        this.speedOverGround = speedOverGround;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTrueHeadOverGround() {
        return trueHeadOverGround;
    }

    public void setTrueHeadOverGround(int trueHeadOverGround) {
        this.trueHeadOverGround = trueHeadOverGround;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(int typeValue) {
        this.typeValue = typeValue;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = (name+"_"+time).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }


    public static void main(String[] args) {
        String data = " {\n" +
                "\t\t\t\"callsign\": \"9V5003\",\n" +
                "\t\t\t\"color\": \"#fff170\",\n" +
                "\t\t\t\"courseOverGround\": 6.0,\n" +
                "\t\t\t\"delayState\": 1,\n" +
                "\t\t\t\"destination\": \"VNSGNTHLCH\",\n" +
                "\t\t\t\"drought\": 10.1,\n" +
                "\t\t\t\"eta\": \"00002460\",\n" +
                "\t\t\t\"headDirector\": 0,\n" +
                "\t\t\t\"iState\": 0,\n" +
                "\t\t\t\"imo\": 9343077,\n" +
                "\t\t\t\"lat\": 30.718678,\n" +
                "\t\t\t\"length\": 222,\n" +
                "\t\t\t\"lon\": 123.001816,\n" +
                "\t\t\t\"mmsi\": 565936000,\n" +
                "\t\t\t\"name\": \"MAERSK JABAL\",\n" +
                "\t\t\t\"navigationStatus\": \"0\",\n" +
                "\t\t\t\"netMark\": false,\n" +
                "\t\t\t\"source\": 0,\n" +
                "\t\t\t\"speedOverGround\": 5.2,\n" +
                "\t\t\t\"time\": 1615969680000,\n" +
                "\t\t\t\"trueHeadOverGround\": 4.0,\n" +
                "\t\t\t\"type\": \"70\",\n" +
                "\t\t\t\"typeValue\": 70,\n" +
                "\t\t\t\"updateDate\": 1615969680000,\n" +
                "\t\t\t\"width\": 30\n" +
                "\t\t}";

        AisInfoHTable aisInfoHTable = JSONObject.parseObject(data, AisInfoHTable.class);



    }
}
