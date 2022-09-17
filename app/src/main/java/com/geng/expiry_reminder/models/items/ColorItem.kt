package com.geng.expiry_reminder.models.items

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo

data class ColorItem(
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @DrawableRes
    val color: Int
)
