package com.hr.menu.hrui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.bumptech.glide.Glide
import com.hr.MainActivity
import com.hr.R
import com.hr.data.network.api.request.RequestList
import com.hr.data.network.viewmodel.UserProfileViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.attendance_item.view.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(), LifecycleObserver {
    private val userProfileViewModel by viewModel<UserProfileViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu_profile.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }
        logout.setOnClickListener {
            SessionSave.saveSession("user_login_token","",requireContext())

            val moveNext = Intent(requireContext(), MainActivity::class.java)
            startActivity(moveNext)
            requireActivity().finish()
        }
        getUserLoginDetails()

    }

    private fun getUserLoginDetails() {

        userProfileViewModel.reqGetUserDetails(RequestList(
            token = SessionSave.getSession("user_login_token", requireContext())
        ), {

            Glide.with(this).load(it.res.photo).into(profile_image_employee);

            et_empname.setText(it.res.empname)
            et_department.setText(it.res.department)
            et_icno.setText(it.res.icno)
            et_emailid.setText(it.res.emailid)
            et_marital_status.setText(it.res.marital_status)
            et_emergency_name.setText(it.res.emergency_name)
            et_emergency_contactno.setText(it.res.emergency_contactno)
            et_emergency_relationship.setText(it.res.emergency_relationship)



        }, {


            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
        })
    }
}