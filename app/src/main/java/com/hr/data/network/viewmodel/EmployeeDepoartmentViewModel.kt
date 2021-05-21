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
import com.hr.data.network.api.response.*
import com.hr.data.network.repository.AllFollowUpRespository
import com.hr.data.network.repository.EmployeeEmployeeDepartmentRespository

import kotlinx.coroutines.launch

class EmployeeDepoartmentViewModel(
    private val repository: EmployeeEmployeeDepartmentRespository,
    val context: Context
) : ViewModel() {

    fun reqAllFollowUP(
         requestList: RequestList,
            onSuccess: OnSuccess<ResponseDepartment>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqDepartment(
                requestList,onSuccess, onError)
        }
    }


    fun reqBranchList(
        requestList: RequestList,
        onSuccess: OnSuccess<ResponseBranch>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqBranch(
                requestList,onSuccess, onError)
        }
    }

    fun reqAddEmployeeEmployeement(
        requestEmployeeEmployeement: RequestEmployeeEmployeement,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqAddEmployeementDetails(
                requestEmployeeEmployeement,onSuccess, onError)
        }
    }


    fun reqEditEmployeeEmployeement(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmployeeEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqEditEmployeementDetails(
                requestEmployeeEditPersonalInfo,onSuccess, onError)
        }
    }





}