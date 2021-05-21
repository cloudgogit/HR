

interface IPreferenceManager {


    fun setUserId(userId: String?)
    fun getUserId(): String


    fun setMobileNumber(mobileNo: String?)
    fun getMobileNumber(): String

    fun setVehicleName(username: String?)
    fun getVehicleName(): String

    fun setEmployeeId(empid: String?)
    fun getEmployeeId(): String

    fun setIsEmpEdit(empedit: String?)
    fun getIsEmpEdit(): String


}