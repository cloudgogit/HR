package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestList(
    @SerializedName("token") val token: String? = ""
)
