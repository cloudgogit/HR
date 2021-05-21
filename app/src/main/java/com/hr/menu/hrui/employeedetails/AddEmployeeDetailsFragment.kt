package com.hr.menu.hrui.employeedetails

import PreferenceManager
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hr.R
import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.request.RequestEmployeeEmployeement
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.ResponseBranch
import com.hr.data.network.api.response.ResponseDepartment
import com.hr.data.network.viewmodel.EmployeeDepoartmentViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.bottom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_add_employee_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddEmployeeDetailsFragment : Fragment(),LifecycleObserver, DatePickerDialog.OnDateSetListener {
    private val employeeDepoartmentViewModel by viewModel<EmployeeDepoartmentViewModel>()

    val Paybasic = listOf("Weekly ", "Daily ", "Monthly")
    val payment = listOf("Online  ", "Cash ")
    val employement_type = listOf("Permanent", "Contract ", "Part Time")
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    var userid = String()
    var deptid = String()
    var branchid = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_employee_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paybasis_adapter = ArrayAdapter(requireContext(), R.layout.list_item, Paybasic)
        et_paybasis.setAdapter(paybasis_adapter)

        val payment_adapter = ArrayAdapter(requireContext(), R.layout.list_item, payment)
        et_payment_mode.setAdapter(payment_adapter)

        val employement_type_adapter =
            ArrayAdapter(requireContext(), R.layout.list_item, employement_type)
        ed_employement_type.setAdapter(employement_type_adapter)

        menu_back.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }
        PreferenceManager(requireContext()).apply {

            userid = getUserId()

        }
//            SessionSave.saveSession("emp_id",it.empid,requireContext())
        et_dept.setOnClickListener {

            getemployeeList()
        }
        et_branch.setOnClickListener {
            getBranchList()
        }
        if (SessionSave.getSession("emp_edit_personal", requireContext()).equals("yes")) {
            SessionSave.saveSession("emp_edit", "yes", requireContext())
            showEmpDetails()
        }
        ed_doj.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this, year, month, day)

            datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }
        emp_related.setOnClickListener {
            if (et_nodesignation.text.toString().trim().equals("")) {
                Toast.makeText(requireContext(), "Kindly enter the Designation", Toast.LENGTH_LONG)
                    .show()
            } else if (et_dept.text.toString().trim().equals("")) {
                Toast.makeText(requireContext(), "Kindly enter the Department", Toast.LENGTH_LONG)
                    .show()
            } else if (et_branch.text.toString().trim().equals("")) {
                Toast.makeText(requireContext(), "Kindly enter the Branch", Toast.LENGTH_LONG)
                    .show()
            } else {
                addEmployeeDetails()
            }


        }
        personal_details.setOnClickListener {
            if (SessionSave.getSession("emp_edit_personal", requireContext()).equals("yes")) {
                findNavController().navigate(R.id.editEmployeePersonalFragment)
            } else {
                findNavController().navigate(R.id.addNewEmployeePersonal2)
            }

        }

    }

    private fun showEmpDetails() {
        employeeDepoartmentViewModel.reqEditEmployeeEmployeement(RequestEmployeeEditPersonalInfo(
            token = SessionSave.getSession("user_login_token", requireContext()),
            id = SessionSave.getSession("emp_id", requireContext())
        ), {
            et_nodesignation.setText(it.res[0].designation)
            et_dept.setText(it.res[0].department)
            et_branch.setText(it.res[0].branch)
            et_paybasis.setText(it.res[0].paybasis)
            et_payment_mode.setText(it.res[0].payment_mode)
            ed_employement_type.setText(it.res[0].employement_type)
            ed_work_permit.setText(it.res[0].work_permit)
            ed_employment_pass.setText(it.res[0].employment_pass)
            ed_immegrationno.setText(it.res[0].immegrationno)
            ed_doj.setText(it.res[0].doj)
        }, {

        })
    }

    private fun addEmployeeDetails() {
        employeeDepoartmentViewModel.reqAddEmployeeEmployeement(
            RequestEmployeeEmployeement(
                token = SessionSave.getSession("user_login_token", requireContext()),
                empid = SessionSave.getSession("emp_id", requireContext()),
                designation = et_nodesignation.text.toString(),
                department = deptid,
                branch = branchid,
                paybasis = et_paybasis.text.toString(),
                payment_mode = et_payment_mode.text.toString(),
                employement_type = ed_employement_type.text.toString(),
                work_permit = ed_work_permit.text.toString(),
                employment_pass = ed_employment_pass.text.toString(),
                immegrationno = ed_immegrationno.text.toString(),
                doj = ed_doj.text.toString()

            ), {

                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()

                if (SessionSave.getSession("emp_edit", requireContext()).equals("yes")) {

                    findNavController().navigate(R.id.editEmployeeProffFragment)

                } else {
                    findNavController().navigate(R.id.employeeProfFragment2)

                }
            }, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun getBranchList() {
        employeeDepoartmentViewModel.reqBranchList(
            RequestList(
                token = SessionSave.getSession(
                    "user_login_token",
                    requireContext()
                )
            ), {
                showBranchList(requireContext(), it)
            }, {

            })
    }

    private fun getemployeeList() {
        employeeDepoartmentViewModel.reqAllFollowUP(
            RequestList(token = SessionSave.getSession("user_login_token", requireContext())), {
                println("Get_employee_result" + "" + it)
                showTaxiList(requireContext(), it)
            }, {
                println("Get_employee_result" + "" + it)

            })
    }


    private fun showTaxiList(
        context: Context,
        category: ResponseDepartment

    ) {
        val view = layoutInflater.inflate(R.layout.bottom_dialog, null)
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(view)
        view.emp_type_list.adapter = EmployementDetailListAdapter { _category ->
            et_dept.setText(_category.dept_name)
            deptid = _category.id.toString()
            dialog.dismiss()
        }
        (view.emp_type_list.adapter as EmployementDetailListAdapter).addCategoryList(category.res)
        dialog.show()
    }


    private fun showBranchList(
        context: Context,
        category: ResponseBranch

    ) {
        val view = layoutInflater.inflate(R.layout.bottom_dialog, null)
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(view)
        view.emp_type_list.adapter = EmployementBranchListAdapter { _category ->
            et_branch.setText(_category.branch_name)
            branchid = _category.id.toString()
            dialog.dismiss()
        }
        (view.emp_type_list.adapter as EmployementBranchListAdapter).addCategoryList(category.res)
        dialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)  {
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        val month = myMonth+1
        ed_doj.setText(myYear.toString()+"/"+month.toString()+"/"+myDay.toString())
    }


}