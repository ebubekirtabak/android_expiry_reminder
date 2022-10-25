package com.geng.expiry_reminder.activities.NewCategoryActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.adapters.ColorItemAdapter
import com.geng.expiry_reminder.adapters.IconItemAdapter
import com.geng.expiry_reminder.database.AppDatabase
import com.geng.expiry_reminder.models.category.Category
import com.geng.expiry_reminder.models.items.ColorItem
import com.geng.expiry_reminder.models.items.IconItem
import com.geng.expiry_reminder.utils.AppThemeUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.lang.Exception

class NewCategoryActivity : AppCompatActivity() {

    private var selectedColor: ColorItem = ColorItem(0, "Red", R.color.red)
    private lateinit var selectedIcon: IconItem
    private lateinit var colorItemAdapter: IconItemAdapter
    private lateinit var categoryNameText: EditText
    private val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(AppThemeUtils().getAppThemeStyle(this@NewCategoryActivity));
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val saveButton: FloatingActionButton = findViewById(R.id.save_fab)
        val colorSpinner: Spinner = findViewById(R.id.color_spinner)
        val iconSpinner: Spinner = findViewById(R.id.icon_spinner)
        categoryNameText = findViewById(R.id.category_name_input)
        initIconSpinner(iconSpinner)
        val colorItemAdapter = ColorItemAdapter(
            applicationContext,
            R.layout.color_spinner_item,
            listOf(
                ColorItem(0, "Red", R.color.red),
                ColorItem(0, "Blue", R.color.blue),
                ColorItem(0, "Black", R.color.black),
                ColorItem(0, "Gray", R.color.gray),
                ColorItem(0, "Deep Jungle Green", R.color.deep_jungle_green),
                ColorItem(0, "Sea Green", R.color.sea_green),
                ColorItem(0, "Purple", R.color.purple_500),
                ColorItem(0, "Dark Purple", R.color.dark_purple),
                ColorItem(0, "United Nation Blue", R.color.united_nation_blue),
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
                updateIconSpinnerAdapter()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
        colorSpinner.adapter = colorItemAdapter
        saveButton.setOnClickListener { view ->
            categoryNameText.error = ""
            val category = Category(0, categoryNameText.text.toString(), selectedIcon.icon, 0, selectedColor.color)
            saveCategory(category)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
            applicationContext,
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
                IconItem(0, "Home", selectedColor.color, R.drawable.ic_home_black_24dp),
                IconItem(0, "Boxes", selectedColor.color, R.drawable.ic_baseline_category_24)
            )
        )
        return iconList
    }

    fun updateIconSpinnerAdapter() {
        colorItemAdapter.clear()
        colorItemAdapter.addAll(getIconList())
        colorItemAdapter.notifyDataSetChanged()
    }

    private fun saveCategory(category: Category): Boolean {
        if (category.name.isEmpty()) {
            categoryNameText.error = resources.getString(R.string.category_name_is_not_valid)
            showToastMessage(resources.getString(R.string.category_name_is_not_valid))
            return false
        }

        val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
        try {
            database.CategoryDao().insert(category)
            showToastMessage(resources.getString(R.string.category_created))
            finish()
        } catch (error: Exception) {
            Log.e("saveCategory ", error.message.toString())
            return false
        }

        return true
    }

    private fun showToastMessage(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}