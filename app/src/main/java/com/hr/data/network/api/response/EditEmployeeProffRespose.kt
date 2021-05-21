package com.hr.data.network.api.response

data class EditEmployeeProffRespose(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val bank_acno: String,
        val bank_branch: String,
        val bank_name: String,
        val eis_table: String,
        val epf_table: String,
        val epfno: String,
        val ic_passport: String,
        val id: Int,
        val incometax_no: String,
        val pcb_table: String,
        val photo: String,
        val socso_icno: String,
        val socso_table: String
    )
}