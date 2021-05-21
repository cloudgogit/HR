package com.hr.menu.hrui.attandance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.hr.R
import com.hr.data.network.api.request.RequestAttendance
import com.hr.data.network.api.request.RequestAttendanceDetails
import com.hr.data.network.api.response.ResponseAttendance
import com.hr.data.network.viewmodel.AttendanceViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_add_new_employee_personal.*
import kotlinx.android.synthetic.main.fragment_attandance.*
import kotlinx.android.synthetic.main.fragment_claim.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class AttandanceFragment : Fragment(), LifecycleObserver, DatePickerDialog.OnDateSetListener,AttendanceEvent {
    var formattedDate: String = ""


    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    private val attendanceViewModel by viewModel<AttendanceViewModel>()
    private lateinit var attendanceListAdapter: AttendanceListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attandance, container, false)

    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_attandance.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }

        val c: Calendar = Calendar.getInstance()

        System.out.println("Current time => " + c.getTime())

        val df = SimpleDateFormat("yyyy-MM-dd")
        formattedDate = df.format(c.getTime())
        date_of_attendance.setText(formattedDate)
        leftdate.setOnClickListener {
            c.add(Calendar.DATE, -1);
            formattedDate = df.format(c.getTime());


            date_of_attendance.setText(formattedDate);
            getDailyAttendance()
        }

        rightdate.setOnClickListener {

            c.add(Calendar.DATE, 1);
            formattedDate = df.format(c.getTime());
            date_of_attendance.setText(formattedDate);
            getDailyAttendance()
        }
        date_of_attendance.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }

        getDailyAttendance()
    }

    private fun getDailyAttendance() {

        attendanceViewModel.reqAttendance(RequestAttendance(
            token = SessionSave.getSession("user_login_token", requireContext()),
            page = "1",
            date = formattedDate
        ), {
            attendanceListAdapter = AttendanceListAdapter(this,it.res)
            attendance_list.adapter = attendanceListAdapter
            attendanceListAdapter.notifyDataSetChanged()
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month+1
        formattedDate = myYear.toString() + "-" + myMonth + "-" + myDay;
        date_of_attendance.setText(formattedDate)
        getDailyAttendance()

    }

    override fun viewWorkTime(_category: ResponseAttendance.Re) {

        attendanceViewModel.reqAttendanceDetails(
            RequestAttendanceDetails(
            token = SessionSave.getSession("user_login_token", requireContext()),
            id = _category.id.toString(),
            date = formattedDate
        ), {

        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun manualAttendance(_category: ResponseAttendance.Re) {

        println("manual_checking = manual_checking")

    }


}