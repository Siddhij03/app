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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.NutriPulseViewModel
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun NotificationSetupScreen(
    viewModel: NutriPulseViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var predictiveOnly by remember { mutableStateOf(true) }
    var quietHours by remember { mutableStateOf(true) }
    var instantWasteAlerts by remember { mutableStateOf(true) }
    var alertsActivated by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Nav Back
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainer)
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary, modifier = Modifier.size(18.dp))
                }

                TextButton(onClick = { onBack() }) {
                    Text(text = "Skip for now", color = TextSecondary, fontSize = 12.sp)
                }
            }
        }

        // Header & Brand Intro
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida/AEtjO1W8IYUWKDaC4HWXtpYbGlYFJrtFFc26A39Q0IA8DbJV16Nw9a0YeihZ7rMpIPbnvbBC_STtpZROj9So_m7wh1Rsr5YW_8QfKeDop9B7RtwxFD-O3H0xrub-BiKGvF3QSIO1M1eHpSY7QwjWStlIypvM-UUB1B_0igErJkDZ9Jygb29mvMb2NvvXD0TFbKHRWbEE63VeFewPqXt5g0jDvySA0D8IZMNJdqcG_bqr4xqN-6J4-e3eGr59S9c",
                    contentDescription = "Emblem",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.5.dp, PrimaryEmerald.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "ADAPTIVE INTELLIGENCE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryFixedDim,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Stay in Sync with Smart Alerts",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "NutriPulse uses timely notifications to predict energy dips before they happen and rescue food before it expires.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }
        }

        // Android System Prompt Preview Box
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(20.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(SurfaceContainerHigh, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                            }
                            Column {
                                Text(text = "Android System Prompt Preview", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text(text = "Estimated volume: ~2 alerts / day", fontSize = 10.sp, color = TextMuted)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = "Preview", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                        }
                    }

                    // Inner Dialog
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(modifier = Modifier.size(6.dp).background(PrimaryEmerald, CircleShape))
                            Text(text = "NUTRIPULSE ENGINE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextPrimary, letterSpacing = 0.5.sp)
                        }

                        Text(
                            text = "Allow NutriPulse to send you predictive stamina alerts and zero-waste reminders?",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(onClick = { viewModel.showToast("Alerts dismissed") }) {
                                Text(text = "Don't allow", fontSize = 11.sp, color = TextMuted)
                            }
                            Button(
                                onClick = {
                                    alertsActivated = true
                                    viewModel.showToast("Predictive notifications enabled!")
                                },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryEmerald, contentColor = OnPrimary),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp)
                            ) {
                                Text(text = "Allow", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Predictive Capabilities Grid
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "PREDICTIVE CAPABILITIES", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary, letterSpacing = 0.8.sp)
                    Text(text = "ZERO SPOILAGE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim, letterSpacing = 0.8.sp)
                }

                CapabilityCard(
                    icon = Icons.Default.Bolt,
                    iconColor = SecondaryAmber,
                    title = "Circadian Energy Dip Warnings",
                    tag = "Metabolic",
                    desc = "Get notified 30 mins before your 2:15 PM slump with the ideal glucose-stabilizing snack fix."
                )
                CapabilityCard(
                    icon = Icons.Default.Eco,
                    iconColor = PrimaryFixedDim,
                    title = "Zero-Waste Pantry Rescues",
                    tag = "Eco-Save",
                    desc = "Timely reminders when ingredients like spinach or Greek yogurt reach critical 24h expiration."
                )
                CapabilityCard(
                    icon = Icons.Default.WaterDrop,
                    iconColor = PrimaryEmerald,
                    title = "Hydration & Peak Windows",
                    tag = "Focus",
                    desc = "Unlock maximum productivity during your predicted daily peak stamina window (10:00 AM - 12:30 PM)."
                )
                CapabilityCard(
                    icon = Icons.Default.Restaurant,
                    iconColor = SecondaryAmber,
                    title = "AI Chef Rapid Preps",
                    tag = "Zero Effort",
                    desc = "Receive fast 10-minute dinner assembly recipes calibrated to whatever is sitting in your fridge."
                )
            }
        }

        // Lock Screen Previews
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "LOCK SCREEN PREVIEWS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary, letterSpacing = 0.8.sp)
                    Text(text = "Real-world feel", fontSize = 11.sp, color = TextMuted)
                }

                LockScreenNotificationPreview(
                    time = "1:45 PM",
                    title = "Afternoon Energy Dip in 30 mins",
                    body = "Grab your Almond & Matcha snack to maintain stable focus and sidestep a blood sugar crash.",
                    dotColor = SecondaryAmber
                )

                LockScreenNotificationPreview(
                    time = "5:00 PM",
                    title = "2 Ingredients Expire Tonight",
                    body = "Spinach & Greek yogurt are ready to go. Tap to view a 5-minute High-Protein Green Dip recipe.",
                    dotColor = PrimaryEmerald
                )
            }
        }

        // Frequency & Quiet Hours Controls
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Frequency & Quiet Hours", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text(text = "Customizable", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                }

                AlertToggleRow(
                    icon = Icons.Default.Tune,
                    title = "Predictive Engine Only",
                    subtitle = "Recommended · Max 2-3 essential alerts / day",
                    checked = predictiveOnly,
                    onToggle = { predictiveOnly = !predictiveOnly }
                )

                AlertToggleRow(
                    icon = Icons.Default.Bedtime,
                    title = "Quiet Hours (DND)",
                    subtitle = "Auto-silenced during sleep (10:00 PM – 7:00 AM)",
                    checked = quietHours,
                    onToggle = { quietHours = !quietHours }
                )

                AlertToggleRow(
                    icon = Icons.Default.Inventory2,
                    title = "Instant Zero-Waste Alerts",
                    subtitle = "Flash ping when fresh produce hits 24h limit",
                    checked = instantWasteAlerts,
                    onToggle = { instantWasteAlerts = !instantWasteAlerts }
                )
            }
        }

        // Enable Notifications Action
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        alertsActivated = true
                        viewModel.showToast("All adaptive notifications enabled!")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("enable_notifications_button"),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (alertsActivated) PrimaryFixedDim else PrimaryEmerald,
                        contentColor = OnPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (alertsActivated) Icons.Default.CheckCircle else Icons.Default.NotificationsActive,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (alertsActivated) "Alerts Activated!" else "Enable Notifications",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerLow, RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = PrimaryFixedDim, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "100% on-device timing. Zero spam. Revoke anytime in settings.",
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
private fun CapabilityCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    tag: String,
    desc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainer)
            .padding(12.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(SurfaceContainerHigh, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Box(
                    modifier = Modifier
                        .background(SurfaceContainerHigh, RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                ) {
                    Text(text = tag, fontSize = 9.sp, color = TextSecondary)
                }
            }
            Text(text = desc, fontSize = 11.sp, color = TextMuted, lineHeight = 15.sp)
        }
    }
}

@Composable
private fun LockScreenNotificationPreview(
    time: String,
    title: String,
    body: String,
    dotColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(modifier = Modifier.size(6.dp).background(PrimaryEmerald, CircleShape))
                Text(text = "NUTRIPULSE • $time", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextMuted)
            }
            Box(modifier = Modifier.size(6.dp).background(dotColor, CircleShape))
        }
        Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(text = body, fontSize = 11.sp, color = TextSecondary, lineHeight = 15.sp)
    }
}

@Composable
private fun AlertToggleRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(icon, contentDescription = null, tint = TextMuted, modifier = Modifier.size(18.dp))
            Column {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = subtitle, fontSize = 10.sp, color = TextMuted)
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = { onToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = ObsidianBase,
                checkedTrackColor = PrimaryEmerald,
                uncheckedThumbColor = TextMuted,
                uncheckedTrackColor = SurfaceContainerHighest
            )
        )
    }
}
