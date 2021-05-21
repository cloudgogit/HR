package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.response.ResponseFollowUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AllFollowUpApi {

    @POST("employee.php")
    suspend fun getAllFollowUpList(

        @Body requestFollowUp: RequestFollowUp
    ): Response<ResponseFollowUp>
}