package com.example.ui.components

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberTint
import com.example.ui.theme.CardBorder
import com.example.ui.theme.CeramicLinen
import com.example.ui.theme.PrimarySage
import com.example.ui.theme.PrimarySageMedium
import com.example.ui.theme.SageTint
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.TertiaryAmberWarm

@Composable
fun NeighborknotTopBar(
    userKarma: Int,
    onKarmaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CeramicLinen)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag("app_top_bar")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Brand Logo & Title
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Knot badge container
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(PrimarySage)
                        .border(1.dp, PrimarySageMedium, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    KnotGlyph(
                        color = Color.White,
                        size = 22.dp,
                        strokeWidth = 3.5f
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Neighborknot",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SlateInk,
                        letterSpacing = (-0.3).sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Neighborhood",
                            tint = PrimarySageMedium,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Maple & 4th Commons",
                            fontSize = 11.sp,
                            color = SlateInkVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Karma Badge (Clickable)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(AmberTint)
                    .border(1.dp, TertiaryAmberWarm.copy(alpha = 0.5f), RoundedCornerShape(9999.dp))
                    .clickable { onKarmaClick() }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .testTag("top_bar_karma_badge")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    KnotGlyph(
                        color = AmberDark,
                        size = 14.dp,
                        strokeWidth = 3f
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "$userKarma Karma",
                        color = AmberDark,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
