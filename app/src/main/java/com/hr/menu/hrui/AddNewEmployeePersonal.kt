package com.hr.menu.hrui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.hr.R
import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.api.request.RequestEmployeePersonalInfo
import com.hr.data.network.api.request.RequestUpdateEmployeePersonalInfo
import com.hr.data.network.viewmodel.EmployeeAddPersonalViewViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_add_new_employee_personal.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AddNewEmployeePersonal : Fragment(), DatePickerDialog.OnDateSetListener, LifecycleObserver {
    private val employeeAddPersonalViewViewModel by viewModel<EmployeeAddPersonalViewViewModel>()

    val gender = listOf("Male ", "Female ", "Others")
    val race = listOf("Malay  ", "Chinese  ", "Indians")
    val physical_challenge = listOf("Yes", "No ")
    val martial_status = listOf(" Married", "Single ", "Divorced", "Widowed ")
    val children = listOf(" One", "Two ", "Three", "Four ")
    var date_type: String = ""
    var hr_type : String = "no"
    var hr_send_type : String = "no"


    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_employee_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gender_adapter = ArrayAdapter(requireContext(), R.layout.list_item, gender)
        et_gender.setAdapter(gender_adapter)

        val race_adapter = ArrayAdapter(requireContext(), R.layout.list_item, race)
        et_race.setAdapter(race_adapter)

        val physical_challenge_adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            physical_challenge
        )
        et_physical_challenge.setAdapter(physical_challenge_adapter)


        val martial_status_adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            martial_status
        )
        et_marital_status.setAdapter(martial_status_adapter)

        val children_adapter = ArrayAdapter(requireContext(), R.layout.list_item, children)
        et_children.setAdapter(children_adapter)
        menu_click.setOnClickListener {


            (activity as SlideMenu?)!!.openCloseNavigationDrawer()

        }

        ed_dob.setOnClickListener {
            date_type = "dob"
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }
        ed_passport_issuedate.setOnClickListener {
            date_type = "passport_issuedate"
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }
        ed_passport_expiredate.setOnClickListener {
            date_type = "passport_expiredate"
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.getDatePicker().setMaxDate(Date().time)

            datePickerDialog.show()
        }

        emp_next.setOnClickListener {

            //
            if (SessionSave.getSession("emp_edit", requireContext()).equals("yes")) {
                editEmployeeInfo()
            } else {
                if (et_emp_name.text.toString().equals("")) {
                    Toast.makeText(requireContext(),"Kindly enter the employee name",Toast.LENGTH_LONG).show()
                }

              else  if (et_ico.text.toString().equals("")) {
                    Toast.makeText(requireContext(),"Kindly enter the ICNO",Toast.LENGTH_LONG).show()
                }
                else  if (et_emergency_name.text.toString().equals("")) {
                    Toast.makeText(requireContext(),"Kindly enter the emergency name",Toast.LENGTH_LONG).show()
                }
                else  if (et_emergency_contactno.text.toString().equals("")) {
                    Toast.makeText(requireContext(),"Kindly enter the emergency contactno",Toast.LENGTH_LONG).show()
                }

                else{
                    addEmployeePersonlInfo()
                }


            }
        }

        lay_heading.setOnClickListener {

        }
        if (SessionSave.getSession("emp_edit", requireContext()).equals("yes")) {
        //    setEmployeeinfo()
        }

        radio_group.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<View>(checkedId) as RadioButton
            if (null != rb && checkedId > -1) {
                hr_type = rb.text.toString()
                println("land_type"+""+hr_type)
            }
        })


    }

    private fun editEmployeeInfo() {
        if(hr_type.equals("Need HR Access"))
        {
            hr_send_type = "yes"
        }
        else{
            hr_send_type = "no"
        }
        employeeAddPersonalViewViewModel.reqUpdateEmployeePersonalInfo(
            RequestUpdateEmployeePersonalInfo(

                token = SessionSave.getSession("user_login_token", requireContext()),
                id = SessionSave.getSession("emp_id", requireContext()),
                empname = et_emp_name.text.toString(),
                dob = ed_dob.text.toString(),
                icno = et_ico.text.toString(),
                gender = et_gender.text.toString(),
                emailid = et_email_id.text.toString(),
                race = et_race.text.toString(),
                religion = et_religion.text.toString(),
                current_address = et_current_address.text.toString(),
                ic_address = et_ic_address.text.toString(),
                physical_challenge = et_physical_challenge.text.toString(),
                passportno = et_passportno.text.toString(),
                passport_issuedate = ed_passport_issuedate.text.toString(),
                passport_expiredate = ed_passport_expiredate.text.toString(),
                marital_status = et_marital_status.text.toString(),
                spouse_name = et_spouse_name.text.toString(),
                spouse_icno = et_spouse_icno.text.toString(),
                spouse_employement = et_spouse_employement.text.toString(),
                children = et_children.text.toString(),
                emergency_name = et_emergency_name.text.toString(),
                emergency_contactno = et_emergency_contactno.text.toString(),
                hraccess = hr_send_type


            ), {
                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                SessionSave.saveSession("emp_edit", "NO", requireContext())

                // SessionSave.saveSession("emp_edit_personal","yes",requireContext())

                findNavController().navigate(R.id.addEmployeeDetailsFragment2)
            }, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

            }
        )
    }

    private fun setEmployeeinfo() {
        employeeAddPersonalViewViewModel.reqEditEmployeePersonalInfo(RequestEmployeeEditPersonalInfo(
            token = SessionSave.getSession("user_login_token", requireContext()),
            id = SessionSave.getSession("emp_id", requireContext())
        ), {
            et_emp_name.setText(it.res[0].empname)
            ed_dob.setText(it.res[0].dob)
            et_ico.setText(it.res[0].icno)
            et_gender.setText(it.res[0].gender)
            et_email_id.setText(it.res[0].emailid)
            et_race.setText(it.res[0].race)
            et_religion.setText(it.res[0].religion)
            et_ic_address.setText(it.res[0].ic_address)
            et_current_address.setText(it.res[0].current_address)
            et_physical_challenge.setText(it.res[0].physical_challenge)
            et_passportno.setText(it.res[0].passportno)
            ed_passport_issuedate.setText(it.res[0].passport_issuedate)
            ed_passport_expiredate.setText(it.res[0].passport_expiredate)
            et_marital_status.setText(it.res[0].marital_status)
            et_spouse_icno.setText(it.res[0].spouse_icno)
            et_spouse_employement.setText(it.res[0].spouse_employement)
            et_emergency_name.setText(it.res[0].emergency_name)
            et_emergency_contactno.setText(it.res[0].emergency_contactno)
            et_emergency_relationship.setText(it.res[0].emergency_relationship)

            et_spouse_name.setText(it.res[0].spouse_name)
            et_children.setText(it.res[0].children)


        }, {

        })
    }

    private fun addEmployeePersonlInfo() {
        if(hr_type.equals("Need HR Access"))
        {
            hr_send_type = "yes"
        }
        else{
            hr_send_type = "no"
        }
        employeeAddPersonalViewViewModel.reqAddEmployeePersonalInfo(RequestEmployeePersonalInfo(
            token = SessionSave.getSession("user_login_token", requireContext()),
            empname = et_emp_name.text.toString(),
            dob = ed_dob.text.toString(),
            icno = et_ico.text.toString(),
            gender = et_gender.text.toString(),
            emailid = et_email_id.text.toString(),
            race = et_race.text.toString(),
            religion = et_religion.text.toString(),
            current_address = et_current_address.text.toString(),
            ic_address = et_ic_address.text.toString(),
            physical_challenge = et_physical_challenge.text.toString(),
            passportno = et_passportno.text.toString(),
            passport_issuedate = ed_passport_issuedate.text.toString(),
            passport_expiredate = ed_passport_expiredate.text.toString(),
            marital_status = et_marital_status.text.toString(),
            spouse_name = et_spouse_name.text.toString(),
            spouse_icno = et_spouse_icno.text.toString(),
            spouse_employement = et_spouse_employement.text.toString(),
            children = et_children.text.toString(),
            emergency_name = et_emergency_name.text.toString(),
            emergency_contactno = et_emergency_contactno.text.toString(),
            emergency_relationship = et_emergency_relationship.text.toString(),
                    hraccess = hr_send_type
        ), {
            Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
            SessionSave.saveSession("emp_id", it.empid, requireContext())
            findNavController().navigate(R.id.addEmployeeDetailsFragment2)
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR, 1)
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        if (date_type.equals("dob")) {
            ed_dob.setText(myYear.toString() + "/" + myMonth + "/" + myDay)
        } else if (date_type.equals("passport_issuedate")) {
            ed_passport_issuedate.setText(myYear.toString() + "/" + myMonth + "/" + myDay)
        } else if (date_type.equals("passport_expiredate")) {

            ed_passport_expiredate.setText(myYear.toString() + "/" + myMonth + "/" + myDay)
        }


    }




}