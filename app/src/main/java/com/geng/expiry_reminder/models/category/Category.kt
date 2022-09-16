package com.geng.expiry_reminder.models.category

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @DrawableRes @ColumnInfo(name = "icon")
    val icon: Int,
    val id: Int,
    @DrawableRes @ColumnInfo(name = "color")
    val color: Int
)