package com.hr.data.network.api.response

data class ResponseBranch(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val branch_name: String,
        val id: Int
    )
}