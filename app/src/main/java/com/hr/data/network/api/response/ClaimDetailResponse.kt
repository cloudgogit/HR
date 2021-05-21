package com.hr.data.network.api.response

data class ClaimDetailResponse(
    val msg: String,
    val res: List<Re>,
    val status: String
) {
    data class Re(
        val amount_1: String,
        val amount_2: String,
        val amount_3: String,
        val amount_4: String,
        val amount_5: String,
        val bill_date_1: String,
        val bill_date_2: String,
        val bill_date_3: String,
        val bill_date_4: String,
        val bill_date_5: String,
        val date_from: String,
        val date_to: String,
        val department: String,
        val description: String,
        val empname: String,
        val id: Int,
        val photo: String,
        val purpose: String,
        val purpose_1: String,
        val purpose_2: String,
        val purpose_3: String,
        val purpose_4: String,
        val purpose_5: String,
        val receipt_1: String,
        val receipt_2: String,
        val receipt_3: String,
        val receipt_4: String,
        val receipt_5: String,
        val receiptno_1: String,
        val receiptno_2: String,
        val receiptno_3: String,
        val receiptno_4: String,
        val receiptno_5: String,
        val remarks_1: String,
        val remarks_2: String,
        val remarks_3: String,
        val remarks_4: String,
        val remarks_5: String,
        val status: String,
        val totalamount: Int,
        val userid: String
    )
}