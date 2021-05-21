/*
 * *
 *  * Created by Nethaji on 27/6/20 1:17 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27/6/20 1:17 PM
 *
 */


import android.content.Context
import android.content.SharedPreferences


class PreferenceManager(private val context: Context) : IPreferenceManager {

    val pref: SharedPreferences =
        context.getSharedPreferences("community_app_preference", Context.MODE_PRIVATE)



    override fun setUserId(userId: String?) =
        pref.edit().putString(USER_ID, userId.toString()).apply()

    override fun getUserId(): String = pref.getString(USER_ID, "").toString()

    override fun setMobileNumber(mobileNo: String?) =
        pref.edit().putString(MOBILE_NUMBER, mobileNo.toString()).apply()


    override fun getMobileNumber(): String = pref.getString(MOBILE_NUMBER, "").toString()
    override fun setVehicleName(vehiclename: String?) =   pref.edit().putString(VEHICLE_ID, vehiclename.toString()).apply()

    override fun getVehicleName(): String = pref.getString(VEHICLE_ID, "").toString()


    override fun setEmployeeId(empid: String?)  = pref.edit().putString(EMP_ID, empid.toString()).apply()

    override fun setIsEmpEdit(empedit: String?) = pref.edit().putString(Employee_EDIT, empedit.toString()).apply()


    override fun getEmployeeId(): String  = pref.getString(EMP_ID, "").toString()


    override fun getIsEmpEdit(): String = pref.getString(Employee_EDIT, "").toString()

    companion object {

        const val USER_ID = "user_id_employee"
        const val EMP_ID = "emp_id"
        const val MOBILE_NUMBER = "mobile_number_employee"
        const val VEHICLE_ID = "vehicle_id_employee"
        const val Employee_EDIT = "employee_edit"
    }
}


