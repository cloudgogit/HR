package com.hr.menu.hrui.proof

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit


interface MyAPI {

    @Multipart
    @POST("upload_image.php")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): MyAPI {
             val okHttpClient: OkHttpClient by lazy {
                OkHttpClient.Builder().apply {

                    addNetworkInterceptor(StethoInterceptor())
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        }
                    )

                }
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    //  .callTimeout(25, TimeUnit.SECONDS)
                    .build()
            }

            return Retrofit.Builder()
                .baseUrl("http://www.puvnaa.com/demo/cloudgago/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()
                .create(MyAPI::class.java)
        }
    }
}