package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.BarterListing
import com.example.data.model.KnotExchange
import com.example.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface BarterDao {
    @Query("SELECT * FROM barter_listings ORDER BY id DESC")
    fun getAllListings(): Flow<List<BarterListing>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: BarterListing): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllListings(listings: List<BarterListing>)

    @Update
    suspend fun updateListing(listing: BarterListing)

    @Query("DELETE FROM barter_listings WHERE id = :id")
    suspend fun deleteListing(id: Long)

    @Query("SELECT * FROM knot_exchanges ORDER BY updatedAt DESC")
    fun getAllExchanges(): Flow<List<KnotExchange>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchange(exchange: KnotExchange): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExchanges(exchanges: List<KnotExchange>)

    @Update
    suspend fun updateExchange(exchange: KnotExchange)

    @Query("UPDATE knot_exchanges SET status = :status, updatedAt = :timestamp WHERE id = :id")
    suspend fun updateExchangeStatus(id: Long, status: String, timestamp: Long = System.currentTimeMillis())

    @Query("SELECT * FROM user_profiles WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    @Query("UPDATE user_profiles SET karmaScore = karmaScore + :karma, knotsTiedCount = knotsTiedCount + 1 WHERE id = 1")
    suspend fun addKarmaAndTiedKnot(karma: Int)
}
