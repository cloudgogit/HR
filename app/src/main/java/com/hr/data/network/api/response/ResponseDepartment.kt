package com.hr.data.network.api.response

data class ResponseDepartment(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val dept_name: String,
        val id: Int
    )
}