package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class ClaimRequest(
    @SerializedName("token") val token: String? = "",
    @SerializedName("page") val page: String? = ""

)

