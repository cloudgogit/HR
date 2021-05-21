package com.hr.data.network.api.service


import com.hr.data.network.api.request.EditProfRequest
import com.hr.data.network.api.request.RequestEmployeeProfSubmition
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestTables
import com.hr.data.network.api.response.EditEmployeeProffRespose
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.response.ResponseTable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EmployeeProfApi {

    @POST("tables.php")
    suspend fun getTableList(
        @Body requestTables: RequestTables
    ): Response<ResponseTable>

    @POST("add_employee_step3.php")
    suspend fun updateEmployee(
        @Body requestEmployeeProfSubmition: RequestEmployeeProfSubmition
    ): Response<ResponseAddEmployeement>


    @POST("employee_step3.php")
    suspend fun EditEmployeeStep(
        @Body editProfRequest: EditProfRequest
    ): Response<EditEmployeeProffRespose>
}