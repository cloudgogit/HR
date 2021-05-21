package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestList
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.EmployeeProfileResponse
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.api.service.ProfileApi
import com.hr.data.network.api.service.UserLoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class ProfileRespositoryImpl(private val api : ProfileApi) :
    ProfileRespository {
    override suspend fun reqGetUserDetails(
        requestList: RequestList,
        onSuccess: OnSuccess<EmployeeProfileResponse>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.reqGetProfile(requestList = requestList)
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


interface ProfileRespository {

    suspend fun reqGetUserDetails(
      requestList: RequestList,
        onSuccess: OnSuccess<EmployeeProfileResponse>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: ProfileApi): ProfileRespository =
            ProfileRespositoryImpl(api)
    }

}