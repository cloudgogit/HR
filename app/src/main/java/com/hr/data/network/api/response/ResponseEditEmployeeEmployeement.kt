package com.hr.data.network.api.response

data class ResponseEditEmployeeEmployeement(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val branch: String,
        val department: String,
        val designation: String,
        val doj: String,
        val employement_type: String,
        val employment_pass: String,
        val id: Int,
        val immegrationno: String,
        val paybasis: String,
        val payment_mode: String,
        val work_permit: String
    )
}