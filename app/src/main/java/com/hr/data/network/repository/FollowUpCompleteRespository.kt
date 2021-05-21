package com.hr.data.network.repository

import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUpComplete
import com.hr.data.network.api.response.ResponseFollowUpCompleted
import com.hr.data.network.api.service.FollowUpCompleteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class FollowUpCompleteRespositoryImpl(private val api : FollowUpCompleteApi) :
    FollowUpCompleteRespository {
    override suspend fun reqFollowUPComplete(
        requestFollowUpComplete: RequestFollowUpComplete,
        onSuccess: OnSuccess<ResponseFollowUpCompleted>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.upDateFollowUp( requestFollowUpComplete = requestFollowUpComplete)
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


interface FollowUpCompleteRespository {

    suspend fun reqFollowUPComplete(
    requestFollowUpComplete: RequestFollowUpComplete,
        onSuccess: OnSuccess<ResponseFollowUpCompleted>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: FollowUpCompleteApi): FollowUpCompleteRespository =
            FollowUpCompleteRespositoryImpl(api)
    }

}