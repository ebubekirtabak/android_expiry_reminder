package com.geng.expiry_reminder.activities.NewCategoryActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.adapters.ColorItemAdapter
import com.geng.expiry_reminder.adapters.IconItemAdapter
import com.geng.expiry_reminder.databinding.FragmentNewCategoryBinding

import com.geng.expiry_reminder.models.items.ColorItem
import com.geng.expiry_reminder.models.items.IconItem

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentNewCategoryBinding? = null

    private val binding get() = _binding!!
    private var selectedColor: ColorItem = ColorItem(0, "Red", R.color.red)
    private lateinit var selectedIcon: IconItem
    private lateinit var colorItemAdapter: IconItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorSpinner: Spinner = view.findViewById(R.id.color_spinner)
        val iconSpinner: Spinner = view.findViewById(R.id.icon_spinner)
        initIconSpinner(iconSpinner)
        val colorItemAdapter = ColorItemAdapter(
            context,
            R.layout.color_spinner_item,
            listOf(
                ColorItem(0, "Red", R.color.red),
                ColorItem(0, "Blue", R.color.blue),
                ColorItem(0, "Black", R.color.black),
                ColorItem(0, "Gray", R.color.gray),
                ColorItem(0, "Deep Jungle Green", R.color.deep_jungle_green),
                ColorItem(0, "Purple", R.color.purple_500)
            )
        )
        colorItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                selectedColor = parentView?.getItemAtPosition(position) as ColorItem
                if (iconSpinner !== null) {
                    updateIconSpinnerAdapter()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
        colorSpinner.adapter = colorItemAdapter
    }

    private fun initIconSpinner(iconSpinner: Spinner) {
        iconSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                selectedIcon = parentView?.getItemAtPosition(position) as IconItem
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        colorItemAdapter= IconItemAdapter(
            context,
            R.layout.icon_spinner_item,
            getIconList()
        )
        colorItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorItemAdapter.setNotifyOnChange(true);
        iconSpinner.adapter = colorItemAdapter
    }

    private fun getIconList(): ArrayList<IconItem> {
        val iconList = ArrayList<IconItem>()
        iconList.addAll(
            listOf(
                IconItem(0, "Fridge", selectedColor.color, R.drawable.ic_baseline_kitchen_24),
                IconItem(0, "Dashboard", selectedColor.color, R.drawable.ic_dashboard_black_24dp),
                IconItem(0, "Payments", selectedColor.color, R.drawable.ic_baseline_payments_24),
                IconItem(0, "Shopping Cart", selectedColor.color, R.drawable.ic_baseline_shopping_cart_24),
                IconItem(0, "Time", selectedColor.color, R.drawable.ic_time_black_24dp),
                IconItem(0, "Home", selectedColor.color, R.drawable.ic_home_black_24dp)
            )
        )
        return iconList
    }

    fun updateIconSpinnerAdapter() {
        colorItemAdapter.clear()
        colorItemAdapter.addAll(getIconList())
        colorItemAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.d("Not yet implemented", "onItemSelected")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}