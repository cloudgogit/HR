package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestLeaveApproval
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseEditEmploeePersonalInfo
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.response.ResponseLeaveList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LeaveApi {

    @POST("leave_requests.php")
    suspend fun getLeaveList(

        @Body requestList: RequestList
    ): Response<ResponseLeaveList>

    @POST("leave_requests_update.php")
    suspend fun getLeaveApproval(

        @Body requestLeaveApproval: RequestLeaveApproval
    ): Response<ResponseAddEmployeement>
}