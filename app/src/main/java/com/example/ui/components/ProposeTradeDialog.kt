package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import com.example.data.model.BarterListing
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberTint
import com.example.ui.theme.CardBorder
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
fun ProposeTradeDialog(
    listing: BarterListing,
    onDismiss: () -> Unit,
    onPropose: (myItem: String, location: String, notes: String) -> Unit
) {
    var myOffering by remember { mutableStateOf(listing.seeksText) }
    var meetLocation by remember { mutableStateOf("Maple & 4th Community Park Bench") }
    var tradeNotes by remember { mutableStateOf("Hi ${listing.neighborName.split(" ").firstOrNull() ?: ""}, I’d love to trade! Let me know if that works for you.") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
                .testTag("propose_trade_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(SageTint),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Handshake,
                                contentDescription = null,
                                tint = PrimarySage,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Propose Barter",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = SlateInk
                            )
                            Text(
                                text = "with ${listing.neighborName}",
                                fontSize = 12.sp,
                                color = SlateInkVariant
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = SlateInkVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Listing summary card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = listing.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateInk
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "They seek: ${listing.seeksText}",
                        fontSize = 12.sp,
                        color = SecondaryTerracottaVibrant,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Input: What you will offer
                Text(
                    text = "What will you offer in return?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = myOffering,
                    onValueChange = { myOffering = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("trade_offering_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    placeholder = {
                        Text("e.g., 2 loaves fresh focaccia, or 2 hours garden help", fontSize = 13.sp)
                    },
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Input: Meeting spot
                Text(
                    text = "Suggested handshake location",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = meetLocation,
                    onValueChange = { meetLocation = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("trade_location_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Input: Friendly note
                Text(
                    text = "Neighborly note",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = tradeNotes,
                    onValueChange = { tradeNotes = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("trade_notes_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Submit button
                Button(
                    onClick = {
                        if (myOffering.isNotBlank()) {
                            onPropose(myOffering, meetLocation, tradeNotes)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("confirm_propose_btn"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimarySage,
                        contentColor = Color.White
                    )
                ) {
                    KnotGlyph(color = Color.White, size = 18.dp, strokeWidth = 3f)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Send Barter Proposal",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
