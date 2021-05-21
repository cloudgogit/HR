/*
 * *
 *  * Created by Nethaji on 27/6/20 1:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27/6/20 1:32 PM
 *
 */

package com.hr.data.network.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hr.data.network.api.request.*
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseAttendance
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.repository.AttendanceRespository
import com.hr.data.network.repository.ClaimRespository
import com.hr.data.network.repository.UserLoginRespository
import com.hr.data.network.api.response.ResponseAttendanceDetails


import kotlinx.coroutines.launch

class AttendanceViewModel(
    private val repository: AttendanceRespository,
    val context: Context
) : ViewModel() {

    fun reqAttendance(
        requestAttendance: RequestAttendance,
        onSuccess: OnSuccess<ResponseAttendance>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqAttendance(
                requestAttendance,onSuccess, onError)
        }
    }

    fun reqAttendanceDetails(
        requestAttendanceDetails: RequestAttendanceDetails,
        onSuccess: OnSuccess<ResponseAttendanceDetails>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqAttendanceDetails(
                requestAttendanceDetails,onSuccess, onError)
        }
    }


}