package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestEmployeeEmployeement
import com.hr.data.network.api.request.RequestEmployeePersonalInfo
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.response.ResponseAddEmployee
import com.hr.data.network.api.response.ResponseFollowUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddEmployeeEmployeeMentInfoApi {

    @POST("update_employee_step2.php")
    suspend fun addEmployeePersonlInfoApi(

        @Body requestEmployeeEmployeement: RequestEmployeeEmployeement
    ): Response<ResponseAddEmployee>
}