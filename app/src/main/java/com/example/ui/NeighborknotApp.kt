package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Diversity3
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.BarterListing
import com.example.ui.components.ChatNeighborDialog
import com.example.ui.components.KnotGlyph
import com.example.ui.components.NeighborknotTopBar
import com.example.ui.components.ProposeTradeDialog
import com.example.ui.screens.CommunityKarmaScreen
import com.example.ui.screens.KnotLedgerScreen
import com.example.ui.screens.NewKnotScreen
import com.example.ui.screens.NoticeboardScreen
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CeramicLinen
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SecondaryTerracottaVibrant
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TertiaryAmberWarm
import com.example.ui.viewmodel.NeighborknotViewModel

@Composable
fun NeighborknotApp(
    viewModel: NeighborknotViewModel = viewModel()
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    val listings by viewModel.filteredListings.collectAsStateWithLifecycle()
    val exchanges by viewModel.allExchanges.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val radiusMiles by viewModel.radiusMiles.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedFilter by viewModel.selectedFilter.collectAsStateWithLifecycle()
    val tradeDialogListing by viewModel.tradeDialogListing.collectAsStateWithLifecycle()
    val snackbarMessage by viewModel.snackbarMessage.collectAsStateWithLifecycle()

    var chatListing by remember { mutableStateOf<BarterListing?>(null) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSnackbar()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NeighborknotTopBar(
                userKarma = userProfile?.karmaScore ?: 168,
                onKarmaClick = { selectedTab = 3 }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .border(1.dp, CardBorder)
                    .testTag("app_navigation_bar")
            ) {
                // Tab 0: Noticeboard
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab == 0) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "Noticeboard"
                        )
                    },
                    label = {
                        Text(
                            text = "Noticeboard",
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimarySage,
                        selectedTextColor = PrimarySage,
                        unselectedIconColor = SlateInkVariant,
                        unselectedTextColor = SlateInkVariant,
                        indicatorColor = SageTint
                    ),
                    modifier = Modifier.testTag("nav_tab_noticeboard")
                )

                // Tab 1: The Knot Barter Ledger
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        KnotGlyph(
                            color = if (selectedTab == 1) PrimarySage else SlateInkVariant,
                            size = 20.dp,
                            strokeWidth = if (selectedTab == 1) 3.5f else 2.5f
                        )
                    },
                    label = {
                        Text(
                            text = "The Knot",
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimarySage,
                        selectedTextColor = PrimarySage,
                        unselectedIconColor = SlateInkVariant,
                        unselectedTextColor = SlateInkVariant,
                        indicatorColor = SageTint
                    ),
                    modifier = Modifier.testTag("nav_tab_the_knot")
                )

                // Tab 2: Tie a Knot (Post)
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Post Barter"
                        )
                    },
                    label = {
                        Text(
                            text = "Tie Knot",
                            fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimarySage,
                        selectedTextColor = PrimarySage,
                        unselectedIconColor = SlateInkVariant,
                        unselectedTextColor = SlateInkVariant,
                        indicatorColor = SageTint
                    ),
                    modifier = Modifier.testTag("nav_tab_tie_knot")
                )

                // Tab 3: Community & Karma
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab == 3) Icons.Filled.Diversity3 else Icons.Outlined.Diversity3,
                            contentDescription = "Karma"
                        )
                    },
                    label = {
                        Text(
                            text = "Karma",
                            fontWeight = if (selectedTab == 3) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimarySage,
                        selectedTextColor = PrimarySage,
                        unselectedIconColor = SlateInkVariant,
                        unselectedTextColor = SlateInkVariant,
                        indicatorColor = SageTint
                    ),
                    modifier = Modifier.testTag("nav_tab_karma")
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 70.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(CeramicLinen)
        ) {
            when (selectedTab) {
                0 -> NoticeboardScreen(
                    listings = listings,
                    radiusMiles = radiusMiles,
                    onRadiusChange = { viewModel.setRadius(it) },
                    searchQuery = searchQuery,
                    onSearchChange = { viewModel.setSearch(it) },
                    selectedFilter = selectedFilter,
                    onFilterSelect = { viewModel.setFilter(it) },
                    onProposeTradeClick = { viewModel.openTradeDialog(it) },
                    onMessageClick = { chatListing = it },
                    onPostListingClick = { selectedTab = 2 }
                )
                1 -> KnotLedgerScreen(
                    exchanges = exchanges,
                    onCompleteTrade = { viewModel.completeTrade(it) }
                )
                2 -> NewKnotScreen(
                    onSubmitListing = { title, type, category, has, seeks ->
                        viewModel.createNewListing(title, type, category, has, seeks)
                        selectedTab = 0 // Return to noticeboard
                    }
                )
                3 -> CommunityKarmaScreen(
                    userProfile = userProfile
                )
            }

            // Propose Trade Dialog
            tradeDialogListing?.let { listing ->
                ProposeTradeDialog(
                    listing = listing,
                    onDismiss = { viewModel.closeTradeDialog() },
                    onPropose = { myItem, location, notes ->
                        viewModel.proposeTrade(listing, myItem, location, notes)
                        selectedTab = 1 // Switch to ledger to show the new in-flight proposal!
                    }
                )
            }

            // Chat with Neighbor Dialog
            chatListing?.let { listing ->
                ChatNeighborDialog(
                    listing = listing,
                    onDismiss = { chatListing = null }
                )
            }
        }
    }
}
