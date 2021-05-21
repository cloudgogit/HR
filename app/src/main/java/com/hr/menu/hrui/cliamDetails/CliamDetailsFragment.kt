package com.hr.menu.hrui.cliamDetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.hr.R
import com.hr.data.network.api.request.RequestEmployeeEditPersonalInfo
import com.hr.data.network.di.loadImage
import com.hr.data.network.viewmodel.ClaimDetailsViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_cliam_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CliamDetailsFragment : Fragment() {

    private val claimDetailsViewModel by viewModel<ClaimDetailsViewModel>()
    var image_show: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cliam_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCliamDetails()
        menu_claim_details.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()

        }
        et_attachement.setOnClickListener {
            showImage()
        }
    }

    private fun showImage() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout: View = inflater.inflate(R.layout.alert_dialog_with_imageview, null)
        builder.setPositiveButton("OK", null)
        builder.setView(dialogLayout)
        builder.show()
        val image = dialogLayout.findViewById<ImageView>(R.id.imageView_dialalog)
        image.loadImage(image_show)

    }

    private fun getCliamDetails() {
        claimDetailsViewModel.reqClaimDetails(
            RequestEmployeeEditPersonalInfo(
                token =  SessionSave.getSession("user_login_token",requireContext()),
                id = SessionSave.getSession("claim_id", requireContext())
            ), {
                et_emp_name.setText(it.res[0].empname)
                et_typeofleave_claim.setText(it.res[0].purpose)
                et_from_date_claim.setText(it.res[0].date_from)
                et_todate_claim.setText(it.res[0].date_to)
                et_reason_claim.setText(it.res[0].description)
                et_purpose_1.setText(it.res[0].purpose_1)
                et_receipt_1.setText(it.res[0].receiptno_1)
                bill_date.setText(it.res[0].bill_date_1)
                et_amount_claim.setText(it.res[0].amount_1)

                et_attachement.setText("Show attachement")
                et_remarks.setText(it.res[0].remarks_1)

                image_show = it.res[0].receipt_1
            }, {

            }

        )
    }


}