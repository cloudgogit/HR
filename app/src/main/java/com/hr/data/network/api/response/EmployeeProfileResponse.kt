package com.hr.data.network.api.response

data class EmployeeProfileResponse(
    val msg: String,
    val res: Res,
    val status: String
) {
    data class Res(
        val department: String,
        val emailid: String,
        val emergency_contactno: String,
        val emergency_name: String,
        val emergency_relationship: String,
        val empname: String,
        val icno: String,
        val marital_status: String,
        val photo: String,
        val userid: String
    )
}