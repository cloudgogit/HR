package com.hr.data.network.repository

import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.service.AllFollowUpApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class AllFollowUpRespositoryImpl(private val api : AllFollowUpApi) :
    AllFollowUpRespository {
    override suspend fun reqAllFollowUP(
        requestFollowUp: RequestFollowUp,
        onSuccess: OnSuccess<ResponseFollowUp>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getAllFollowUpList( requestFollowUp = requestFollowUp)
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


interface AllFollowUpRespository {

    suspend fun reqAllFollowUP(
    requestFollowUp: RequestFollowUp,
        onSuccess: OnSuccess<ResponseFollowUp>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: AllFollowUpApi): AllFollowUpRespository =
            AllFollowUpRespositoryImpl(api)
    }

}