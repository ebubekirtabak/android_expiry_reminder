package com.geng.expiry_reminder.models.category

import androidx.annotation.DrawableRes

class Category(
    val name: String,
    @DrawableRes
    val icon: Int,
    val id: Int
)