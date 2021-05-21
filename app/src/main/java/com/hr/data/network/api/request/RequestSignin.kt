package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName



data class RequestSignin(
    @SerializedName("token") val token: String? = "",
    @SerializedName("type") val type: String? = "",
    @SerializedName("latitude") val latitude: String? = "",
    @SerializedName("longitude") val longitude: String? = ""

)

