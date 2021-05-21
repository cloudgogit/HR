package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestFollowUpComplete
import com.hr.data.network.api.response.ResponseFollowUpCompleted
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FollowUpCompleteApi {

    @POST("followup_update.php")
    suspend fun upDateFollowUp(

        @Body requestFollowUpComplete: RequestFollowUpComplete
    ): Response<ResponseFollowUpCompleted>
}