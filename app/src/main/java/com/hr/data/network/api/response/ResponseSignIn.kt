package com.hr.data.network.api.response

data class ResponseSignIn(
    val msg: String,
    val res: List<Any>,
    val start_time: String,
    val status: String,
    val workhours: Int
)