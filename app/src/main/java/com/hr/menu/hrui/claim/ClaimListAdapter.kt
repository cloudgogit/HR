package com.hr.menu.hrui.claim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hr.R
import com.hr.data.network.api.response.ClaimsResponse
import kotlinx.android.synthetic.main.claim_item.view.*
import kotlinx.android.synthetic.main.claim_item.view.emp_name
import kotlinx.android.synthetic.main.claim_item.view.profile_image
import kotlinx.android.synthetic.main.fllow_item.view.*
import kotlinx.android.synthetic.main.levae_list_item.view.approve
import kotlinx.android.synthetic.main.levae_list_item.view.reject


class ClaimListAdapter(
    val claimDetails: ClaimDetails,
    upComingList: List<ClaimsResponse.Re>
) :
    RecyclerView.Adapter<ClaimListAdapter.CategoryHolder>() {

    val upComingList = upComingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.claim_item,
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
                    emp_name.text = _category.empname
                    claim_date.text = _category.date
                    claim_reason.text = _category.purpose
                    claim_amount.text = "$" + _category.totalamount.toString()
                    Glide.with(this).load(_category.photo).into(profile_image);

                    claim_main_lay.setOnClickListener {
                        claimDetails.claimDetails( _category)

                    }
                    claim_sub_lay.setOnClickListener {
                        claimDetails.claimDetails( _category)
                    }

                    approve.setOnClickListener {
                        claimDetails.claimApprovel("approved", _category)
                    }
                    reject.setOnClickListener {
                        claimDetails.claimApprovel("rejected", _category)
                    }

                }
            }
        }
    }


}