package com.geng.expiry_reminder.models.items

import androidx.annotation.DrawableRes

data class IconItem(
    val uid: Int,
    val name: String,
    @DrawableRes
    val color: Int,
    @DrawableRes
    val icon: Int
)
