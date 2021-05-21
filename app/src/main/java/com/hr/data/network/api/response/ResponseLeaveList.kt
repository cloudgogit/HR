package com.hr.data.network.api.response

data class ResponseLeaveList(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val date: String,
        val employee_name: String,
        val id: Int,
        val reason: String
    )
}