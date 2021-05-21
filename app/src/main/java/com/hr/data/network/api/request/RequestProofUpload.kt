package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestProofUpload(
    @SerializedName("userid") val userid: String? = ""
)
