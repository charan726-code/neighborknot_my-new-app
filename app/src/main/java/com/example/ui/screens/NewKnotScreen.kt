package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.SouthWest
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.ui.components.KnotGlyph
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
import com.example.ui.theme.TerracottaWash
import com.example.ui.theme.TertiaryAmberWarm

@Composable
fun NewKnotScreen(
    onSubmitListing: (title: String, type: String, category: String, has: String, seeks: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var tradeType by remember { mutableStateOf("OFFERING") } // "OFFERING" or "REQUESTING"
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Homegrown & Pantry") }
    var hasText by remember { mutableStateOf("") }
    var seeksText by remember { mutableStateOf("") }
    var formError by remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        "Homegrown & Pantry",
        "Tools & Workshop",
        "Skill Share",
        "Craft & Ceramics",
        "Care & Household"
    )

    val quickSuggestions = listOf(
        "Loaf of Rosemary Sourdough",
        "Electric Sander 2hr loan",
        "Vine Heirloom Tomatoes",
        "Bicycle Chain & Gear Tuning",
        "Pottery Ceramic Planter",
        "Kombucha Scoby + Starter"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CeramicLinen)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("new_knot_screen")
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(SageTint),
                contentAlignment = Alignment.Center
            ) {
                KnotGlyph(
                    color = PrimarySage,
                    size = 22.dp,
                    strokeWidth = 3.5f
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Tie a New Knot",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = SlateInk
                )
                Text(
                    text = "Post an offer or request on the noticeboard",
                    fontSize = 12.sp,
                    color = SlateInkVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Card Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Type Selector: OFFERING vs REQUESTING
                Text(
                    text = "What kind of post is this?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // OFFERING BUTTON
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (tradeType == "OFFERING") SageTint else Color.White)
                            .border(
                                1.5.dp,
                                if (tradeType == "OFFERING") PrimarySageMedium else CardBorder,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable { tradeType = "OFFERING" }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.NorthEast,
                                contentDescription = null,
                                tint = if (tradeType == "OFFERING") PrimarySage else SlateInkVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "I HAVE (Offer)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (tradeType == "OFFERING") PrimarySage else SlateInk
                            )
                        }
                    }

                    // REQUESTING BUTTON
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (tradeType == "REQUESTING") TerracottaTint else Color.White)
                            .border(
                                1.5.dp,
                                if (tradeType == "REQUESTING") SecondaryTerracottaVibrant else CardBorder,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable { tradeType = "REQUESTING" }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.SouthWest,
                                contentDescription = null,
                                tint = if (tradeType == "REQUESTING") SecondaryTerracottaVibrant else SlateInkVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "I NEED (Request)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (tradeType == "REQUESTING") SecondaryTerracottaVibrant else SlateInk
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title Input
                Text(
                    text = "Listing Title",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("e.g., Active Sourdough Starter & Proofing Banneton", fontSize = 13.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("new_knot_title_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Quick suggestions chips
                Text(
                    text = "Quick Inspiration:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SlateInkVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickSuggestions.forEach { suggestion ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(DebossedFieldBg)
                                .border(1.dp, CardBorder, RoundedCornerShape(6.dp))
                                .clickable {
                                    title = suggestion
                                    if (hasText.isEmpty()) hasText = suggestion
                                }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = suggestion, fontSize = 11.sp, color = PrimarySage)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Category selector
                Text(
                    text = "Category",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    categories.forEach { cat ->
                        val isCatSelected = category == cat
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isCatSelected) PrimarySage else SurfaceContainerLow)
                                .border(1.dp, if (isCatSelected) PrimarySage else CardBorder, RoundedCornerShape(8.dp))
                            .clickable { category = cat }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 11.sp,
                                fontWeight = if (isCatSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isCatSelected) Color.White else SlateInk
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Has Text
                Text(
                    text = if (tradeType == "OFFERING") "What are you offering? (What you have)" else "What can you offer in exchange?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = hasText,
                    onValueChange = { hasText = it },
                    placeholder = {
                        Text(
                            text = if (tradeType == "OFFERING") "Describe the item or skill in detail (quantity, condition, duration)" else "What will you barter in return (e.g. 2 loaves fresh bread, garden labor)",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("new_knot_has_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Seeks Text
                Text(
                    text = if (tradeType == "OFFERING") "What do you seek in return?" else "What do you need?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = seeksText,
                    onValueChange = { seeksText = it },
                    placeholder = {
                        Text(
                            text = if (tradeType == "OFFERING") "e.g., Fresh culinary herbs (rosemary, thyme) or backyard honeycomb" else "e.g., Random orbital sander for Saturday afternoon restoration",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("new_knot_seeks_input"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DebossedFieldBg,
                        unfocusedContainerColor = DebossedFieldBg,
                        focusedBorderColor = PrimarySageMedium,
                        unfocusedBorderColor = DebossedFieldBorder
                    ),
                    maxLines = 3
                )

                if (formError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = formError ?: "",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            formError = "Please enter a listing title"
                        } else if (hasText.isBlank() || seeksText.isBlank()) {
                            formError = "Please describe both what you have and what you seek"
                        } else {
                            formError = null
                            onSubmitListing(title, tradeType, category, hasText, seeksText)
                            // Reset
                            title = ""
                            hasText = ""
                            seeksText = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("submit_new_knot_btn"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimarySage,
                        contentColor = Color.White
                    )
                ) {
                    KnotGlyph(color = Color.White, size = 18.dp, strokeWidth = 3f)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tie Knot & Post to Noticeboard",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}
