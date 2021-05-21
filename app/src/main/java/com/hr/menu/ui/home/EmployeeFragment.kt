package com.hr.menu.ui.home

import PreferenceManager
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.hr.R
import com.hr.data.network.api.request.RequestFollowUp
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.data.network.viewmodel.EmployeeListViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.EditEmployee
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeeFragment : Fragment(), LifecycleObserver,EditEmployee {

    var userid: String = ""
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001

    private val allFollowUpViewModel by viewModel<EmployeeListViewModel>()
    private lateinit var employeeListAdapter: EmployeeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PreferenceManager(requireContext()).apply {

            userid = getUserId()
        }

        SessionSave.saveSession("emp_edit","No",requireContext())



            getFllowUpList()




        menu_click.setOnClickListener {

            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }
        /*   man_lay.setOnClickListener {

               findNavController().navigate(R.id.odderDetailsFragment)
           }*/
    }

    private fun getFllowUpList() {
        allFollowUpViewModel.reqAllFollowUP(RequestFollowUp(
            token  = userid,
            page = "1"
        ), {
            if (it.res.size != 0) {
                employeeListAdapter = EmployeeListAdapter( this,it.res)
                follow_up_list.adapter = employeeListAdapter
                employeeListAdapter.notifyDataSetChanged()
                follow_up_list.visibility = View.VISIBLE
            } else {
                follow_up_list.visibility = View.GONE
            }

        }, {


        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }





    private fun checkRuntimePermission(): Boolean {

        val writeablePermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.SEND_SMS

            )
        if (writeablePermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }


    private fun requestRuntimePermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS
            ), READ_EXTERNAL_STORAGE_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                val granted = grantResults.isNotEmpty()
                        && permissions.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && !ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    permissions[0]
                )
                when (granted) {
                    true -> moveNextScreen()  //  openImagePicker()
                    else -> Toast.makeText(activity, "Check Permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun moveNextScreen() {

        getFllowUpList()
    }

    override fun editEmployee(_category: ResponseFollowUp.Re) {
        SessionSave.saveSession("emp_edit","yes",requireContext())
        SessionSave.saveSession("emp_id",_category.id,requireContext())

        findNavController().navigate(R.id.editEmployeePersonalFragment)
    }
}

