package com.geng.expiry_reminder.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.geng.expiry_reminder.adapters.CategoryViewAdapter
import com.geng.expiry_reminder.database.AppDatabase
import com.geng.expiry_reminder.models.category.Category
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _categoryViewAdapter = MutableLiveData<CategoryViewAdapter>().apply {
        val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { AppDatabase.getDatabase(application.applicationContext, applicationScope) }
        val categoryList = database.CategoryDao().getAll() as ArrayList<Category>
        value = CategoryViewAdapter(categoryList)
    }

    val categoryViewAdapter: MutableLiveData<CategoryViewAdapter> = _categoryViewAdapter
}