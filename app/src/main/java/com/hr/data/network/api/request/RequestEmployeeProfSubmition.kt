package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestEmployeeProfSubmition(
    @SerializedName("token") val token: String? = "",
    @SerializedName("empid") val empid: String? = "",
    @SerializedName("epfno") val epfno: String? = "",
    @SerializedName("epf_table") val epf_table: String? = "",
    @SerializedName("socso_icno") val socso_icno: String? = "",
    @SerializedName("socso_table") val socso_table: String? = "",
    @SerializedName("eis_table") val eis_table: String? = "",
    @SerializedName("incometax_no") val incometax_no: String? = "",
    @SerializedName("pcb_table") val pcb_table: String? = "",
    @SerializedName("bank_name") val bank_name: String? = "",
    @SerializedName("bank_branch") val bank_branch: String? = "",
    @SerializedName("bank_acno") val bank_acno: String? = "",
    @SerializedName("photo") val photo: String? = "",
@SerializedName("ic_passport") val ic_passport: String? = ""

)

