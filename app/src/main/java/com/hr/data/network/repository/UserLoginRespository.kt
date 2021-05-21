package com.hr.data.network.repository

import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.api.service.UserLoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class UserLoginRespositoryImpl(private val api : UserLoginApi) :
    UserLoginRespository {
    override suspend fun reqUserLogin(
        requestUserLogin: RequestUserLogin,
        onSuccess: OnSuccess<ResponseUserLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.reqUserLogin(requestUserLogin = requestUserLogin)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status.equals("success"))
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.msg) }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(response.message().toString())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {}
            }
        }

    }


}


interface UserLoginRespository {

    suspend fun reqUserLogin(
       requestUserLogin: RequestUserLogin,
        onSuccess: OnSuccess<ResponseUserLogin>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: UserLoginApi): UserLoginRespository =
            UserLoginRespositoryImpl(api)
    }

}