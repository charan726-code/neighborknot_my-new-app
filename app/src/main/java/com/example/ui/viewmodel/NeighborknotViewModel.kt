package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.NeighborknotDatabase
import com.example.data.model.BarterListing
import com.example.data.model.KnotExchange
import com.example.data.model.UserProfile
import com.example.data.repository.NeighborknotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NeighborknotViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NeighborknotRepository

    init {
        val database = NeighborknotDatabase.getInstance(application)
        repository = NeighborknotRepository(database)
        viewModelScope.launch {
            repository.ensureInitialized()
        }
    }

    // Filter states
    val radiusMiles = MutableStateFlow(2.5f)
    val searchQuery = MutableStateFlow("")
    val selectedFilter = MutableStateFlow("All") // "All", "Offering", "Requesting", "Skill Share", "Homegrown & Pantry", "Tools & Workshop"

    // Trade Dialog state
    val tradeDialogListing = MutableStateFlow<BarterListing?>(null)

    // Temporary notification / feedback toast message
    val snackbarMessage = MutableStateFlow<String?>(null)

    // User Profile
    val userProfile: StateFlow<UserProfile?> = repository.userProfile
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserProfile(
                name = "Julian Mercer",
                handle = "@julian_m",
                neighborhood = "Maple & 4th Urban Commons",
                bio = "Woodworker & urban gardener on Maple St · 100% reciprocal exchange",
                karmaScore = 168,
                knotsTiedCount = 19,
                zeroWasteKg = 34.5,
                activeTradesCount = 3
            )
        )

    // Knot Exchanges
    val allExchanges: StateFlow<List<KnotExchange>> = repository.allExchanges
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Filtered Listings
    val filteredListings: StateFlow<List<BarterListing>> = combine(
        repository.allListings,
        radiusMiles,
        searchQuery,
        selectedFilter
    ) { listings, radius, query, filter ->
        listings.filter { item ->
            // Distance filter
            val inRadius = item.distanceMiles <= radius

            // Search query
            val matchesQuery = query.isBlank() ||
                    item.title.contains(query, ignoreCase = true) ||
                    item.hasText.contains(query, ignoreCase = true) ||
                    item.seeksText.contains(query, ignoreCase = true) ||
                    item.neighborName.contains(query, ignoreCase = true)

            // Category or Type filter
            val matchesFilter = when (filter) {
                "All" -> true
                "Offering" -> item.type == "OFFERING"
                "Requesting" -> item.type == "REQUESTING"
                else -> item.category.equals(filter, ignoreCase = true)
            }

            inRadius && matchesQuery && matchesFilter
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setRadius(radius: Float) {
        radiusMiles.value = radius
    }

    fun setSearch(query: String) {
        searchQuery.value = query
    }

    fun setFilter(filter: String) {
        selectedFilter.value = filter
    }

    fun openTradeDialog(listing: BarterListing) {
        tradeDialogListing.value = listing
    }

    fun closeTradeDialog() {
        tradeDialogListing.value = null
    }

    fun proposeTrade(listing: BarterListing, myOffering: String, location: String, notes: String) {
        viewModelScope.launch {
            val exchange = KnotExchange(
                listingId = listing.id,
                myItem = myOffering,
                neighborName = listing.neighborName,
                neighborHandle = listing.neighborHandle,
                neighborItem = if (listing.type == "OFFERING") listing.title else listing.seeksText,
                distanceText = "${listing.distanceMiles} mi · ${location.split(" ").take(2).joinToString(" ")}",
                status = "OFFER_SENT",
                meetingLocation = location,
                mutualKarma = 15,
                notes = notes
            )
            repository.proposeKnotExchange(exchange)
            closeTradeDialog()
            snackbarMessage.value = "Knot proposal sent to ${listing.neighborName}!"
        }
    }

    fun completeTrade(exchange: KnotExchange) {
        viewModelScope.launch {
            repository.updateExchangeStatus(exchange.id, "KNOT_FINALIZED")
            snackbarMessage.value = "Knot Tied with ${exchange.neighborName}! +${exchange.mutualKarma} Karma awarded."
        }
    }

    fun createNewListing(
        title: String,
        type: String,
        category: String,
        hasText: String,
        seeksText: String
    ) {
        viewModelScope.launch {
            val newListing = BarterListing(
                title = title,
                type = type,
                category = category,
                hasText = hasText,
                seeksText = seeksText,
                neighborName = "Julian Mercer (You)",
                neighborHandle = "@julian_m",
                neighborBio = "Maple St Urban Commons · Zero-waste community member",
                neighborKarma = userProfile.value?.karmaScore ?: 168,
                distanceMiles = 0.1,
                timeAgoText = "0.1 mi away · Just now",
                localDrawableName = if (category.contains("Homegrown", true)) "img_sourdough_jar" else "img_tools_harvest",
                status = "ACTIVE"
            )
            repository.createListing(newListing)
            snackbarMessage.value = "Your barter listing is live on the neighborhood board!"
        }
    }

    fun clearSnackbar() {
        snackbarMessage.value = null
    }
}
