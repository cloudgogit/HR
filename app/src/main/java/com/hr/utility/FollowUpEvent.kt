package com.hr.utility

import com.hr.data.network.api.response.ResponseFollowUp


interface FollowUpEvent {

    fun callCustomer(_category: ResponseFollowUp.Re)

    fun customerDetails(_category: ResponseFollowUp.Re)
}