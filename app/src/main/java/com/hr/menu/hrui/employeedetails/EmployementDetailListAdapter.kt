package com.hr.menu.hrui.employeedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hr.R
import com.hr.data.network.api.response.ResponseDepartment
import kotlinx.android.synthetic.main.layout_types_item.view.*

import kotlin.to


typealias category = (ResponseDepartment.Re) -> Unit

class EmployementDetailListAdapter(val category: category) :
    RecyclerView.Adapter<EmployementDetailListAdapter.CategoryHolder>() {

    val categoryList = mutableListOf<ResponseDepartment.Re>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_types_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindUi(position)
    }

    fun addCategoryList(_categoryList: List<ResponseDepartment.Re>) {
        categoryList.addAll(_categoryList)
        notifyDataSetChanged()
    }

    inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUi(position: Int) {
            view.apply {
                categoryList[position].let { _category ->
                    emp_type.text = _category.dept_name


                    setOnClickListener {
                        category.invoke(_category) }
                    }
                }
            }
        }


}