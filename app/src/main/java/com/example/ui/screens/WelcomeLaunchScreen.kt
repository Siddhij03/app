package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.ObsidianLowest
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun WelcomeLaunchScreen(
    onOpenLogin: () -> Unit,
    onLaunchDemo: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBase)
    ) {
        // High-res glowing energy vortex backdrop
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAV2dyqRi5NO3vwNTwA4O407EjU25N_2Fg21T9y2lQlHOb9D2BKs8tXVExtFkyYrdEDR4S8lboH_Nig_Y28sNhxEipfN2kkRpD6RuJngTTKkPrwr5yJFvqXDq4RVOGSbLFiTqpQikyLH0_EvxFHQFFDWBZG074JpR3GPh1Zune9lAx-ZGwXldYbtyQpIW6lVwJsMmKoaC_AhpVcUWFphDMcyyv3-ttJ9VROBUZgw774F1pusCc7a0qU4w",
            contentDescription = "Futuristic Vortex Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        // Gradient dark overlay for ultra crisp contrast
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ObsidianLowest.copy(alpha = 0.85f),
                            ObsidianBase.copy(alpha = 0.6f),
                            ObsidianLowest.copy(alpha = 0.98f)
                        )
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Status Bar Spacer
            Spacer(modifier = Modifier.height(10.dp))

            // Center Branding & Hero
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Concentric Halo Ring with Logo
                Box(
                    modifier = Modifier.size(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer subtle glowing rings
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .border(1.dp, PrimaryEmerald.copy(alpha = 0.15f), CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .size(112.dp)
                            .border(1.dp, PrimaryEmerald.copy(alpha = 0.3f), CircleShape)
                    )

                    // Emblem
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida/AEtjO1W8IYUWKDaC4HWXtpYbGlYFJrtFFc26A39Q0IA8DbJV16Nw9a0YeihZ7rMpIPbnvbBC_STtpZROj9So_m7wh1Rsr5YW_8QfKeDop9B7RtwxFD-O3H0xrub-BiKGvF3QSIO1M1eHpSY7QwjWStlIypvM-UUB1B_0igErJkDZ9Jygb29mvMb2NvvXD0TFbKHRWbEE63VeFewPqXt5g0jDvySA0D8IZMNJdqcG_bqr4xqN-6J4-e3eGr59S9c",
                        contentDescription = "NutriPulse Emblem",
                        modifier = Modifier
                            .size(92.dp)
                            .shadow(24.dp, CircleShape, spotColor = PrimaryEmerald)
                            .clip(CircleShape)
                            .border(2.dp, PrimaryEmerald.copy(alpha = 0.7f), CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // App Wordmark
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Nutri",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Pulse",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryEmerald
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(PrimaryEmerald, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Circadian Status Pill
                Row(
                    modifier = Modifier
                        .background(PrimaryEmerald.copy(alpha = 0.12f), RoundedCornerShape(50))
                        .border(1.dp, PrimaryEmerald.copy(alpha = 0.35f), RoundedCornerShape(50))
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(PrimaryEmerald, CircleShape)
                    )
                    Text(
                        text = "SYSTEM ACTIVE • CIRCADIAN ENGINE ONLINE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryEmerald,
                        letterSpacing = 0.8.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Predict your energy. Eat smarter. Waste less.",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Feature Pill Badges
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureBadge("⚡", "Circadian Forecast")
                    FeatureBadge("🥗", "Zero-Waste AI")
                    FeatureBadge("🧬", "Biometric Sync")
                }
            }

            // Bottom CTA Cluster
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Interactive Demo Mode Preview Card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(SurfaceContainer.copy(alpha = 0.85f))
                        .border(1.dp, PrimaryEmerald.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                        .clickable { onLaunchDemo() }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(PrimaryEmerald.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = null,
                                tint = PrimaryEmerald,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Explore Maya's Live Day",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "(Demo Mode)",
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )
                            }
                            Text(
                                text = "Preview instantaneous circadian nutrition feed",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }

                    // Stamina Badge
                    Box(
                        modifier = Modifier
                            .background(PrimaryEmerald.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                            .border(1.dp, PrimaryEmerald.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "84%\nStamina",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryEmerald,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Primary Launch Button
                Button(
                    onClick = onOpenLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("launch_experience_button"),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryEmerald,
                        contentColor = OnPrimary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Log In / Set Up Profile",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Secondary Sign In Options
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onOpenLogin,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("google_signin_button"),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceContainerHigh.copy(alpha = 0.6f),
                            contentColor = TextPrimary
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.linearGradient(listOf(SurfaceContainerHigh, SurfaceContainerHigh))
                        )
                    ) {
                        Text(text = "Google Sign In", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    OutlinedButton(
                        onClick = onOpenLogin,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("email_access_button"),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceContainerHigh.copy(alpha = 0.6f),
                            contentColor = TextPrimary
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.linearGradient(listOf(SurfaceContainerHigh, SurfaceContainerHigh))
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = TextMuted
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Email Access", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                // Footer note
                Text(
                    text = "Powered by Pulse AI v2.4 • Circadian & Metabolic Telemetry.\nEstimates for wellness optimization, not clinical diagnosis.",
                    fontSize = 10.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
private fun FeatureBadge(emoji: String, title: String) {
    Row(
        modifier = Modifier
            .background(SurfaceContainerHigh.copy(alpha = 0.7f), RoundedCornerShape(50))
            .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = emoji, fontSize = 11.sp)
        Text(text = title, fontSize = 11.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
    }
}
