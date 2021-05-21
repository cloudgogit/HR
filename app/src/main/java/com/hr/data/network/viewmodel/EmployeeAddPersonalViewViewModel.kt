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
import com.hr.data.network.repository.EmployeeAddPersonalIfoRespository
import com.hr.data.network.repository.EmployeeEmployeeDepartmentRespository

import kotlinx.coroutines.launch

class EmployeeAddPersonalViewViewModel(
    private val repository: EmployeeAddPersonalIfoRespository,
    val context: Context
) : ViewModel() {

    fun reqAddEmployeePersonalInfo(
        requestEmployeePersonalInfo: RequestEmployeePersonalInfo,
            onSuccess: OnSuccess<ResponseAddEmployee>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqAddEmployeePersonalInfo(
                requestEmployeePersonalInfo,onSuccess, onError)
        }
    }

    fun reqEditEmployeePersonalInfo(
        equestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmploeePersonalInfo>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqEditEmployeePersonalInfo(
                equestEmployeeEditPersonalInfo,onSuccess, onError)
        }
    }

    fun reqUpdateEmployeePersonalInfo(
        requestUpdateEmployeePersonalInfo: RequestUpdateEmployeePersonalInfo,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqUpdateEmployeePersonalInfo(
                requestUpdateEmployeePersonalInfo,onSuccess, onError)
        }
    }


}