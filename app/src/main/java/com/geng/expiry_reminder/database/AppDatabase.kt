package com.geng.expiry_reminder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geng.expiry_reminder.models.category.Category
import com.geng.expiry_reminder.models.category.CategoryDao

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CategoryDao(): CategoryDao
}