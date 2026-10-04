package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.KnotExchange
import com.example.ui.components.KnotGlyph
import com.example.ui.components.TheKnotLedgerCard
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberTint
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CeramicLinen
import com.example.ui.theme.MintAccent
import com.example.ui.theme.MintDark
import com.example.ui.theme.MintTint
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TertiaryAmberWarm

@Composable
fun KnotLedgerScreen(
    exchanges: List<KnotExchange>,
    onCompleteTrade: (KnotExchange) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("All") }

    val filteredList = when (selectedFilter) {
        "Active" -> exchanges.filter { it.status != "KNOT_FINALIZED" }
        "Finalized" -> exchanges.filter { it.status == "KNOT_FINALIZED" }
        else -> exchanges
    }

    val activeCount = exchanges.count { it.status != "KNOT_FINALIZED" }
    val finalizedCount = exchanges.count { it.status == "KNOT_FINALIZED" }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CeramicLinen)
            .testTag("knot_ledger_screen"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Explanatory Ledger Hero Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(AmberTint),
                                contentAlignment = Alignment.Center
                            ) {
                                KnotGlyph(
                                    color = AmberDark,
                                    size = 20.dp,
                                    strokeWidth = 3.5f
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "The Knot Barter Ledger",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SlateInk
                                )
                                Text(
                                    text = "Reciprocal Neighbor Ledger",
                                    fontSize = 11.sp,
                                    color = SlateInkVariant
                                )
                            }
                        }

                        // Summary Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(SageTint)
                                .border(1.dp, PrimarySageMedium, RoundedCornerShape(9999.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$activeCount In Motion",
                                color = PrimarySage,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Reciprocal trade without money. Two neighbors agree on a handshake, exchange goods or skills, and tie the knot to build mutual neighborhood trust.",
                        fontSize = 12.sp,
                        color = SlateInkVariant,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // Ledger Filter Tabs
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Active", "Finalized").forEach { filter ->
                    val isSelected = selectedFilter == filter
                    val count = when (filter) {
                        "Active" -> activeCount
                        "Finalized" -> finalizedCount
                        else -> exchanges.size
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) PrimarySage else Color.White)
                            .border(1.dp, if (isSelected) PrimarySage else CardBorder, RoundedCornerShape(10.dp))
                            .clickable { selectedFilter = filter }
                            .padding(vertical = 9.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$filter ($count)",
                            color = if (isSelected) Color.White else SlateInk,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        // List of Knot Barter Ledger Cards
        if (filteredList.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Handshake,
                            contentDescription = null,
                            tint = SlateInkVariant,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No exchanges in this category",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SlateInk
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Browse the Noticeboard to propose a barter trade with a neighbor.",
                            fontSize = 12.sp,
                            color = SlateInkVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(filteredList, key = { it.id }) { exchange ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    TheKnotLedgerCard(
                        exchange = exchange,
                        onCompleteTrade = onCompleteTrade
                    )
                }
            }
        }
    }
}
