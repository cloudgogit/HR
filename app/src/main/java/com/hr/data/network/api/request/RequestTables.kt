package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestTables(
    @SerializedName("token") val token: String? = "",
    @SerializedName("type_name") val type_name: String? = ""
)
