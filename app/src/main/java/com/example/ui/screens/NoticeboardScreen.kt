package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.BarterListing
import com.example.ui.components.KnotGlyph
import com.example.ui.components.RadiusSlider
import com.example.ui.components.TradeListingCard
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CeramicLinen
import com.example.ui.theme.DebossedFieldBg
import com.example.ui.theme.DebossedFieldBorder
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SecondaryTerracottaVibrant
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TerracottaTint
import com.example.ui.theme.TertiaryAmberWarm

@Composable
fun NoticeboardScreen(
    listings: List<BarterListing>,
    radiusMiles: Float,
    onRadiusChange: (Float) -> Unit,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedFilter: String,
    onFilterSelect: (String) -> Unit,
    onProposeTradeClick: (BarterListing) -> Unit,
    onMessageClick: (BarterListing) -> Unit,
    onPostListingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf(
        "All",
        "Offering",
        "Requesting",
        "Homegrown & Pantry",
        "Tools & Workshop",
        "Skill Share",
        "Craft & Ceramics"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CeramicLinen)
            .testTag("noticeboard_feed"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Hero Community Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(PrimarySage)
                    .testTag("community_hero_banner")
            ) {
                // Background hero image
                Image(
                    painter = painterResource(id = R.drawable.img_community_hero),
                    contentDescription = "Community Barter Noticeboard",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop,
                    alpha = 0.35f
                )

                // Overlay Text & Ethos
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        KnotGlyph(
                            color = TertiaryAmberWarm,
                            size = 18.dp,
                            strokeWidth = 3.5f
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "COMMUNITY NOTICEBOARD",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Barter skills & goods.\nNo money needed.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Sourdough, bike tunes, garden harvests, and workshop tools traded within walking distance.",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // Search Input (Ceramic Inset Look)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("noticeboard_search_input"),
                    placeholder = {
                        Text(
                            text = "Search sourdough, tools, bike repair...",
                            color = SlateInkVariant.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = PrimarySageMedium
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = SlateInkVariant
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    singleLine = true
                )
            }
        }

        // Neighborhood Radius Slider
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                RadiusSlider(
                    radiusMiles = radiusMiles,
                    onRadiusChange = onRadiusChange
                )
            }
        }

        // Filter Pills Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    val isOfferingChip = filter == "Offering"
                    val isRequestingChip = filter == "Requesting"

                    val bgColor = when {
                        isSelected && isOfferingChip -> SageTint
                        isSelected && isRequestingChip -> TerracottaTint
                        isSelected -> PrimarySage
                        else -> Color.White
                    }

                    val textColor = when {
                        isSelected && isOfferingChip -> PrimarySage
                        isSelected && isRequestingChip -> SecondaryTerracottaVibrant
                        isSelected -> Color.White
                        else -> SlateInk
                    }

                    val borderColor = when {
                        isSelected && isOfferingChip -> PrimarySage
                        isSelected && isRequestingChip -> SecondaryTerracottaVibrant
                        isSelected -> PrimarySage
                        else -> CardBorder
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(9999.dp))
                            .background(bgColor)
                            .border(1.dp, borderColor, RoundedCornerShape(9999.dp))
                            .clickable { onFilterSelect(filter) }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                            .testTag("filter_chip_$filter")
                    ) {
                        Text(
                            text = filter,
                            color = textColor,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Results Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${listings.size} Local Knots nearby",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Text(
                    text = "Within ${String.format("%.1f", radiusMiles)} mi",
                    fontSize = 12.sp,
                    color = SlateInkVariant
                )
            }
        }

        // Empty state
        if (listings.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerLow)
                            .border(1.dp, CardBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = SlateInkVariant,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No listings in this radius",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateInk
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Try expanding your neighborhood radius slider or clearing search terms to find more reciprocal trades.",
                        fontSize = 13.sp,
                        color = SlateInkVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onRadiusChange(5.0f)
                            onSearchChange("")
                            onFilterSelect("All")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimarySage)
                    ) {
                        Text("Expand Radius to 5 mi")
                    }
                }
            }
        } else {
            // Listings Items
            items(listings, key = { it.id }) { listing ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    TradeListingCard(
                        listing = listing,
                        onProposeTradeClick = onProposeTradeClick,
                        onMessageClick = onMessageClick
                    )
                }
            }
        }
    }
}
