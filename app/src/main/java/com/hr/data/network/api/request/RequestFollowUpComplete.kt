package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestFollowUpComplete(
    @SerializedName("userid") val userid: String? = "",
    @SerializedName("enquiry_id") val enquiry_id: String? = "",
    @SerializedName("comments") val comments: String? = "",
    @SerializedName("status") val status: String? = "",
    @SerializedName("payment_mode") val payment_mode: String? = "",
    @SerializedName("payment_status") val payment_status: String? = "",
    @SerializedName("cash_received") val cash_received: String? = "",
    @SerializedName("bill_number") val bill_number: String? = "",
    @SerializedName("followup_date") val followup_date: String? = "",
    @SerializedName("followup_time") val followup_time: String? = ""
)
