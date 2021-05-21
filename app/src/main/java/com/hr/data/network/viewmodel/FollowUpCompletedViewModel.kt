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
import com.hr.data.network.api.request.RequestFollowUpComplete
import com.hr.data.network.api.response.ResponseFollowUpCompleted
import com.hr.data.network.repository.FollowUpCompleteRespository

import kotlinx.coroutines.launch

class FollowUpCompletedViewModel(
    private val repository: FollowUpCompleteRespository,
    val context: Context
) : ViewModel() {

    fun reqFollowUP(
        requestFollowUpComplete: RequestFollowUpComplete,
            onSuccess: OnSuccess<ResponseFollowUpCompleted>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqFollowUPComplete(
                requestFollowUpComplete,onSuccess, onError)
        }
    }

}