package com.geng.expiry_reminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.models.category.Category
import com.geng.expiry_reminder.models.category.CategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CategoryDao(): CategoryDao

    private class AppDatabaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val categoryDao = database.CategoryDao()
                    val categoryList = arrayListOf<Category>(
                        Category(0,"Fridge", R.drawable.ic_baseline_food_bank_24, 1, R.color.purple_500),
                        Category(0,"Freezer", R.drawable.ic_baseline_kitchen_24, 2, R.color.blue),
                        Category(0,"Pantry", R.drawable.ic_cupboard_black_24, 3, R.color.gray),
                        Category(0,"Documents", R.drawable.ic_baseline_insert_drive_file_24, 4, R.color.deep_jungle_green),
                        Category(0,"Payments", R.drawable.ic_baseline_shopping_cart_24, 5, R.color.purple_500) ,
                        Category(0,"Medicine", R.drawable.ic_baseline_medical_services_24, 6, R.color.red),
                    )
                    categoryDao.insertAll(categoryList)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ReminderDatabase"
                )
                    .addCallback(AppDatabaseCallBack(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}