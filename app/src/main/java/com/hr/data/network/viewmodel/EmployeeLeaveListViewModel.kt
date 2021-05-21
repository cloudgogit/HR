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
import com.hr.data.network.api.request.RequestLeaveApproval
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseLeaveList
import com.hr.data.network.repository.EmployeeLeaveListRespository


import kotlinx.coroutines.launch

class EmployeeLeaveListViewModel(
    private val repository: EmployeeLeaveListRespository,
    val context: Context
) : ViewModel() {

    fun reqLeaveListUP(
        requestList: RequestList,
            onSuccess: OnSuccess<ResponseLeaveList>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqLeaveList(
                requestList,onSuccess, onError)
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