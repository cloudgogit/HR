package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.ResponseUserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {

    @POST("login.php")
    suspend fun reqUserLogin(

        @Body requestUserLogin: RequestUserLogin
    ): Response<ResponseUserLogin>
}