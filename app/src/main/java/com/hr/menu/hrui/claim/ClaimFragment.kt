package com.hr.menu.hrui.claim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.hr.R
import com.hr.data.network.api.request.ClaimRequest
import com.hr.data.network.api.request.RequestLeaveApproval
import com.hr.data.network.api.response.ClaimsResponse
import com.hr.data.network.api.response.ResponseLeaveList
import com.hr.data.network.viewmodel.ClaimViewModel
import com.hr.data.network.viewmodel.EmployeeListViewModel
import com.hr.menu.SlideMenu
import com.hr.menu.ui.home.EmployeeListAdapter
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_claim.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.follow_up_list
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClaimFragment : Fragment(),LifecycleObserver,ClaimDetails {

    private val claimViewModel by viewModel<ClaimViewModel>()
    private lateinit var claimListAdapter: ClaimListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_claim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_claim.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }
        claimViewModel.reqGetClaimList(ClaimRequest(
            token =  SessionSave.getSession("user_login_token",requireContext()),
            page = "1"

        ),{
            if (it.res.size==0) {
                no_claim.visibility = View.VISIBLE
                claim_list.visibility= View.GONE
            }
            else{
                no_claim.visibility = View.GONE
                claim_list.visibility= View.VISIBLE
                claimListAdapter = ClaimListAdapter( this,it.res)
                claim_list.adapter = claimListAdapter
                claimListAdapter.notifyDataSetChanged()
            }

        },{

        })
    }

    override fun claimDetails(_category: ClaimsResponse.Re) {

        SessionSave.saveSession("claim_id",_category.id.toString(),requireContext())
        findNavController().navigate(R.id.cliamDetailsFragment)
    }

    override fun claimApprovel(type: String, _category: ClaimsResponse.Re) {
        ClaimApproval(type, _category, type)
    }



    private fun ClaimApproval(type: String, _category1: ClaimsResponse.Re, reson: String) {
        claimViewModel.updateClaimApproval(
            RequestLeaveApproval(
            token =  SessionSave.getSession("user_login_token",requireContext()),
            id = _category1.id.toString(),
            status = type,
            rejected_reason = reson
        ), {
            Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()

                claimViewModel.reqGetClaimList(ClaimRequest(
                    token =  SessionSave.getSession("user_login_token",requireContext()),
                    page = "1"

                ),{
                    claimListAdapter = ClaimListAdapter( this,it.res)
                    claim_list.adapter = claimListAdapter
                    claimListAdapter.notifyDataSetChanged()
                },{

                })
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

        })
    }
}