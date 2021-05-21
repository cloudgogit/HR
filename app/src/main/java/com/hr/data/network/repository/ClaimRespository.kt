package com.hr.data.network.repository

import com.hr.data.network.api.request.ClaimRequest
import com.hr.data.network.api.request.RequestLeaveApproval
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseUserLogin
import com.hr.data.network.api.service.ClaimApi
import com.hr.data.network.api.service.UserLoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class ClaimRespositoryImpl(private val api : ClaimApi) :
    ClaimRespository {
    override suspend fun reqGetClaimList(
        claimRequest: ClaimRequest,
        onSuccess: OnSuccess<ClaimsResponse>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getClaimList(claimRequest = claimRequest)
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

    override suspend fun updateClaimApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.getClaimApproval(requestLeaveApproval = requestLeaveApproval)
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


interface ClaimRespository {

    suspend fun reqGetClaimList(
        claimRequest: ClaimRequest,
        onSuccess: OnSuccess<ClaimsResponse>,
        onError: OnError<String>
    )

    suspend fun updateClaimApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: ClaimApi): ClaimRespository =
            ClaimRespositoryImpl(api)
    }

}