/*
 * *
 *  * Created by Nethaji on 27/6/20 1:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27/6/20 1:13 PM
 *
 */




import com.hr.data.network.api.service.*
import com.hr.data.network.repository.*
import com.hr.data.network.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Networking modules here
 * Must be a singleton
 * Also, using the default overload methods
 **/
val NETWORKING_MODULE = module {
    single { HttpClientManager.newInstance(androidContext()) }
    single { get<HttpClientManager>().createApi<UserLoginApi>() }
    single { get<HttpClientManager>().createApi<LeaveApi>() }
    single { get<HttpClientManager>().createApi<FollowUpCompleteApi>() }
    single { get<HttpClientManager>().createApi<AllFollowUpApi>() }
    single { get<HttpClientManager>().createApi<EmployeeEmployeeDepartmentApi>() }
    single { get<HttpClientManager>().createApi<AddEmployeePersonlInfoApi>() }
    single { get<HttpClientManager>().createApi<EmployeeProfApi>() }
    single { get<HttpClientManager>().createApi<ClaimApi>() }
    single { get<HttpClientManager>().createApi<ClaimDetailsApi>() }
    single { get<HttpClientManager>().createApi<DashBoardApi>() }
    single { get<HttpClientManager>().createApi<AttendanceApi>() }

    single { get<HttpClientManager>().createApi<ProfileApi>() }
}

/**
 * Repository modules here
 * Must be a singleton
 **/
val REPOSITORY_MODULE = module {
    single { UserLoginRespository.create(get()) }
    single { EmployeeLeaveListRespository.create(get()) }
    single { FollowUpCompleteRespository.create(get()) }
    single { AllFollowUpRespository.create(get()) }
    single { EmployeeEmployeeDepartmentRespository.create(get()) }
    single { EmployeeAddPersonalIfoRespository.create(get()) }
    single { EmployeeProfRespository.create(get()) }
    single { ClaimRespository.create(get()) }
    single { ClaimDetailsRespository.create(get()) }
   single { DashBoardRespository.create(get()) }
  single { EditEmployeeProfRespository.create(get()) }
    single { AttendanceRespository.create(get()) }
    single { ProfileRespository.create(get()) }


}

/**
 * ViewModel modules here
 **/
val VIEW_MODEL_MODULE = module {
    viewModel { UserLoginViewModel(get(), androidContext()) }
    viewModel { EmployeeLeaveListViewModel(get(), androidContext()) }
    viewModel { FollowUpCompletedViewModel(get(), androidContext()) }
    viewModel { EmployeeListViewModel(get(), androidContext()) }
    viewModel { EmployeeDepoartmentViewModel(get(), androidContext()) }
    viewModel { EmployeeAddPersonalViewViewModel(get(), androidContext()) }
    viewModel { EmployeeProfViewModel(get(), androidContext()) }
    viewModel { ClaimViewModel(get(), androidContext()) }
    viewModel { ClaimDetailsViewModel(get(), androidContext()) }
    viewModel { DashBoardViewModel(get(), androidContext()) }
    viewModel { EditEmployeeProfViewModel(get(), androidContext()) }
    viewModel { AttendanceViewModel(get(), androidContext()) }
    viewModel { UserProfileViewModel(get(), androidContext()) }


}

