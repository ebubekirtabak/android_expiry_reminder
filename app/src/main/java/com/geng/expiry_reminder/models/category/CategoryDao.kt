package com.geng.expiry_reminder.models.category

import androidx.room.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid IN (:categoryIds)")
    fun loadAllByIds(categoryIds: IntArray): List<Category>

    @Query("SELECT * FROM category WHERE name LIKE '%' || :name || '%' LIMIT 1")
    fun findByName(name: String): Category

    @Insert
    fun insert(vararg category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)

    @Delete
    fun delete(category: Category)

    @Query("DELETE FROM category")
    fun deleteAll()
}