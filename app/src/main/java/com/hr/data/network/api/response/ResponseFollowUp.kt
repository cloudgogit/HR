package com.hr.data.network.api.response

data class ResponseFollowUp(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val id: String,
        val photo: String,
        val empname: String,
        val department: String,
        val userid: String
    )
}