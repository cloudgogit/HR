package com.hr.data.network.api.service


import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DashBoardApi {

    @POST("dashboard.php")
    suspend fun getDashBoardDetails(

        @Body requestList: RequestList
    ): Response<DashboardResponse>

    @POST("signin.php")
    suspend fun requestUpdateSignin(

        @Body requestSignin: RequestSignin
    ): Response<ResponseSignIn>

    @POST("claim_update.php")
    suspend fun getClaimApproval(

        @Body requestLeaveApproval: RequestLeaveApproval
    ): Response<ResponseAddEmployeement>

    @POST("leave_requests_update.php")
    suspend fun getLeaveApproval(

        @Body requestLeaveApproval: RequestLeaveApproval
    ): Response<ResponseAddEmployeement>
}