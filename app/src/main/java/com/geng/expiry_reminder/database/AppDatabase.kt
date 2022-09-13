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
                    val categoyDao = database.CategoryDao()

                    categoyDao.deleteAll()

                    val category =  Category(0,"Fridge", R.drawable.ic_baseline_food_bank_24, 1, R.color.purple_500)
                    categoyDao.insert(category)
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
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}