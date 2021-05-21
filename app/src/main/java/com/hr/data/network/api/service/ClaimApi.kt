package com.hr.data.network.api.service


import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseAddEmployee
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseFollowUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClaimApi {

    @POST("claims.php")
    suspend fun getClaimList(

        @Body claimRequest: ClaimRequest
    ): Response<ClaimsResponse>

    @POST("claim_update.php")
    suspend fun getClaimApproval(

        @Body requestLeaveApproval: RequestLeaveApproval
    ): Response<ResponseAddEmployeement>
}