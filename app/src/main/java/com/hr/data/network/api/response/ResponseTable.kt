package com.hr.data.network.api.response

data class ResponseTable(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val id: Int,
        val type_value: String
    )
}