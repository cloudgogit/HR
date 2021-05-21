package com.hr.data.network.repository

import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.*
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.service.ClaimApi
import com.hr.data.network.api.service.DashBoardApi
import com.hr.data.network.api.service.UserLoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class DashBoardRespositoryImpl(private val api : DashBoardApi) :
    DashBoardRespository {
    override suspend fun reqGetDashBoardData(
        requestList: RequestList,
        onSuccess: OnSuccess<DashboardResponse>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getDashBoardDetails(requestList = requestList)
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

    override suspend fun updateWorkstatus(
        requestSignin: RequestSignin,
        onSuccess: OnSuccess<ResponseSignIn>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.requestUpdateSignin(requestSignin = requestSignin)
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


    override suspend fun updateLeaveApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.getLeaveApproval(requestLeaveApproval = requestLeaveApproval)
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


interface DashBoardRespository {

    suspend fun reqGetDashBoardData(
        requestList: RequestList,
        onSuccess: OnSuccess<DashboardResponse>,
        onError: OnError<String>
    )

    suspend fun updateWorkstatus(
        requestSignin: RequestSignin,
        onSuccess: OnSuccess<ResponseSignIn>,
        onError: OnError<String>
    )

    suspend fun updateClaimApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )

    suspend fun updateLeaveApproval(
        requestLeaveApproval: RequestLeaveApproval,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: DashBoardApi): DashBoardRespository =
            DashBoardRespositoryImpl(api)
    }

}