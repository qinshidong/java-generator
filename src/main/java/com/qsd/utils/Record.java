package com.qsd.utils;

/**
 * @author 秦世东
 * date 2020-03-30
 * 文件说明:
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="record")
public class Record {

    @XmlElement(name = "mission_num")
    private String missionNum;
    @XmlElement(name = "dest_id")
    private String destId;

    @XmlElement(name = "send_status")
    private String sendStatus;
    @XmlElement(name = "receive_status")
    private String receiveStatus;

    @XmlElement(name = "batch_num")
    private String batchNum;
    @XmlElement(name = "stat_time")
    private String statTime;
    // setter  getter 方法

    public String getMissionNum() {
        return missionNum;
    }

    public void setMissionNum(String missionNum) {
        this.missionNum = missionNum;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

    //toString方法
    @Override
    public String toString() {
        return "Record [missionNum=" + missionNum + ", destId=" + destId + ", sendStatus=" + sendStatus
                + ", receiveStatus=" + receiveStatus + ", batchNum=" + batchNum + ", statTime=" + statTime + "]";
    }
}