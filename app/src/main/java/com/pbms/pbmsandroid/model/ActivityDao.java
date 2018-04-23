package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class ActivityDao {

    @SerializedName("acId")
    private String acId;

    @SerializedName("acCode")
    private String acCode;

    @SerializedName("acName")
    private String acName;

    @SerializedName("acBgyId")
    private String acBgyId;

    @SerializedName("acStId")
    private String acStId;

    @SerializedName("acOrigin")
    private String acOrigin;

    @SerializedName("type")
    private String type;

    @SerializedName("acSpend")
    private String acSpend;

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getAcCode() {
        return acCode;
    }

    public void setAcCode(String acCode) {
        this.acCode = acCode;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcBgyId() {
        return acBgyId;
    }

    public void setAcBgyId(String acBgyId) {
        this.acBgyId = acBgyId;
    }

    public String getAcStId() {
        return acStId;
    }

    public void setAcStId(String acStId) {
        this.acStId = acStId;
    }

    public String getAcOrigin() {
        return acOrigin;
    }

    public void setAcOrigin(String acOrigin) {
        this.acOrigin = acOrigin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcSpend() {
        return acSpend;
    }

    public void setAcSpend(String acSpend) {
        this.acSpend = acSpend;
    }
}
