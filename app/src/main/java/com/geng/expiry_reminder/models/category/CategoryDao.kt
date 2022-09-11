package com.geng.expiry_reminder.models.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid IN (:categoryIds)")
    fun loadAllByIds(categoryIds: IntArray): List<Category>

    @Query("SELECT * FROM category WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String, last: String): Category

    @Insert
    fun insertAll(vararg categories: Category)

    @Delete
    fun delete(category: Category)
}