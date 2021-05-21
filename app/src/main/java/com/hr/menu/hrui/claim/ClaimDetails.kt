package com.hr.menu.hrui.claim

import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.response.ResponseLeaveList

interface ClaimDetails {

    fun claimDetails(_category: ClaimsResponse.Re)

    fun claimApprovel(type: String, _category: ClaimsResponse.Re)

}