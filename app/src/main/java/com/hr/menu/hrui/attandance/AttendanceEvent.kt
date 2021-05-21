package com.hr.menu.hrui.attandance

import com.hr.data.network.api.response.ResponseAttendance
import com.hr.data.network.api.response.ResponseFollowUp


interface AttendanceEvent {

    fun viewWorkTime(_category: ResponseAttendance.Re)

    fun manualAttendance(_category: ResponseAttendance.Re)
}