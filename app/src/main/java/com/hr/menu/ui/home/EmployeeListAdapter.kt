package com.hr.menu.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hr.R
import com.hr.data.network.api.response.ResponseFollowUp
import com.hr.utility.EditEmployee
import kotlinx.android.synthetic.main.fllow_item.view.*


class EmployeeListAdapter(val editEmployee: EditEmployee,
    upComingList: List<ResponseFollowUp.Re>
) :
    RecyclerView.Adapter<EmployeeListAdapter.CategoryHolder>() {

    val upComingList = upComingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fllow_item,
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
                    emp_dept.text = _category.department

                    Glide.with(this).load(_category.photo).into(profile_image);

                    emp_id_edit.setOnClickListener {
                        editEmployee.editEmployee(_category)
                    }


                    }
                }
            }
        }


}