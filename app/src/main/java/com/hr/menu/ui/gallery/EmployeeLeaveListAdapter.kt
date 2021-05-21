package com.hr.menu.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hr.R
import com.hr.data.network.api.response.ResponseLeaveList
import com.hr.utility.LeaveApproval
import kotlinx.android.synthetic.main.levae_list_item.view.*


class EmployeeLeaveListAdapter(val leaveApproval: LeaveApproval,
    upComingList: List<ResponseLeaveList.Re>
) :
    RecyclerView.Adapter<EmployeeLeaveListAdapter.CategoryHolder>() {

    val upComingList = upComingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.levae_list_item,
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
                    emp_el_name.text = _category.employee_name
                    leave_type.text = _category.reason
                    no_of_days.text = _category.date


                    approve.setOnClickListener {
                        leaveApproval.leaveRequest("approved",_category)
                    }
reject.setOnClickListener {
    leaveApproval.leaveRequest("rejected",_category)
}

                    }
                }
            }
        }


}