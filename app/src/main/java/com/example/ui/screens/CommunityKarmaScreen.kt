package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
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
import com.example.data.model.UserProfile
import com.example.ui.components.KnotGlyph
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
import com.example.ui.theme.SecondaryTerracottaVibrant
import com.example.ui.theme.SlateInk
import com.example.ui.theme.SlateInkVariant
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TerracottaTint
import com.example.ui.theme.TertiaryAmberWarm

@Composable
fun CommunityKarmaScreen(
    userProfile: UserProfile?,
    modifier: Modifier = Modifier
) {
    val profile = userProfile ?: UserProfile(
        name = "Julian Mercer",
        handle = "@julian_m",
        neighborhood = "Maple & 4th Urban Commons",
        bio = "Woodworker & urban gardener on Maple St · 100% reciprocal exchange",
        karmaScore = 168,
        knotsTiedCount = 19,
        zeroWasteKg = 34.5,
        activeTradesCount = 3
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CeramicLinen)
            .testTag("community_karma_screen"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Profile Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Large Avatar with sage border
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(PrimarySage)
                                .border(3.dp, PrimarySageMedium, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "JM",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = profile.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = SlateInk
                            )
                            Text(
                                text = profile.handle,
                                fontSize = 13.sp,
                                color = PrimarySage,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = profile.neighborhood,
                                fontSize = 12.sp,
                                color = SlateInkVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = profile.bio,
                        fontSize = 13.sp,
                        color = SlateInk,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Big Karma Metric Box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(AmberTint)
                            .border(1.dp, TertiaryAmberWarm.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            KnotGlyph(
                                color = AmberDark,
                                size = 28.dp,
                                strokeWidth = 4f
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "COMMUNITY KARMA",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AmberDark,
                                    letterSpacing = 1.sp
                                )
                                Text(
                                    text = "${profile.karmaScore} Points",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AmberDark
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Level 4 Pillar",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AmberDark
                            )
                        }
                    }
                }
            }
        }

        // Reciprocal Impact Milestones
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Reciprocal Impact Milestones",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Knots Tied
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White)
                            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
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
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${profile.knotsTiedCount}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimarySage
                        )
                        Text(
                            text = "Knots Tied",
                            fontSize = 11.sp,
                            color = SlateInkVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Zero Waste Saved
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White)
                            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(MintTint),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Eco,
                                contentDescription = null,
                                tint = MintDark,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${profile.zeroWasteKg} kg",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MintDark
                        )
                        Text(
                            text = "Zero-Waste Saved",
                            fontSize = 11.sp,
                            color = SlateInkVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Zero Dollars Spent
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White)
                            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(TerracottaTint),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = SecondaryTerracottaVibrant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "$0",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondaryTerracottaVibrant
                        )
                        Text(
                            text = "100% Barter",
                            fontSize = 11.sp,
                            color = SlateInkVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Trust Badges
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Neighbor Trust Badges",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val badges = listOf(
                        Triple("Master Sourdough Baker", "Shared active rye starter with 12+ households", PrimarySage),
                        Triple("Prompt Exchanger", "Always on time for designated handshake pickups", AmberDark),
                        Triple("Zero Waste Hero", "Diverted over 30kg of goods & packaging from disposal", MintDark),
                        Triple("Workshop Mentor", "Lends tools with safety checks and sandpaper supplies", SecondaryTerracottaVibrant)
                    )

                    badges.forEach { (title, desc, color) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(color.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = color,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SlateInk
                                )
                                Text(
                                    text = desc,
                                    fontSize = 11.sp,
                                    color = SlateInkVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Neighbor Reciprocal Stories
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Neighbor Reciprocal Testimonials",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SlateInk
                )
                Spacer(modifier = Modifier.height(8.dp))

                val testimonials = listOf(
                    Triple(
                        "Clara Jensen",
                        "“Julian's sourdough was still warm from the oven! In return my mint harvest made incredible herbal tea. The best kind of barter.”",
                        "4th Ave · 3 days ago"
                    ),
                    Triple(
                        "Marcus Vance",
                        "“Prompt, trustworthy, and returned the sander spotless with fresh sandpaper discs. 10/10 neighbor.”",
                        "Elm St · 1 week ago"
                    ),
                    Triple(
                        "Devon Lee",
                        "“Tuned up his commuter bike in exchange for Japanese shears to trim my front orchard. That's true community economy.”",
                        "Birch St · 2 weeks ago"
                    )
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    testimonials.forEach { (author, quote, time) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.White)
                                .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
                                .padding(14.dp)
                        ) {
                            Column {
                                Text(
                                    text = quote,
                                    fontSize = 12.sp,
                                    color = SlateInk,
                                    lineHeight = 17.sp,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "— $author",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimarySage
                                    )
                                    Text(
                                        text = time,
                                        fontSize = 11.sp,
                                        color = SlateInkVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
