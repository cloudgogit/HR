package com.hr.menu.hrui.attandance

import com.hr.data.network.api.response.ResponseAttendanceDetails

class AttendanceDetailsAdapter (
                                upComingList: List<ResponseAttendanceDetails.Re>
) :
    RecyclerView.Adapter<AttendanceDetailsAdapter.CategoryHolder>() {

    val upComingList = upComingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.attendance_details_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = upComingList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindUi(position)
    }


    inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUi(position: Int) {
            view.apply {
                upComingList[position].let { _category ->
                    emp_name_attendance.text = _category.empname
                    var inTime: String = ""
                    if (!_category.signin.equals("")) {
                        inTime = _category.signin
                    } else {
                        inTime = "-"
                    }

                    var OutTime: String = ""
                    if (!_category.signout.equals("")) {
                        OutTime = _category.signout
                    } else {
                        OutTime = "-"
                    }
                    if (_category.workhours.equals("")) {
                        emp_working_hours.visibility = View.GONE
                        info_time.visibility = View.GONE
                    } else {
                        emp_working_hours.visibility = View.VISIBLE
                        emp_working_hours.text = "Total Working hours" +":"+" "+_category.workhours
                        info_time.visibility = View.VISIBLE


                    }

                    info_time.setOnClickListener {
                        attendanceEvent.viewWorkTime(_category)

                    }
                    emp_dept_attendance.text = "In: " + " " + inTime + "   " + "Out:" + "" + OutTime

                    Glide.with(this).load(_category.photo).into(profile_image_attendance);


                    if (_category.attendance.equals("P")) {
                        emp_attendance_status.text = _category.attendance
                        attandance_status_lay.setBackgroundColor(
                            ContextCompat.getColor(
                                getContext(),
                                R.color.attance_p
                            )
                        );

                    } else {
                        emp_attendance_status.text = _category.attendance
                        attandance_status_lay.setBackgroundColor(
                            ContextCompat.getColor(
                                getContext(),
                                R.color.attance_a
                            )
                        );

                    }


                }
            }
        }
    }


}