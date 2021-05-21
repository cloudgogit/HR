package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.response.ClaimDetailResponse
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.service.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class ClaimDetailsRespositoryImpl(private val api : ClaimDetailsApi) :
    ClaimDetailsRespository {
    override suspend fun reqClaimDetails(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ClaimDetailResponse>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getClaimDetails(requestEmployeeEditPersonalInfo = requestEmployeeEditPersonalInfo)
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


interface ClaimDetailsRespository {

    suspend fun reqClaimDetails(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ClaimDetailResponse>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: ClaimDetailsApi): ClaimDetailsRespository =
            ClaimDetailsRespositoryImpl(api)
    }

}