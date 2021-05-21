package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestEmployeeProfSubmition
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestTables
import com.hr.data.network.api.response.ResponseAddEmployeement
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.api.response.ResponseTable
import com.hr.data.network.api.service.AllFollowUpApi
import com.hr.data.network.api.service.EmployeeProfApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class EmployeeProfRespositoryImpl(private val api : EmployeeProfApi) :
    EmployeeProfRespository {
    override suspend fun reqTable(
        requestTables : RequestTables,
        onSuccess: OnSuccess<ResponseTable>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getTableList( requestTables = requestTables)
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

    override suspend fun updateproof(
        requestEmployeeProfSubmition: RequestEmployeeProfSubmition,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {


        withContext(Dispatchers.IO) {
            try {
                val response = api.updateEmployee( requestEmployeeProfSubmition = requestEmployeeProfSubmition)
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


interface EmployeeProfRespository {

    suspend fun reqTable(
    requestTables: RequestTables,
        onSuccess: OnSuccess<ResponseTable>,
        onError: OnError<String>
    )


    suspend fun updateproof(
       requestEmployeeProfSubmition: RequestEmployeeProfSubmition,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )

    companion object Factory {
        fun create(api: EmployeeProfApi): EmployeeProfRespository =
            EmployeeProfRespositoryImpl(api)
    }

}