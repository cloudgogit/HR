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
import com.hr.data.network.api.request.ClaimRequest
import com.hr.data.network.api.request.RequestLeaveApproval
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.repository.ClaimRespository
import com.hr.data.network.repository.UserLoginRespository

import kotlinx.coroutines.launch

class ClaimViewModel(
    private val repository: ClaimRespository,
    val context: Context
) : ViewModel() {

    fun reqGetClaimList(
        claimRequest: ClaimRequest,
        onSuccess: OnSuccess<ClaimsResponse>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqGetClaimList(
                claimRequest,onSuccess, onError)
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


}