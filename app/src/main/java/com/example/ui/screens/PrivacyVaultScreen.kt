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
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.TableView
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.NutriPulseViewModel
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun PrivacyVaultScreen(
    viewModel: NutriPulseViewModel,
    onBack: () -> Unit,
    onOpenNotificationSetup: () -> Unit,
    modifier: Modifier = Modifier
) {
    val privacySettings by viewModel.privacySettings.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Navigation Header
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { onBack() }
                            .padding(vertical = 4.dp, horizontal = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextMuted, modifier = Modifier.size(16.dp))
                        Text(text = "Account & Security", fontSize = 12.sp, color = TextMuted)
                    }

                    Row(
                        modifier = Modifier
                            .background(PrimaryEmerald.copy(alpha = 0.12f), RoundedCornerShape(50))
                            .border(1.dp, PrimaryEmerald.copy(alpha = 0.3f), RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(13.dp))
                        Text(text = "Zero-Knowledge Vault", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                    }
                }

                Text(
                    text = "Privacy & Data Governance",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary
                )
                Text(
                    text = "You own your biometric identity. NutriPulse enforces strict on-device data processing, zero third-party broker sales, and instantaneous record deletion.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 16.sp
                )
            }
        }

        // Data Sovereignty Pledge Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(PrimaryEmerald.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Shield, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                            }
                            Text(text = "Data Sovereignty Pledge", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }

                        Box(
                            modifier = Modifier
                                .background(PrimaryEmerald.copy(alpha = 0.2f), RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(text = "100% PRIVATE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                        }
                    }

                    Text(
                        text = "Your daily energy curves, food logs, and fridge inventory are isolated in your encrypted storage vault. Never shared with insurance firms or commercial advertisers.",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )

                    HorizontalDivider(color = SurfaceContainerHigh)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PledgeBadge("AES-256 Cloud")
                        PledgeBadge("On-Device AI")
                        PledgeBadge("GDPR / HIPAA Ready")
                    }
                }
            }
        }

        // Granular Permissions
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "DATA PERMISSIONS & TELEMETRY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextMuted, letterSpacing = 0.8.sp)
                    Text(
                        text = "Reset Defaults",
                        fontSize = 11.sp,
                        color = PrimaryEmerald,
                        modifier = Modifier.clickable { viewModel.showToast("Defaults restored") }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainer)
                ) {
                    PermissionToggleRow(
                        icon = Icons.Default.Favorite,
                        iconColor = PrimaryEmerald,
                        title = "Health Connect & Biometrics",
                        desc = "Syncs step counts, resting heart rate, and sleep intervals for metabolic stamina curves.",
                        checked = privacySettings.healthConnect,
                        onCheckedChange = { viewModel.togglePrivacySetting("health") }
                    )
                    HorizontalDivider(color = SurfaceContainerHigh)
                    PermissionToggleRow(
                        icon = Icons.Default.Bolt,
                        iconColor = SecondaryAmber,
                        title = "Circadian Energy Forecasting",
                        desc = "Processes meal timings and past slumps to calculate your 2:15 PM dip risk curve locally.",
                        checked = privacySettings.circadianForecasting,
                        onCheckedChange = { viewModel.togglePrivacySetting("circadian") }
                    )
                    HorizontalDivider(color = SurfaceContainerHigh)
                    PermissionToggleRow(
                        icon = Icons.Default.SmartToy,
                        iconColor = PrimaryEmerald,
                        title = "Pulse AI Conversational Memory",
                        desc = "Stores ingredients and past workout fuel preferences. Purged automatically every 30 days.",
                        checked = privacySettings.pulseAiMemory,
                        onCheckedChange = { viewModel.togglePrivacySetting("ai") }
                    )
                    HorizontalDivider(color = SurfaceContainerHigh)
                    PermissionToggleRow(
                        icon = Icons.Default.PhotoCamera,
                        iconColor = TextPrimary,
                        title = "On-Device Pantry OCR Scanner",
                        desc = "Extracts grocery receipt items on your phone. Original photo files are purged immediately.",
                        checked = privacySettings.pantryOcrScanner,
                        onCheckedChange = { viewModel.togglePrivacySetting("ocr") }
                    )
                    HorizontalDivider(color = SurfaceContainerHigh)
                    PermissionToggleRow(
                        icon = Icons.Default.Analytics,
                        iconColor = TextMuted,
                        title = "Anonymous Crash Telemetry",
                        desc = "Help engineering improve app stability. Stripped of all user identification before sending.",
                        checked = privacySettings.anonymousTelemetry,
                        onCheckedChange = { viewModel.togglePrivacySetting("telemetry") }
                    )
                }
            }
        }

        // Notification Previews Shortcut Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceContainer)
                    .clickable { onOpenNotificationSetup() }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = PrimaryEmerald)
                    Column {
                        Text(text = "Adaptive Intelligence & Alerts", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(text = "View Lock Screen alert simulations & quiet hours", fontSize = 11.sp, color = TextMuted)
                    }
                }
                Text(text = "Configure", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
            }
        }

        // Data Portability & Export
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "DATA PORTABILITY & EXPORT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextMuted, letterSpacing = 0.8.sp)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainer)
                        .padding(14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = "Full Health & Kitchen Archive", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text(text = "Includes meals, hydration logs, sleep trends, and XP streak history.", fontSize = 11.sp, color = TextMuted)
                            }
                            Box(
                                modifier = Modifier
                                    .background(PrimaryEmerald.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(text = "3.4 MB", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { viewModel.showToast("Exporting JSON archive to device...") },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = TextPrimary)
                            ) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "JSON Archive", fontSize = 11.sp)
                            }

                            Button(
                                onClick = { viewModel.showToast("Exporting CSV sheet to device...") },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = TextPrimary)
                            ) {
                                Icon(Icons.Default.TableView, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "CSV Sheet", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }

        // Regulatory clarification
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainer.copy(alpha = 0.6f))
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(18.dp))
                Text(
                    text = "Medical & Diagnostic Clarification: NutriPulse energy forecasts and sleep calculations are algorithmic estimates designed for lifestyle optimization, not medical diagnostics. Always consult a licensed physician for healthcare concerns.",
                    fontSize = 11.sp,
                    color = TextSecondary,
                    lineHeight = 15.sp
                )
            }
        }

        // Danger Zone: Reset & Account Purge
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "DATA ERASURE & ACCOUNT PURGE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ErrorRed, letterSpacing = 0.8.sp)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainer)
                        .border(1.dp, ErrorRed.copy(alpha = 0.25f), RoundedCornerShape(16.dp))
                        .padding(14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Clear 30-Day Biometrics Log", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text(text = "Wipe meal entries, energy slumps, and water logs while keeping pantry.", fontSize = 11.sp, color = TextMuted)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { viewModel.clearBiometricLogs() },
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = TextPrimary),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(text = "Clear Logs", fontSize = 11.sp)
                            }
                        }

                        HorizontalDivider(color = SurfaceContainerHigh)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Permanently Delete Account", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                                Text(text = "Purges all biometric records, pantry data, and credentials within 24 hours.", fontSize = 11.sp, color = TextMuted)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { viewModel.showToast("Account deletion requested. Token revoked.") },
                                colors = ButtonDefaults.buttonColors(containerColor = ErrorContainer, contentColor = ErrorRed),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(text = "Delete All", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Footer links
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Privacy Policy", fontSize = 11.sp, color = TextMuted)
                Text(text = "  •  ", fontSize = 11.sp, color = TextMuted)
                Text(text = "Terms of Service", fontSize = 11.sp, color = TextMuted)
                Text(text = "  •  ", fontSize = 11.sp, color = TextMuted)
                Text(text = "Health Connect Disclosure", fontSize = 11.sp, color = TextMuted)
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
private fun PledgeBadge(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(13.dp))
        Text(text = text, fontSize = 10.sp, color = TextSecondary)
    }
}

@Composable
private fun PermissionToggleRow(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    desc: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(SurfaceContainerHigh, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
            }
            Column {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = desc, fontSize = 11.sp, color = TextMuted, lineHeight = 14.sp)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = ObsidianBase,
                checkedTrackColor = PrimaryEmerald,
                uncheckedThumbColor = TextMuted,
                uncheckedTrackColor = SurfaceContainerHighest
            )
        )
    }
}
