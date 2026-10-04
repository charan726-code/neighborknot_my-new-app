package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barter_listings")
data class BarterListing(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val type: String, // "OFFERING" or "REQUESTING"
    val category: String, // "Skill Share", "Homegrown & Pantry", "Tools & Workshop", "Craft & Ceramics", "Care & Household"
    val hasText: String, // What neighbor has/offers
    val seeksText: String, // What neighbor seeks in exchange
    val neighborName: String,
    val neighborHandle: String,
    val neighborBio: String,
    val neighborKarma: Int,
    val distanceMiles: Double,
    val timeAgoText: String,
    val localDrawableName: String? = null,
    val hotlinkImageUrl: String? = null,
    val status: String = "ACTIVE", // "ACTIVE", "IN_NEGOTIATION", "FINALIZED"
    val createdAt: Long = System.currentTimeMillis()
)
