package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.EmployeeProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApi {

    @POST("profile.php")
    suspend fun reqGetProfile(

        @Body requestList: RequestList
    ): Response<EmployeeProfileResponse>
}