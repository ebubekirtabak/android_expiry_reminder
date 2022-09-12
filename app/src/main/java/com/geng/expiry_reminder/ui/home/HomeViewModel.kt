package com.geng.expiry_reminder.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.adapters.CategoryViewAdapter
import com.geng.expiry_reminder.models.category.Category

class HomeViewModel : ViewModel() {
    private val _categoryViewAdapter = MutableLiveData<CategoryViewAdapter>().apply {
        val categoryList = arrayListOf<Category>(
            Category(0,"Fridge", R.drawable.ic_baseline_food_bank_24, 1, R.color.purple_500),
            Category(0,"Freezer", R.drawable.ic_baseline_kitchen_24, 2, R.color.blue),
            Category(0,"Pantry", R.drawable.ic_cupboard_black_24, 3, R.color.gray),
            Category(0,"Documents", R.drawable.ic_baseline_insert_drive_file_24, 4, R.color.deep_jungle_green),
            Category(0,"Payments", R.drawable.ic_baseline_shopping_cart_24, 5, R.color.purple_500) ,
            Category(0,"Medicine", R.drawable.ic_baseline_medical_services_24, 6, R.color.red)
        )
        value = CategoryViewAdapter(categoryList)
    }
    val categoryViewAdapter: MutableLiveData<CategoryViewAdapter> = _categoryViewAdapter
}