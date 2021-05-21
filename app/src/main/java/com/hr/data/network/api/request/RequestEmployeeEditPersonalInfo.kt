package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestEmployeeEditPersonalInfo(
    @SerializedName("token") val token: String? = "",
    @SerializedName("id") val id: String? = ""

)

