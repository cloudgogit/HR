package com.hr.menu.hrui.employeedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hr.R
import com.hr.data.network.api.response.ResponseBranch
import kotlinx.android.synthetic.main.layout_types_item.view.*

import kotlin.to


typealias _category = (ResponseBranch.Re) -> Unit

class EmployementBranchListAdapter(val category: _category) :
    RecyclerView.Adapter<EmployementBranchListAdapter.CategoryHolder>() {

    val categoryList = mutableListOf<ResponseBranch.Re>()

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

    fun addCategoryList(_categoryList: List<ResponseBranch.Re>) {
        categoryList.addAll(_categoryList)
        notifyDataSetChanged()
    }

    inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUi(position: Int) {
            view.apply {
                categoryList[position].let { _category ->
                    emp_type.text = _category.branch_name


                    setOnClickListener {
                        category.invoke(_category) }
                    }
                }
            }
        }


}