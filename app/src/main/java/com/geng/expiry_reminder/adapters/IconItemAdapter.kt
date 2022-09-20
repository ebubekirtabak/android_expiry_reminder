package com.geng.expiry_reminder.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.models.items.IconItem

class IconItemAdapter (
    context: Context?,
    private val layoutResourceId: Int,
    private val iconItemList: ArrayList<IconItem>
) :
    ArrayAdapter<IconItem>(context!!, layoutResourceId, iconItemList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getCount(): Int {
        return iconItemList.size
    }

    override fun getItem(position: Int): IconItem {
        return iconItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return iconItemList[position].uid.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        val textViewName: TextView = view!!.findViewById(R.id.color_item_text)
        val currentItem: IconItem? = getItem(position)
        val iconView: ImageView = view!!.findViewById(R.id.icon_view)

        if (currentItem != null) {
            iconView.setBackgroundResource(currentItem.icon)
            setIconColor(iconView, currentItem.color)
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
        val currentItem: IconItem? = getItem(position)
        val iconView: ImageView = convertView!!.findViewById(R.id.icon_view)

        if (currentItem != null) {
            iconView.setBackgroundResource(currentItem.icon)
            setIconColor(iconView, currentItem.color)
            textViewName.text = currentItem.name
        }

        return convertView
    }

    private fun setIconColor(iconView: ImageView, color: Int) {
        var buttonDrawable: Drawable? = iconView.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        DrawableCompat.setTint(
            buttonDrawable,
            ContextCompat.getColor(iconView.context, color)
        )
        iconView.setBackgroundDrawable(buttonDrawable)
    }
}