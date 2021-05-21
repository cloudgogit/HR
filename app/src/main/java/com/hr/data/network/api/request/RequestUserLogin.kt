package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestUserLogin(
    @SerializedName("username") val username: String? = "",
    @SerializedName("password") val password: String? = ""
)
