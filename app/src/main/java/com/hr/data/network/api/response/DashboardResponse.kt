package com.hr.data.network.api.response

data class DashboardResponse(
    val absent: Int,
    val claim: Claim,
    val late: String,
    val leave: Leave,
    val msg: String,
    val present: String,
    val sign: String,
    val start_time: String,
    val status: String,
    val workhours: String,
    val leave_data: String,
    val claim_data: String
) {
    data class Claim(
        val date: String,
        val department: String,
        val empname: String,
        val id: String,
        val photo: String,
        val purpose: String,
        val status: String,
        val totalamount: String,
        val userid: String
    )

    data class Leave(
        val dates: String,
        val department: String,
        val duration: String,
        val empname: String,
        val hours: String,
        val id: String,
        val photo: String,
        val posted_date: String,
        val reason: String,
        val status: String,
        val userid: String
    )
}