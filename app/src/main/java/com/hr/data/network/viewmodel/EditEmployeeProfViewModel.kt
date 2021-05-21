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
import com.hr.data.network.api.request.EditProfRequest
import com.hr.data.network.api.request.RequestEmployeeProfSubmition
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestTables
import com.hr.data.network.api.response.EditEmployeeProffRespose
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.response.ResponseTable
import com.hr.data.network.repository.AllFollowUpRespository
import com.hr.data.network.repository.EditEmployeeProfRespository
import com.hr.data.network.repository.EmployeeProfRespository

import kotlinx.coroutines.launch

class EditEmployeeProfViewModel(
    private val repository: EditEmployeeProfRespository,
    val context: Context
) : ViewModel() {

    fun reqTable(
        requestTables: RequestTables,
            onSuccess: OnSuccess<ResponseTable>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqTable(
                requestTables,onSuccess, onError)
        }
    }


    fun updateProof(
        requestEmployeeProfSubmition: RequestEmployeeProfSubmition,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.updateproof(
                requestEmployeeProfSubmition,onSuccess, onError)
        }
    }

    fun EditProof(
        editProfRequest: EditProfRequest,
        onSuccess: OnSuccess<EditEmployeeProffRespose>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.editeproof(
                editProfRequest,onSuccess, onError)
        }
    }

}