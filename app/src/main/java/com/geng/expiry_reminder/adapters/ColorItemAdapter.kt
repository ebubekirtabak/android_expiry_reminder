package com.geng.expiry_reminder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.geng.expiry_reminder.R

import com.geng.expiry_reminder.models.items.ColorItem


class ColorItemAdapter(
    context: Context?,
    private val layoutResourceId: Int,
    private val colorItemList: List<ColorItem>
) :
    ArrayAdapter<ColorItem>(context!!, layoutResourceId, colorItemList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getCount(): Int {
        return colorItemList.size
    }

    override fun getItem(position: Int): ColorItem {
        return colorItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return colorItemList[position].uid.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        val textViewName: TextView = view!!.findViewById(R.id.color_item_text)
        val currentItem: ColorItem? = getItem(position)

        if (currentItem != null) {
            textViewName.text = currentItem.name
        }

        return view
    }

    private fun initView(
        position: Int, convertView: View?, parent: ViewGroup
    ): View {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        }

        val textViewName: TextView = convertView!!.findViewById(R.id.color_item_text)
        val currentItem: ColorItem? = getItem(position)

        if (currentItem != null) {
            textViewName.text = currentItem.name
        }

        return convertView
    }
}