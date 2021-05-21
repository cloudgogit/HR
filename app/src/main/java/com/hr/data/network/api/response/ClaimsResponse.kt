package com.hr.data.network.api.response

data class ClaimsResponse(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val date: String,
        val department: String,
        val empname: String,
        val id: Int,
        val photo: String,
        val purpose: String,
        val status: String,
        val totalamount: Int,
        val userid: String
    )
}