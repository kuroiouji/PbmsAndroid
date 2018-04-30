package com.pbms.pbmsandroid.service;

import com.pbms.pbmsandroid.model.BudgetYearDao;
import com.pbms.pbmsandroid.model.PersonDao;
import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.model.StatusDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("getBudgetYear")
    Call<List<BudgetYearDao>> getBudgetYear();

    @GET("getStatus")
    Call<List<StatusDao>> getStatus();

    @FormUrlEncoded
    @POST("getProjectByYear")
    Call<List<ProjectDao>> getProjectByYear(@Field("bgyId") int id);

    @GET("getPerson")
    Call<List<PersonDao>> getPerson();
}
