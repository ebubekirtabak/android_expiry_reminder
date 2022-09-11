package com.geng.expiry_reminder.adapters

import android.R.attr.button
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.models.category.Category


class CategoryViewAdapter(private val categoryList: ArrayList<Category>):RecyclerView.Adapter<CategoryViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTextView.text = categoryList[position].name
        holder.categoryImageView.setBackgroundResource(categoryList[position].icon)
        setIconColor(holder.categoryImageView, categoryList[position].color)
    }

    private fun setIconColor(iconView: ImageView, color: Int) {
        var buttonDrawable: Drawable? = iconView.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        DrawableCompat.setTint(
            buttonDrawable,
            ContextCompat.getColor(iconView.context, color)
        )
        iconView.setImageDrawable(buttonDrawable)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.category_name_text)
        val categoryImageView: ImageView = itemView.findViewById(R.id.category_icon)
    }
}