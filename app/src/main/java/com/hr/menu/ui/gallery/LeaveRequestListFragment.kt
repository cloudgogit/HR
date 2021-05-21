package com.hr.menu.ui.gallery

import PreferenceManager
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.hr.R
import com.hr.data.network.api.request.RequestLeaveApproval
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.response.ResponseLeaveList
import com.hr.data.network.viewmodel.EmployeeLeaveListViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.LeaveApproval
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.reason_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeaveRequestListFragment : Fragment(), LifecycleObserver, LeaveApproval {

    var userid: String = ""
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001

    private val employeeLeaveListViewModel by viewModel<EmployeeLeaveListViewModel>()
    private lateinit var employeeLeaveListAdapter: EmployeeLeaveListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PreferenceManager(requireContext()).apply {

            userid = getUserId()
        }
        SessionSave.saveSession("emp_edit", "No", requireContext())


        menu_leave.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }

        getLeaveLisrist()


    }

    private fun getLeaveLisrist() {
        employeeLeaveListViewModel.reqLeaveListUP(
            RequestList(
                token =  SessionSave.getSession("user_login_token",requireContext())
            ), {
                loading_my_call.visibility = View.GONE
                if (it.res.size != 0) {
                    employeeLeaveListAdapter = EmployeeLeaveListAdapter(this, it.res)
                    my_follow_up_list.adapter = employeeLeaveListAdapter
                    employeeLeaveListAdapter.notifyDataSetChanged()

                    //mynocalls.visibility = View.GONE
                    my_follow_up_list.visibility = View.VISIBLE
                } else {
                    my_follow_up_list.visibility = View.GONE
                    // mynocalls.visibility = View.VISIBLE
                }


            }, {
                loading_my_call.visibility = View.GONE

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }


    override fun leaveRequest(type: String, _category1: ResponseLeaveList.Re) {
        //
        if (type.equals("approved")) {
            updateleaveApproval(type, _category1, "")
        } else {
            val mDialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.reason_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.submit_reson.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val reson = mDialogView.et_reson.text.toString()

                updateleaveApproval(type, _category1, reson)
            }
            //cancel button click of custom layout
            mDialogView.cancel.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }


    }

    private fun updateleaveApproval(type: String, _category1: ResponseLeaveList.Re, reson: String) {
        employeeLeaveListViewModel.updateLeaveApproval(RequestLeaveApproval(
            token =  SessionSave.getSession("user_login_token",requireContext()),
            id = _category1.id.toString(),
            status = type,
            rejected_reason = reson
        ), {
            Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()

            getLeaveLisrist()
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

        })
    }

}