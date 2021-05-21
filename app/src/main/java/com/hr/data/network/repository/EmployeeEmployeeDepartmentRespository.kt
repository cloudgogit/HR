package com.hr.data.network.repository

import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.request.RequestEmployeeEmployeement
import com.mlm.recharege.di.OnError
import com.mlm.recharege.di.OnSuccess
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.*
import com.hr.data.network.api.service.AllFollowUpApi
import com.hr.data.network.api.service.EmployeeEmployeeDepartmentApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class EmployeeEmployeeDepartmentRespositoryImpl(private val api : EmployeeEmployeeDepartmentApi) :
    EmployeeEmployeeDepartmentRespository {
    override suspend fun reqDepartment(
        requestList: RequestList,
        onSuccess: OnSuccess<ResponseDepartment>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getDepartmentList( requestList = requestList)
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

    override suspend fun reqBranch(
        requestList: RequestList,
        onSuccess: OnSuccess<ResponseBranch>,
        onError: OnError<String>
    ) {


        withContext(Dispatchers.IO) {
            try {
                val response = api.getBranchList( requestList = requestList)
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

    override suspend fun reqAddEmployeementDetails(
        requestEmployeeEmployeement: RequestEmployeeEmployeement,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    ) {

        withContext(Dispatchers.IO) {
            try {
                val response = api.addEmployeePersonlInfoApi( requestEmployeeEmployeement = requestEmployeeEmployeement)
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

    override suspend fun reqEditEmployeementDetails(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmployeeEmployeement>,
        onError: OnError<String>
    ) {


        withContext(Dispatchers.IO) {
            try {
                val response = api.editEmployeeEmployeement( requestEmployeeEditPersonalInfo = requestEmployeeEditPersonalInfo)
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


interface EmployeeEmployeeDepartmentRespository {

    suspend fun reqDepartment(
    requestList: RequestList,
        onSuccess: OnSuccess<ResponseDepartment>,
        onError: OnError<String>
    )

    suspend fun reqBranch(
        requestList: RequestList,
        onSuccess: OnSuccess<ResponseBranch>,
        onError: OnError<String>
    )

    suspend fun reqAddEmployeementDetails(
        requestEmployeeEmployeement: RequestEmployeeEmployeement,
        onSuccess: OnSuccess<ResponseAddEmployeement>,
        onError: OnError<String>
    )


    suspend fun reqEditEmployeementDetails(
        requestEmployeeEditPersonalInfo: RequestEmployeeEditPersonalInfo,
        onSuccess: OnSuccess<ResponseEditEmployeeEmployeement>,
        onError: OnError<String>
    )
    companion object Factory {
        fun create(api: EmployeeEmployeeDepartmentApi): EmployeeEmployeeDepartmentRespository =
            EmployeeEmployeeDepartmentRespositoryImpl(api)
    }

}