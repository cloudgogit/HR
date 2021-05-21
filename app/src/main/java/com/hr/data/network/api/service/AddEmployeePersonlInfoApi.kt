package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.request.RequestEmployeePersonalInfo
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestUpdateEmployeePersonalInfo
import com.hr.data.network.api.response.ResponseAddEmployee
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseEditEmploeePersonalInfo
import com.hr.data.network.api.response.ResponseFollowUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddEmployeePersonlInfoApi {

    @POST("add_employee_step1.php")
    suspend fun addEmployeePersonlInfoApi(

        @Body requestEmployeePersonalInfo: RequestEmployeePersonalInfo
    ): Response<ResponseAddEmployee>

    @POST("employee_step1.php")
    suspend fun editEmployeePersonlInfoApi(

        @Body requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo
    ): Response<ResponseEditEmploeePersonalInfo>


    @POST("update_employee_step1.php")
    suspend fun updateEmployeePersonlInfoApi(

        @Body requestUpdateEmployeePersonalInfo: RequestUpdateEmployeePersonalInfo    ):
            Response<ResponseAddEmployeement>
}