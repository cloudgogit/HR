package com.hr.data.network.api.request

import com.google.gson.annotations.SerializedName

data class RequestUpdateEmployeePersonalInfo(
    @SerializedName("token") val token: String? = "",
    @SerializedName("id") val id: String? = "",

    @SerializedName("empname") val empname: String? = "",
    @SerializedName("dob") val dob: String? = "",
    @SerializedName("icno") val icno: String? = "",
    @SerializedName("gender") val gender: String? = "",
    @SerializedName("emailid") val emailid: String? = "",
    @SerializedName("race") val race: String? = "",
    @SerializedName("religion") val religion: String? = "",
    @SerializedName("current_address") val current_address: String? = "",
    @SerializedName("ic_address") val ic_address: String? = "",
    @SerializedName("physical_challenge") val physical_challenge: String? = "",
    @SerializedName("passportno") val passportno: String? = "",
    @SerializedName("passport_issuedate") val passport_issuedate: String? = "",
    @SerializedName("passport_expiredate") val passport_expiredate: String? = "",
    @SerializedName("marital_status") val marital_status: String? = "",
    @SerializedName("spouse_name") val spouse_name: String? = "",
    @SerializedName("spouse_icno") val spouse_icno: String? = "",
    @SerializedName("spouse_employement") val spouse_employement: String? = "",
    @SerializedName("children") val children: String? = "",
    @SerializedName("emergency_name") val emergency_name: String? = "",
    @SerializedName("emergency_contactno") val emergency_contactno: String? = "",
    @SerializedName("emergency_relationship") val emergency_relationship: String? = "",
    @SerializedName("emergency_relationship") val hraccess: String? = ""



)
