package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "knot_exchanges")
data class KnotExchange(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val listingId: Long = 0,
    val myItem: String,
    val neighborName: String,
    val neighborHandle: String,
    val neighborItem: String,
    val distanceText: String,
    val status: String, // "OFFER_SENT", "COUNTER_PROPOSED", "TRADE_ACCEPTED", "KNOT_FINALIZED"
    val meetingLocation: String = "Corner of 4th & Maple Community Bench",
    val mutualKarma: Int = 15,
    val notes: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)
