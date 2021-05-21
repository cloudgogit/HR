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
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.repository.AllFollowUpRespository

import kotlinx.coroutines.launch

class EmployeeListViewModel(
    private val repository: AllFollowUpRespository,
    val context: Context
) : ViewModel() {

    fun reqAllFollowUP(
         requestFollowUp: RequestFollowUp,
            onSuccess: OnSuccess<ResponseFollowUp>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqAllFollowUP(
                requestFollowUp,onSuccess, onError)
        }
    }

}