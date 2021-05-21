package com.hr.data.network.api.service;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class ImageUploadService {
    interface Service {
        @Multipart
        @POST("/")
        Call<ResponseBody> postImage( @Part("token") RequestBody token,
                                      @Part("empid") RequestBody empid,
                                      @Part("epfno") RequestBody epfno,
                                      @Part("epf_table") RequestBody epf_table,
                                      @Part("socso_icno") RequestBody socso_icno,
                                      @Part("socso_table") RequestBody socso_table,
                                      @Part("eis_table") RequestBody eis_table,
                                      @Part("incometax_no") RequestBody incometax_no,
                                      @Part("pcb_table") RequestBody pcb_table,
                                      @Part("bank_name") RequestBody bank_name,
                                      @Part("bank_branch") RequestBody bank_branch,
                                      @Part("bank_acno") RequestBody bank_acno,
                                      @Part MultipartBody.Part image,
                                      @Part MultipartBody.Part image1);
    }
}