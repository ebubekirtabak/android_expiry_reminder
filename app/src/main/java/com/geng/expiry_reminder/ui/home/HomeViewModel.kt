package com.geng.expiry_reminder.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geng.expiry_reminder.adapters.CategoryViewAdapter
import com.geng.expiry_reminder.models.category.Category

class HomeViewModel : ViewModel() {
    private val _categoryViewAdapter = MutableLiveData<CategoryViewAdapter>().apply {
        val categoryList = arrayListOf<Category>(
            Category("Fridge", 0, 1),
            Category("Documents", 0, 2),
            Category("Payment", 0, 1)
        )
        value = CategoryViewAdapter(categoryList)
    }
    val categoryViewAdapter: MutableLiveData<CategoryViewAdapter> = _categoryViewAdapter
}