package com.example.ui.components

import android.content.Intent
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
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.AppScreen
import com.example.model.UserProfile
import com.example.util.AppShareConfig
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.TextPrimary

@Composable
fun NutriPulseTopBar(
    currentScreen: AppScreen,
    userProfile: UserProfile,
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val screenTitle = when (currentScreen) {
        AppScreen.HOME -> "Home"
        AppScreen.MEALS -> "Food Coach"
        AppScreen.ENERGY -> "Energy"
        AppScreen.KITCHEN -> "Kitchen"
        AppScreen.PROFILE -> "My Profile"
        AppScreen.PRIVACY_VAULT -> "Privacy Vault"
        AppScreen.NOTIFICATION_SETTINGS -> "Alerts"
        AppScreen.LOGIN -> "Login / Sign Up"
        AppScreen.WELCOME -> "Welcome"
    }

    fun triggerShareApp() {
        AppShareConfig.shareApp(context, userProfile.name)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ObsidianBase.copy(alpha = 0.95f))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Logo & Screen Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Neon Emblem
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida/AEtjO1W8IYUWKDaC4HWXtpYbGlYFJrtFFc26A39Q0IA8DbJV16Nw9a0YeihZ7rMpIPbnvbBC_STtpZROj9So_m7wh1Rsr5YW_8QfKeDop9B7RtwxFD-O3H0xrub-BiKGvF3QSIO1M1eHpSY7QwjWStlIypvM-UUB1B_0igErJkDZ9Jygb29mvMb2NvvXD0TFbKHRWbEE63VeFewPqXt5g0jDvySA0D8IZMNJdqcG_bqr4xqN-6J4-e3eGr59S9c",
                contentDescription = "NutriPulse Logo",
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = "NUTRIPULSE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryEmerald,
                    letterSpacing = 1.2.sp
                )
                Text(
                    text = screenTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        }

        // Action Buttons: Share, Streak Counter & User Avatar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Share App Icon Button
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerHigh)
                    .clickable { triggerShareApp() }
                    .testTag("topbar_share_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share NutriPulse",
                    tint = PrimaryEmerald,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Streak Pill
            Row(
                modifier = Modifier
                    .background(SurfaceContainerHigh, RoundedCornerShape(50))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = "Active streak",
                    tint = SecondaryAmber,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${userProfile.streakDays}d",
                    color = SecondaryAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // User Avatar with Active Telemetry Dot
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .testTag("avatar_button")
                    .clickable { onAvatarClick() }
            ) {
                AsyncImage(
                    model = userProfile.avatarUrl,
                    contentDescription = "Profile ${userProfile.name}",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, PrimaryEmerald.copy(alpha = 0.5f), CircleShape),
                    contentScale = ContentScale.Crop
                )

                // Neon pulse green indicator dot
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.BottomEnd)
                        .background(PrimaryEmerald, CircleShape)
                        .border(1.5.dp, ObsidianBase, CircleShape)
                )
            }
        }
    }
}
