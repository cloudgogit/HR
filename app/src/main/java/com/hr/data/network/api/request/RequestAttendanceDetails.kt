package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestAttendanceDetails(
    @SerializedName("token") val token: String? = "",
    @SerializedName("id") val id: String? = "",
    @SerializedName("date") val date: String? = ""
)

