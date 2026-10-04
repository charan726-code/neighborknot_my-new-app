package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.KnotExchange
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberTint
import com.example.ui.theme.CardBorder
import com.example.ui.theme.MintAccent
import com.example.ui.theme.MintDark
import com.example.ui.theme.MintTint
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SecondaryTerracottaVibrant
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TerracottaTint
import com.example.ui.theme.TertiaryAmberWarm

/**
 * The "Knot" Barter Ledger Card:
 * A two-sided reciprocal card where two neighbor avatars are connected
 * by a warm amber line with interactive item nodes on both ends.
 */
@Composable
fun TheKnotLedgerCard(
    exchange: KnotExchange,
    onCompleteTrade: (KnotExchange) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFinalized = exchange.status == "KNOT_FINALIZED"
    val isAccepted = exchange.status == "TRADE_ACCEPTED"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, if (isFinalized) MintAccent.copy(alpha = 0.5f) else CardBorder, RoundedCornerShape(16.dp))
            .testTag("knot_ledger_card_${exchange.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Status Header Pill & Distance
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Status indicator
                when {
                    isFinalized -> {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(MintTint)
                                .border(1.dp, MintAccent, RoundedCornerShape(9999.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Trade Finalized",
                                    tint = MintDark,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "TRADE FINALIZED",
                                    color = MintDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }
                    isAccepted -> {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(AmberTint)
                                .border(1.dp, TertiaryAmberWarm, RoundedCornerShape(9999.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Handshake,
                                    contentDescription = "Handshake Accepted",
                                    tint = AmberDark,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "HANDSHAKE ACCEPTED",
                                    color = AmberDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }
                    else -> {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(SurfaceContainerLow)
                                .border(1.dp, CardBorder, RoundedCornerShape(9999.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.HourglassTop,
                                    contentDescription = "Offer Pending",
                                    tint = SlateInkVariant,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "OFFER PROPOSED",
                                    color = SlateInkVariant,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }
                }

                // Neighborhood distance
                Text(
                    text = exchange.distanceText,
                    color = SlateInkVariant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // The Two-Sided Reciprocal Connection Graphic
            // Left: You | Center: Connecting Amber Rope with Knot Glyph | Right: Neighbor
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User Avatar (You)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(PrimarySage)
                            .border(2.dp, PrimarySageMedium, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "YOU",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Julian",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateInk
                    )
                }

                // Connecting Rope & Center Knot
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Warm amber connecting line
                    Canvas(modifier = Modifier.fillMaxWidth().height(4.dp)) {
                        drawLine(
                            color = TertiaryAmberWarm,
                            start = Offset(0f, size.height / 2),
                            end = Offset(size.width, size.height / 2),
                            strokeWidth = 3f,
                            pathEffect = if (!isFinalized) PathEffect.dashPathEffect(floatArrayOf(10f, 6f), 0f) else null
                        )
                    }

                    // Central Knot badge
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(if (isFinalized) MintTint else AmberTint)
                            .border(1.5.dp, if (isFinalized) MintAccent else TertiaryAmberWarm, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        KnotGlyph(
                            color = if (isFinalized) MintDark else AmberDark,
                            size = 18.dp,
                            strokeWidth = 3.5f
                        )
                    }
                }

                // Neighbor Avatar
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(SecondaryTerracottaVibrant)
                            .border(2.dp, SecondaryTerracottaVibrant, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = exchange.neighborName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString(""),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = exchange.neighborName.split(" ").firstOrNull() ?: exchange.neighborName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateInk,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Two Item Nodes on both ends
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Left Node: Your side
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(SageTint)
                        .border(1.dp, PrimarySageMedium.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "YOUR OFFERING",
                        color = PrimarySage,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = exchange.myItem,
                        color = SlateInk,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Right Node: Neighbor's side
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(TerracottaTint)
                        .border(1.dp, SecondaryTerracottaVibrant.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "THEIR RECIPROCAL",
                        color = SecondaryTerracottaVibrant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = exchange.neighborItem,
                        color = SlateInk,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Meeting location & notes
            if (exchange.meetingLocation.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Handshake Spot",
                        tint = SlateInkVariant,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = exchange.meetingLocation,
                        color = SlateInkVariant,
                        fontSize = 12.sp
                    )
                }
            }

            if (exchange.notes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "“${exchange.notes}”",
                    color = SlateInkVariant,
                    fontSize = 11.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    lineHeight = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action: Finalize Trade / Confirm Knot
            if (!isFinalized) {
                Button(
                    onClick = { onCompleteTrade(exchange) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("complete_knot_btn_${exchange.id}"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimarySage,
                        contentColor = Color.White
                    )
                ) {
                    KnotGlyph(color = Color.White, size = 16.dp, strokeWidth = 3f)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Handshake Done · Tie the Knot (+${exchange.mutualKarma} Karma)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MintTint)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MintDark,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Knot Tied! Reciprocal exchange completed.",
                        color = MintDark,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
