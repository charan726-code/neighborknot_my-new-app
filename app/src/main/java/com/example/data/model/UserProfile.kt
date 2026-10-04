package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey
    val id: Long = 1,
    val name: String,
    val handle: String,
    val neighborhood: String,
    val bio: String,
    val karmaScore: Int,
    val knotsTiedCount: Int,
    val zeroWasteKg: Double,
    val activeTradesCount: Int
)
