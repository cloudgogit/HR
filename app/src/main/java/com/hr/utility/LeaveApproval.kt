package com.hr.utility

import com.hr.data.network.api.response.ResponseLeaveList

interface LeaveApproval {

    fun leaveRequest(type: String, _category: ResponseLeaveList.Re)
}