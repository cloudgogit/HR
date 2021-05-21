package com.hr.data.network.repository

import com.hr.data.network.api.request.*
import com.hr.data.network.api.response.*
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.service.AddEmployeePersonlInfoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class EmployeeAddPersonalIfoRespositoryImpl(private val api : AddEmployeePersonlInfoApi) :
    EmployeeAddPersonalIfoRespository {
    override suspend fun reqAddEmployeePersonalInfo(
     requestEmployeePersonalInfo: RequestEmployeePersonalInfo,
        onSuccess: OnSuccess<ResponseAddEmployee>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.addEmployeePersonlInfoApi( requestEmployeePersonalInfo = requestEmployeePersonalInfo)
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

    override suspend fun reqEditEmployeePersonalInfo(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmploeePersonalInfo>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.editEmployeePersonlInfoApi( requestEmployeeEditPersonalInfo = requestEmployeeEditPersonalInfo)
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

    override suspend fun reqUpdateEmployeePersonalInfo(
        requestUpdateEmployeePersonalInfo: RequestUpdateEmployeePersonalInfo,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {



        withContext(Dispatchers.IO) {
            try {
                val response = api.updateEmployeePersonlInfoApi( requestUpdateEmployeePersonalInfo = requestUpdateEmployeePersonalInfo)
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


interface EmployeeAddPersonalIfoRespository {

    suspend fun reqAddEmployeePersonalInfo(
        requestEmployeePersonalInfo: RequestEmployeePersonalInfo,
        onSuccess: OnSuccess<ResponseAddEmployee>,
        onError: OnError<String>
    )


    suspend fun reqEditEmployeePersonalInfo(
        equestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmploeePersonalInfo>,
        onError: OnError<String>
    )

    suspend fun reqUpdateEmployeePersonalInfo(
        requestUpdateEmployeePersonalInfo: RequestUpdateEmployeePersonalInfo,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )
    companion object Factory {
        fun create(api: AddEmployeePersonlInfoApi): EmployeeAddPersonalIfoRespository =
            EmployeeAddPersonalIfoRespositoryImpl(api)
    }

}