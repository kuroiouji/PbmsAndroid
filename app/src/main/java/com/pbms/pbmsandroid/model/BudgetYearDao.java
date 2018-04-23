package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BudgetYearDao {

    @SerializedName("yearId")
    private String yearId;

    @SerializedName("yearTitle")
    private String yearTitle;

    @SerializedName("arrBgy")
    private List<BudgetYear> arrBgy;

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getYearTitle() {
        return yearTitle;
    }

    public void setYearTitle(String yearTitle) {
        this.yearTitle = yearTitle;
    }

    public List<BudgetYear> getArrBgy() {
        return arrBgy;
    }

    public void setArrBgy(List<BudgetYear> arrBgy) {
        this.arrBgy = arrBgy;
    }
}
