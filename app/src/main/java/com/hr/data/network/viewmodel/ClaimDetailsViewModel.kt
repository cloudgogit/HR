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
import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.response.ClaimDetailResponse
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.repository.ClaimDetailsRespository

import kotlinx.coroutines.launch

class ClaimDetailsViewModel(
    private val repository: ClaimDetailsRespository,
    val context: Context
) : ViewModel() {

    fun reqClaimDetails(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ClaimDetailResponse>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqClaimDetails(
                requestEmployeeEditPersonalInfo,onSuccess, onError)
        }
    }

}