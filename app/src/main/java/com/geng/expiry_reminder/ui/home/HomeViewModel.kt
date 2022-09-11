package com.geng.expiry_reminder.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.adapters.CategoryViewAdapter
import com.geng.expiry_reminder.models.category.Category

class HomeViewModel : ViewModel() {
    private val _categoryViewAdapter = MutableLiveData<CategoryViewAdapter>().apply {
        val categoryList = arrayListOf<Category>(
            Category("Fridge", R.drawable.ic_baseline_food_bank_24, 1, R.color.purple_500),
            Category("Freezer", R.drawable.ic_baseline_kitchen_24, 2, R.color.blue),
            Category("Pantry", R.drawable.ic_cupboard_black_24, 3, R.color.gray),
            Category("Documents", R.drawable.ic_baseline_payments_24, 4, R.color.black),
            Category("Payments", R.drawable.ic_baseline_shopping_cart_24, 5, R.color.purple_500) ,
            Category("Medicine", R.drawable.ic_baseline_medical_services_24, 6, R.color.red)
        )
        value = CategoryViewAdapter(categoryList)
    }
    val categoryViewAdapter: MutableLiveData<CategoryViewAdapter> = _categoryViewAdapter
}