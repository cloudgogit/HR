package com.hr.data.network.api.service


import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.ClaimDetailResponse
import com.hr.data.network.api.response.ResponseAttendance
import com.hr.data.network.api.response.ResponseAttendanceDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AttendanceApi {

    @POST("attendance.php")
    suspend fun getAttendance(

        @Body requestAttendance: RequestAttendance
    ): Response<ResponseAttendance>


    @POST("attendance_detail.php")
    suspend fun getAttendanceDetail(

        @Body requestAttendanceDetails: RequestAttendanceDetails
    ): Response<ResponseAttendanceDetails>
}