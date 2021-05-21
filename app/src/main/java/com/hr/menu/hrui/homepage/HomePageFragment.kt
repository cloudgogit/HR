package com.hr.menu.hrui.homepage

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hr.R
import com.hr.data.network.api.request.RequestLeaveApproval
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.api.request.RequestSignin
import com.hr.data.network.viewmodel.DashBoardViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePageFragment : Fragment(), LifecycleObserver {
    private val dashBoardViewModel by viewModel<DashBoardViewModel>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    var lat: Double = 0.0
    var lng: Double = 0.0
    var claim_id: String = ""
    var leave_id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu_home.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }

/*      */
        if (!checkRuntimePermission()) {
            requestRuntimePermission()
        } else {
            moveNextScreen()
        }


        shift_in.setOnClickListener {
            if (txt_sift.text.toString().equals("Signout")) {

                getRequestShift("signoff")

            } else {
                getRequestShift("signin")

            }
        }

        leave_reject.setOnClickListener {


        }
        leave_approve.setOnClickListener {


        }

        claim_reject.setOnClickListener {
            ClaimApproval("rejected")
        }
        claim_approve.setOnClickListener {
            ClaimApproval("approved")
        }
        getDashBoardDetails()
    }

    private fun getDashBoardDetails() {
        dashBoardViewModel.reqGetDashBoardData(RequestList(
            token = SessionSave.getSession("user_login_token", requireContext())
        ), {
            present.setText(it.present.toString())
            late.setText(it.late.toString())
            absent.setText(it.absent.toString())

            if (!it.leave_data.equals("no")) {
                leave_emp_name.setText(it.leave.empname)
                leave_type_dash.setText(it.leave.reason)
                leave_date.setText(it.leave.dates)
                leave_deatils.visibility = View.VISIBLE

            } else {
                leave_deatils.visibility = View.GONE
            }

            if (!it.claim_data.equals("no")) {
                claim_deatils.visibility = View.VISIBLE

                cliam_amount.setText("RM" + " " + it.claim.totalamount)
                claim_emp_name.setText(it.claim.empname)
                claim_reason.setText(it.claim.purpose)
            } else {
                claim_deatils.visibility = View.GONE
            }



            sign_hours.setText(it.workhours.toString())
            no_of_days.setText(it.start_time.toString())

            txt_sift.setText(it.sign)


        }, {

        })
    }


    private fun checkRuntimePermission(): Boolean {

        val writeablePermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
        if (writeablePermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }


    private fun requestRuntimePermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
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

    @SuppressLint("MissingPermission")
    private fun moveNextScreen() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient!!.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    lat = location!!.latitude
                    lng = location!!.longitude


                }


            }
    }

    private fun getRequestShift(s: String) {
        //   requireActivity().progressBarShow(requireContext())

        dashBoardViewModel.updateWorkstatus(
            RequestSignin(
                token = SessionSave.getSession("user_login_token", requireContext()),
                type = s,
                longitude = lng.toString(),
                latitude = lat.toString()

            ), {

                getDashBoardDetails()

              //  sign_hours.setText(it.workhours.toString() + " " + "Hrs")
              //  no_of_days.setText(it.start_time.toString())
                //requireActivity().progressBarDismiss(requireContext())
                //Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
            }, {
                //  requireActivity().progressBarDismiss(requireContext())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
    }


    private fun ClaimApproval(type: String) {
        dashBoardViewModel.updateClaimApproval(
            RequestLeaveApproval(
                token = SessionSave.getSession("user_login_token", requireContext()),
                id = claim_id,
                status = type,
                rejected_reason = type
            ), {
                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                getDashBoardDetails()

            }, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

            })
    }
}