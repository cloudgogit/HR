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
import com.hr.data.network.api.request.RequestList
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.EmployeeProfileResponse
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.repository.ProfileRespository
import com.hr.data.network.repository.UserLoginRespository

import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val repository: ProfileRespository,
    val context: Context
) : ViewModel() {

    fun reqGetUserDetails(
           requestList: RequestList,
            onSuccess: OnSuccess<EmployeeProfileResponse>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqGetUserDetails(
                requestList,onSuccess, onError)
        }
    }

}