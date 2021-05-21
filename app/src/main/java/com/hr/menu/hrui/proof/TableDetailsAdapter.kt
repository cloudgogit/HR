package com.hr.menu.hrui.proof

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hr.R
import com.hr.data.network.api.response.ResponseDepartment
import com.hr.data.network.api.response.ResponseTable
import kotlinx.android.synthetic.main.layout_types_item.view.*

import kotlin.to


typealias category = (ResponseTable.Re) -> Unit

class TableDetailsAdapter(val category: category) :
    RecyclerView.Adapter<TableDetailsAdapter.CategoryHolder>() {

    val categoryList = mutableListOf<ResponseTable.Re>()

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

    fun addCategoryList(_categoryList: List<ResponseTable.Re>) {
        categoryList.addAll(_categoryList)
        notifyDataSetChanged()
    }

    inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUi(position: Int) {
            view.apply {
                categoryList[position].let { _category ->
                    emp_type.text = _category.type_value


                    setOnClickListener {
                        category.invoke(_category) }
                    }
                }
            }
        }


}