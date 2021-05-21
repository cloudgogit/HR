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
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.repository.UserLoginRespository

import kotlinx.coroutines.launch

class UserLoginViewModel(
    private val repository: UserLoginRespository,
    val context: Context
) : ViewModel() {

    fun reqUserLogin(
           requestUserLogin: RequestUserLogin,
            onSuccess: OnSuccess<ResponseUserLogin>,
            onError: OnError<String>
    ) {
        viewModelScope.launch {
            repository.reqUserLogin(
                requestUserLogin,onSuccess, onError)
        }
    }

}