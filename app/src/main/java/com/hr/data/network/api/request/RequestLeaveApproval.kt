package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestLeaveApproval(
    @SerializedName("token") val token: String? = "",
    @SerializedName("id") val id: String? = "",

    @SerializedName("status") val status: String? = "",
    @SerializedName("rejected_reason") val rejected_reason: String? = ""


)
