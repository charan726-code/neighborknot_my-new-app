package com.example.data.repository

import com.example.data.local.BarterDao
import com.example.data.local.NeighborknotDatabase
import com.example.data.model.BarterListing
import com.example.data.model.KnotExchange
import com.example.data.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NeighborknotRepository(
    private val database: NeighborknotDatabase
) {
    private val dao: BarterDao = database.barterDao()

    val allListings: Flow<List<BarterListing>> = dao.getAllListings()
    val allExchanges: Flow<List<KnotExchange>> = dao.getAllExchanges()
    val userProfile: Flow<UserProfile?> = dao.getUserProfile()

    suspend fun createListing(listing: BarterListing): Long = withContext(Dispatchers.IO) {
        dao.insertListing(listing)
    }

    suspend fun proposeKnotExchange(exchange: KnotExchange): Long = withContext(Dispatchers.IO) {
        dao.insertExchange(exchange)
    }

    suspend fun updateExchangeStatus(id: Long, status: String) = withContext(Dispatchers.IO) {
        dao.updateExchangeStatus(id, status)
        if (status == "KNOT_FINALIZED") {
            dao.addKarmaAndTiedKnot(15)
        }
    }

    suspend fun deleteListing(id: Long) = withContext(Dispatchers.IO) {
        dao.deleteListing(id)
    }

    suspend fun ensureInitialized() = withContext(Dispatchers.IO) {
        database.seedInitialData()
    }
}
