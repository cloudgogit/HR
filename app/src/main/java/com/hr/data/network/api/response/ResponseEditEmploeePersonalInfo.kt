package com.hr.data.network.api.response

data class ResponseEditEmploeePersonalInfo(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val children: String,
        val current_address: String,
        val dob: String,
        val emailid: String,
        val emergency_contactno: String,
        val emergency_name: String,
        val emergency_relationship: String,
        val empname: String,
        val gender: String,
        val ic_address: String,
        val icno: String,
        val id: Int,
        val marital_status: String,
        val passport_expiredate: String,
        val passport_issuedate: String,
        val passportno: String,
        val physical_challenge: String,
        val race: String,
        val religion: String,
        val spouse_employement: String,
        val spouse_icno: String,
        val spouse_name: String,
        val hraccess: String
    )
}