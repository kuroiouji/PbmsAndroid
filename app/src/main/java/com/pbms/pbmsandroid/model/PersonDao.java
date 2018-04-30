package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 1/5/2561.
 */

public class PersonDao {
    @SerializedName("psId")
    private String psId;

    @SerializedName("psName")
    private String psName;

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }
}
