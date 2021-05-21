package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestAttendance
import com.hr.data.network.api.request.RequestAttendanceDetails
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUpComplete
import com.hr.data.network.api.response.ResponseAttendance
import com.hr.data.network.api.response.ResponseAttendanceDetails
import com.hr.data.network.api.response.ResponseFollowUpCompleted
import com.hr.data.network.api.service.AttendanceApi
import com.hr.data.network.api.service.FollowUpCompleteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class AttendanceRespositoryImpl(private val api : AttendanceApi) :
    AttendanceRespository {
    override suspend fun reqAttendance(
        requestAttendance: RequestAttendance,
        onSuccess: OnSuccess<ResponseAttendance>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getAttendance( requestAttendance = requestAttendance)
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

    override suspend fun reqAttendanceDetails(
        requestAttendanceDetails: RequestAttendanceDetails,
        onSuccess: OnSuccess<ResponseAttendanceDetails>,
        onError: OnError<String>
    ) {


        withContext(Dispatchers.IO) {
            try {
                val response = api.getAttendanceDetail( requestAttendanceDetails = requestAttendanceDetails)
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


interface AttendanceRespository {

    suspend fun reqAttendance(
        requestAttendance: RequestAttendance,
        onSuccess: OnSuccess<ResponseAttendance>,
        onError: OnError<String>
    )



    suspend fun reqAttendanceDetails(
        requestAttendance: RequestAttendanceDetails,
        onSuccess: OnSuccess<ResponseAttendanceDetails>,
        onError: OnError<String>
    )
    companion object Factory {
        fun create(api: AttendanceApi): AttendanceRespository =
            AttendanceRespositoryImpl(api)
    }

}