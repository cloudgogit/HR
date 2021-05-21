package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestEmployeeEmployeement(
    @SerializedName("token") val token: String? = "",
    @SerializedName("empid") val empid: String? = "",
    @SerializedName("designation") val designation: String? = "",
    @SerializedName("department") val department: String? = "",
    @SerializedName("branch") val branch: String? = "",
    @SerializedName("paybasis") val paybasis: String? = "",
    @SerializedName("payment_mode") val payment_mode: String? = "",
    @SerializedName("employement_type") val employement_type: String? = "",
    @SerializedName("work_permit") val work_permit: String? = "",
    @SerializedName("employment_pass") val employment_pass: String? = "",
    @SerializedName("immegrationno") val immegrationno: String? = "",
    @SerializedName("doj") val doj: String? = ""

)

