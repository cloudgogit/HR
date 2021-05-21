package com.hr.data.network.api.response

data class ResponseAttendance(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val attendance: String,
        val date: String,
        val department: String,
        val empname: String,
        val id: Int,
        val photo: String,
        val signin: String,
        val signout: String,
        val userid: String,
        val workhours: String
    )
}