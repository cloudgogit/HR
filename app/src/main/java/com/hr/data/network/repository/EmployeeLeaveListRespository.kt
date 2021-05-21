package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestLeaveApproval
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseLeaveList
import com.hr.data.network.api.service.LeaveApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class EmployeeLeaveListRespositoryImpl(private val api : LeaveApi) :
    EmployeeLeaveListRespository {
    override suspend fun reqLeaveList(
      requestList: RequestList,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getLeaveList( requestList = requestList)
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

    override suspend fun updateLeaveApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.getLeaveApproval( requestLeaveApproval = requestLeaveApproval)
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


interface EmployeeLeaveListRespository {

    suspend fun reqLeaveList(
  requestList: RequestList,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    )


    suspend fun updateLeaveApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )
    companion object Factory {
        fun create(api: LeaveApi): EmployeeLeaveListRespository =
            EmployeeLeaveListRespositoryImpl(api)
    }

}