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
import com.hr.data.network.api.response.*
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.repository.ClaimRespository
import com.hr.data.network.repository.DashBoardRespository
import com.hr.data.network.repository.UserLoginRespository

import kotlinx.coroutines.launch

class DashBoardViewModel(
    private val repository: DashBoardRespository,
    val context: Context
) : ViewModel() {

    fun reqGetDashBoardData(
        requestList: RequestList,
        onSuccess: OnSuccess<DashboardResponse>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqGetDashBoardData(
                requestList,onSuccess, onError)
        }
    }

    fun updateWorkstatus(
        requestSignin: RequestSignin,
        onSuccess: OnSuccess<ResponseSignIn>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.updateWorkstatus(
                requestSignin,onSuccess, onError)
        }
    }

    fun updateClaimApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.updateClaimApproval(
                requestLeaveApproval,onSuccess, onError)
        }
    }

    fun updateLeaveApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.updateLeaveApproval(
                requestLeaveApproval,onSuccess, onError)
        }
    }


}