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
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SecondaryTerracottaVibrant
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.TerracottaTint

/**
 * Neighborhood Radius Slider:
 * A thick, tactile sage track with a terracotta thumb indicating hyper-local walking or biking distance.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadiusSlider(
    radiusMiles: Float,
    onRadiusChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val walkTimeMins = (radiusMiles * 20).toInt()
    val bikeTimeMins = (radiusMiles * 5).toInt()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag("radius_slider_container")
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
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(SageTint),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.NearMe,
                            contentDescription = "Neighborhood Radius",
                            tint = PrimarySage,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Neighborhood Radius",
                        color = SlateInk,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Radius display pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(9999.dp))
                        .background(TerracottaTint)
                        .border(1.dp, SecondaryTerracottaVibrant.copy(alpha = 0.3f), RoundedCornerShape(9999.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "${String.format("%.1f", radiusMiles)} miles",
                        color = SecondaryTerracottaVibrant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Thick tactile Slider
            Slider(
                value = radiusMiles,
                onValueChange = onRadiusChange,
                valueRange = 0.5f..5.0f,
                steps = 8,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("radius_slider"),
                colors = SliderDefaults.colors(
                    thumbColor = SecondaryTerracottaVibrant,
                    activeTrackColor = PrimarySageMedium,
                    inactiveTrackColor = SageTint,
                    activeTickColor = Color.White.copy(alpha = 0.7f),
                    inactiveTickColor = PrimarySageMedium.copy(alpha = 0.3f)
                ),
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .shadow(4.dp, CircleShape)
                            .clip(CircleShape)
                            .background(SecondaryTerracottaVibrant)
                            .border(2.5.dp, Color.White, CircleShape)
                    )
                }
            )

            // Walking & Biking travel distance info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsWalk,
                        contentDescription = "Walking reach",
                        tint = SlateInkVariant,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "~$walkTimeMins min walk",
                        fontSize = 11.sp,
                        color = SlateInkVariant
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsBike,
                        contentDescription = "Biking reach",
                        tint = SlateInkVariant,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "~$bikeTimeMins min bike",
                        fontSize = 11.sp,
                        color = SlateInkVariant
                    )
                }

                Text(
                    text = "Hyper-local circle",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimarySage
                )
            }
        }
    }
}
