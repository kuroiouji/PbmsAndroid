package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class DraftWithdrawDao {

    @SerializedName("wd_id")
    private String wdId;

    @SerializedName("wd_code")
    private String wdCode;

    @SerializedName("wd_topic")
    private String wdTopic;

    @SerializedName("wd_ps_id")
    private String wdPsId;

    @SerializedName("wd_ps_name")
    private String wdPsName;

    @SerializedName("wd_cutbal_date")
    private String wdCutbalDate;

    @SerializedName("wd_bgy_id")
    private String wdBgyId;

    @SerializedName("wd_active")
    private String wdActive;

    public String getWdId() {
        return wdId;
    }

    public void setWdId(String wdId) {
        this.wdId = wdId;
    }

    public String getWdCode() {
        return wdCode;
    }

    public void setWdCode(String wdCode) {
        this.wdCode = wdCode;
    }

    public String getWdTopic() {
        return wdTopic;
    }

    public void setWdTopic(String wdTopic) {
        this.wdTopic = wdTopic;
    }

    public String getWdPsId() {
        return wdPsId;
    }

    public void setWdPsId(String wdPsId) {
        this.wdPsId = wdPsId;
    }

    public String getWdPsName() {
        return wdPsName;
    }

    public void setWdPsName(String wdPsName) {
        this.wdPsName = wdPsName;
    }

    public String getWdCutbalDate() {
        return wdCutbalDate;
    }

    public void setWdCutbalDate(String wdCutbalDate) {
        this.wdCutbalDate = wdCutbalDate;
    }

    public String getWdBgyId() {
        return wdBgyId;
    }

    public void setWdBgyId(String wdBgyId) {
        this.wdBgyId = wdBgyId;
    }

    public String getWdActive() {
        return wdActive;
    }

    public void setWdActive(String wdActive) {
        this.wdActive = wdActive;
    }
}
