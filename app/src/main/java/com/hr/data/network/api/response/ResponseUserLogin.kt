package com.hr.data.network.api.response

data class ResponseUserLogin(
    val msg: String,
    val token: String,
    val status: String
)