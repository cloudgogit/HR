package com.hr.data.network.api.service


import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.request.RequestEmployeeEmployeement
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EmployeeEmployeeDepartmentApi {

    @POST("department.php")
    suspend fun getDepartmentList(

        @Body requestList: RequestList
    ): Response<ResponseDepartment>


    @POST("branch.php")
    suspend fun getBranchList(

        @Body requestList: RequestList
    ): Response<ResponseBranch>

    @POST("update_employee_step2.php")
    suspend fun addEmployeePersonlInfoApi(

        @Body requestEmployeeEmployeement: RequestEmployeeEmployeement
    ): Response<ResponseAddEmployeement>

    @POST("employee_step2.php")
    suspend fun editEmployeeEmployeement(

        @Body requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo
    ): Response<ResponseEditEmployeeEmployeement>


}