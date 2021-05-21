package com.hr.data.network.api.service


import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.ClaimDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClaimDetailsApi {

    @POST("claim_detail.php")
    suspend fun getClaimDetails(

        @Body requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo
    ): Response<ClaimDetailResponse>
}