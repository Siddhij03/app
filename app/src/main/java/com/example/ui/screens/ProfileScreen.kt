package com.example.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.PresetAvatar
import com.example.ui.NutriPulseViewModel
import com.example.util.AppShareConfig
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ProfileScreen(
    viewModel: NutriPulseViewModel,
    onBack: () -> Unit,
    onOpenPrivacyVault: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val profile by viewModel.userProfile.collectAsState()

    var nameState by remember { mutableStateOf(profile.name) }
    var emailState by remember { mutableStateOf(profile.email) }
    var ageState by remember { mutableStateOf(profile.age.toString()) }
    var heightState by remember { mutableStateOf(profile.heightCm.toString()) }
    var weightState by remember { mutableStateOf(profile.weightKg.toString()) }
    var genderState by remember { mutableStateOf(profile.gender) }
    var activityLevelState by remember { mutableStateOf(profile.activityLevel) }
    var calorieGoalState by remember { mutableIntStateOf(profile.calorieGoal) }
    var waterGoalState by remember { mutableIntStateOf(profile.waterGoalMl) }
    var selectedAvatarUrl by remember { mutableStateOf(profile.avatarUrl) }
    var focusState by remember { mutableStateOf(profile.primaryFocus) }

    val presetAvatars = listOf(
        PresetAvatar(
            id = "maya",
            name = "Maya",
            url = "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw"
        ),
        PresetAvatar(
            id = "alex",
            name = "Alex",
            url = "https://lh3.googleusercontent.com/aida/AEtjO1WaB_jZB5obXRZsoAwFVSF3gN8yhqvI_1CR17B0Rpo6kYHwI0sZO9AkGcOX0iiJOmqPrcHwgvzs3oRqRaPz541RkKBY7NxbRTVVaHd9tgv_UelbyeG_7XXtJqTP0lkktuNnFO2Ikqyxm0N4MELXepmLQnynJ2SFM5XUc9-6JwCkK-al9KSawHJ9lj3l5939XxoLUL10W50fqQIaVd2zlNZA-1ItDo2I8q_rBUtx2VTWQJZDfA0WXJJHyiNP"
        ),
        PresetAvatar(
            id = "runner",
            name = "Stamina",
            url = "https://lh3.googleusercontent.com/aida-public/AB6AXuAUxdpuVFWsKEKOYNPvHvxErhlHLF0gA__0-ZWmgeQF9bzEL9I2gT1fiHI8MMSkRLkkIelXWv_X0zIFYTghrERYyl_9n3Ir1abVS5rwRp6m-8zVY8GEcnxYiE2R7l2T8oXhYNOGvc40qc62pFgTGJQ8FJFLZXYkTDs6XsdQ2S1JnpcaFbVVprhm-LXFiypPJnS_RzhoKSF5g-TyAq-2VrYmc9Obomiptpqkw7hrKFnTTEMfvjtg0ieDxg"
        ),
        PresetAvatar(
            id = "pulse_emblem",
            name = "Pulse",
            url = "https://lh3.googleusercontent.com/aida/AEtjO1W8IYUWKDaC4HWXtpYbGlYFJrtFFc26A39Q0IA8DbJV16Nw9a0YeihZ7rMpIPbnvbBC_STtpZROj9So_m7wh1Rsr5YW_8QfKeDop9B7RtwxFD-O3H0xrub-BiKGvF3QSIO1M1eHpSY7QwjWStlIypvM-UUB1B_0igErJkDZ9Jygb29mvMb2NvvXD0TFbKHRWbEE63VeFewPqXt5g0jDvySA0D8IZMNJdqcG_bqr4xqN-6J4-e3eGr59S9c"
        )
    )

    // Calculate BMI
    val parsedHeight = heightState.toFloatOrNull() ?: 168f
    val parsedWeight = weightState.toFloatOrNull() ?: 60.5f
    val parsedAge = ageState.toIntOrNull() ?: 23
    val heightMeters = (parsedHeight / 100f).coerceAtLeast(0.5f)
    val bmi = parsedWeight / (heightMeters * heightMeters)
    val bmiCategory = when {
        bmi < 18.5f -> "Underweight"
        bmi < 25f -> "Normal (Optimal)"
        bmi < 30f -> "Overweight"
        else -> "Obese"
    }

    // Function to share app with others
    fun triggerShareApp() {
        AppShareConfig.shareApp(context, nameState)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Navigation Header
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
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Share App Header Action Button
                Button(
                    onClick = { triggerShareApp() },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryEmerald.copy(alpha = 0.15f),
                        contentColor = PrimaryEmerald
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("share_app_header_button")
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Share App", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Profile Avatar & Badges Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(20.dp))
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .border(2.5.dp, PrimaryEmerald, CircleShape)
                    ) {
                        AsyncImage(
                            model = selectedAvatarUrl,
                            contentDescription = nameState,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = nameState.ifBlank { "User Profile" },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                        Text(
                            text = emailState.ifBlank { "user@nutripulse.ai" },
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(
                                modifier = Modifier
                                    .background(PrimaryEmerald.copy(alpha = 0.15f), RoundedCornerShape(50))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(text = profile.userLevel, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                            }
                            Box(
                                modifier = Modifier
                                    .background(SecondaryAmber.copy(alpha = 0.15f), RoundedCornerShape(50))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(text = "${profile.streakDays}d Streak", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                            }
                        }
                    }
                }
            }
        }

        // Live Health Telemetry Readout (BMI & Basal Rate)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainerHigh)
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "METABOLIC METRICS TELEMETRY",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryEmerald,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Auto-Calculated",
                            fontSize = 10.sp,
                            color = TextMuted
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "BMI Index", fontSize = 11.sp, color = TextMuted)
                            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "%.1f".format(bmi),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = bmiCategory,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryFixedDim
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Est. Basal Burn", fontSize = 11.sp, color = TextMuted)
                            Text(
                                text = "%,d kcal/d".format((10 * parsedWeight + 6.25f * parsedHeight - 5 * parsedAge + 5).toInt()),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryAmber
                            )
                        }
                    }
                }
            }
        }

        // Daily Streak Telemetry & Testing Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, SecondaryAmber.copy(alpha = 0.35f), RoundedCornerShape(18.dp))
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
                                    .size(32.dp)
                                    .background(SecondaryAmber.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocalFireDepartment,
                                    contentDescription = null,
                                    tint = SecondaryAmber,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "DAILY STREAK ENGINE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryAmber,
                                    letterSpacing = 1.sp
                                )
                                Text(
                                    text = "${profile.streakDays} Day Active Streak 🔥",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextPrimary
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .background(SecondaryAmber.copy(alpha = 0.15f), RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "Active Today",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryAmber
                            )
                        }
                    }

                    Text(
                        text = "⚡ Streaks increase daily if and only if you use NutriPulse every day. If you miss a day, the streak resets to 1 day.",
                        fontSize = 11.sp,
                        color = TextMuted,
                        lineHeight = 15.sp
                    )

                    // Streak Simulation & Verification Controls
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.simulateAdvanceDayStreak() },
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("btn_test_next_day"),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = SecondaryAmber),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(SecondaryAmber.copy(alpha = 0.5f))
                            )
                        ) {
                            Icon(Icons.Default.FastForward, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Test +1 Day", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { viewModel.simulateSkipDaysStreak(2) },
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("btn_test_skip_day"),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(SurfaceContainerHighest)
                            )
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Test Missed Day", fontSize = 11.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }

        // Avatar Picker Strip
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "CHANGE AVATAR",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted,
                    letterSpacing = 1.sp
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(presetAvatars) { avatar ->
                        val isSelected = selectedAvatarUrl == avatar.url
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.clickable { selectedAvatarUrl = avatar.url }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = if (isSelected) 2.5.dp else 1.dp,
                                        color = if (isSelected) PrimaryEmerald else SurfaceContainerHighest,
                                        shape = CircleShape
                                    )
                            ) {
                                AsyncImage(
                                    model = avatar.url,
                                    contentDescription = avatar.name,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                if (isSelected) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(PrimaryEmerald.copy(alpha = 0.25f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                            Text(text = avatar.name, fontSize = 11.sp, color = if (isSelected) PrimaryEmerald else TextMuted)
                        }
                    }
                }
            }
        }

        // Personal Details Form (Name, Age, Height, Weight, Gender)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "PERSONAL BIOMETRIC DETAILS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryEmerald,
                    letterSpacing = 1.sp
                )

                // Name
                ProfileInputField(
                    label = "Name",
                    value = nameState,
                    onValueChange = { nameState = it },
                    icon = Icons.Default.Person,
                    testTag = "input_profile_name"
                )

                // Email
                ProfileInputField(
                    label = "Email Address",
                    value = emailState,
                    onValueChange = { emailState = it },
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    testTag = "input_profile_email"
                )

                // Age, Height, Weight in Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Age
                    ProfileCompactField(
                        label = "Age (yrs)",
                        value = ageState,
                        onValueChange = { ageState = it },
                        modifier = Modifier.weight(1f),
                        testTag = "input_profile_age"
                    )

                    // Height
                    ProfileCompactField(
                        label = "Height (cm)",
                        value = heightState,
                        onValueChange = { heightState = it },
                        modifier = Modifier.weight(1f),
                        testTag = "input_profile_height"
                    )

                    // Weight
                    ProfileCompactField(
                        label = "Weight (kg)",
                        value = weightState,
                        onValueChange = { weightState = it },
                        modifier = Modifier.weight(1f),
                        testTag = "input_profile_weight"
                    )
                }

                // Biological Gender
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Biological Sex (For Basal Calculations)", fontSize = 12.sp, color = TextPrimary)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Female", "Male", "Non-Binary").forEach { g ->
                            val isSelected = genderState == g
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isSelected) PrimaryEmerald.copy(alpha = 0.15f) else SurfaceContainerLow)
                                    .border(
                                        1.dp,
                                        if (isSelected) PrimaryEmerald else SurfaceContainerHigh,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable { genderState = g }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = g,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) PrimaryEmerald else TextSecondary
                                )
                            }
                        }
                    }
                }

                // Activity Level
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Physical Activity Level", fontSize = 12.sp, color = TextPrimary)
                    val activities = listOf(
                        "Light (1-2 walks/wk)",
                        "Active (3-5 workouts/wk)",
                        "Intense (Daily athlete training)"
                    )
                    activities.forEach { act ->
                        val isSelected = activityLevelState == act
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) SurfaceContainerHigh else SurfaceContainerLow)
                                .clickable { activityLevelState = act }
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = act,
                                fontSize = 12.sp,
                                color = if (isSelected) PrimaryEmerald else TextSecondary,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                            if (isSelected) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }

        // Daily Targets & Focus
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "NUTRITION & HYDRATION GOALS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryEmerald,
                    letterSpacing = 1.sp
                )

                // Calorie Target
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Daily Calorie Target", fontSize = 12.sp, color = TextPrimary)
                    Text(text = "$calorieGoalState kcal", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(1800, 2100, 2400, 2700).forEach { cal ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (calorieGoalState == cal) SecondaryAmber.copy(alpha = 0.2f) else SurfaceContainerLow)
                                .border(1.dp, if (calorieGoalState == cal) SecondaryAmber else Color.Transparent, RoundedCornerShape(10.dp))
                                .clickable { calorieGoalState = cal }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$cal",
                                fontSize = 12.sp,
                                fontWeight = if (calorieGoalState == cal) FontWeight.Bold else FontWeight.Normal,
                                color = if (calorieGoalState == cal) SecondaryAmber else TextSecondary
                            )
                        }
                    }
                }

                // Hydration Goal
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Daily Water Goal", fontSize = 12.sp, color = TextPrimary)
                    Text(text = "${waterGoalState} ml (${waterGoalState / 1000f}L)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(1800, 2200, 2600, 3000).forEach { ml ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (waterGoalState == ml) PrimaryEmerald.copy(alpha = 0.2f) else SurfaceContainerLow)
                                .border(1.dp, if (waterGoalState == ml) PrimaryEmerald else Color.Transparent, RoundedCornerShape(10.dp))
                                .clickable { waterGoalState = ml }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${ml / 1000f}L",
                                fontSize = 12.sp,
                                fontWeight = if (waterGoalState == ml) FontWeight.Bold else FontWeight.Normal,
                                color = if (waterGoalState == ml) PrimaryEmerald else TextSecondary
                            )
                        }
                    }
                }
            }
        }

        // Save Changes Button
        item {
            Button(
                onClick = {
                    val age = ageState.toIntOrNull() ?: profile.age
                    val height = heightState.toIntOrNull() ?: profile.heightCm
                    val weight = weightState.toFloatOrNull() ?: profile.weightKg

                    viewModel.updateDetailedProfile(
                        name = nameState,
                        email = emailState,
                        age = age,
                        heightCm = height,
                        weightKg = weight,
                        gender = genderState,
                        activityLevel = activityLevelState,
                        calorieGoal = calorieGoalState,
                        waterGoalMl = waterGoalState,
                        avatarUrl = selectedAvatarUrl,
                        primaryFocus = focusState
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("save_profile_button"),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryEmerald,
                    contentColor = OnPrimary
                )
            ) {
                Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Save Profile Changes", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Share App Prominent Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.35f), RoundedCornerShape(18.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                            Icon(Icons.Default.Share, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                        }
                        Column {
                            Text(text = "Share NutriPulse with Friends", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text(text = "Friends can open and use the live app instantly in any browser.", fontSize = 11.sp, color = TextMuted)
                        }
                    }

                    // Live Web App URL Display Box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "LIVE WEB APP URL",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryEmerald,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = AppShareConfig.SHARED_APP_URL,
                                fontSize = 11.sp,
                                color = TextPrimary,
                                maxLines = 1
                            )
                        }
                    }

                    // Share & Copy Actions
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { triggerShareApp() },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("share_app_card_button"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryEmerald,
                                contentColor = OnPrimary
                            )
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Send Link", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                val copied = AppShareConfig.copyLinkToClipboard(context)
                                if (copied) {
                                    viewModel.showToast("App link copied to clipboard! 📋")
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("copy_link_button"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                            border = BorderStroke(1.dp, SurfaceContainerHighest)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Copy Link", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Privacy Vault & Security Shortcut
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceContainer)
                    .clickable { onOpenPrivacyVault() }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryEmerald)
                    Column {
                        Text(text = "Privacy Vault & Data Governance", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(text = "Manage zero-knowledge encryption, export data & telemetry", fontSize = 11.sp, color = TextMuted)
                    }
                }
                Text(text = "Open", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
            }
        }

        // Reset / Log out button
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                OutlinedButton(
                    onClick = { viewModel.logout() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("logout_button"),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted),
                    border = BorderStroke(1.dp, SurfaceContainerHigh)
                ) {
                    Text(text = "Reset Profile & Onboarding Setup", fontSize = 12.sp)
                }
                Text(
                    text = "Clears saved local data so first-time setup reappears on next launch.",
                    fontSize = 10.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
private fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    testTag: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label, fontSize = 12.sp, color = TextPrimary)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag),
            leadingIcon = {
                Icon(icon, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryEmerald,
                unfocusedBorderColor = SurfaceContainerHigh,
                focusedContainerColor = SurfaceContainerLow,
                unfocusedContainerColor = SurfaceContainerLow,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}

@Composable
private fun ProfileCompactField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    testTag: String
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label, fontSize = 11.sp, color = TextPrimary, maxLines = 1)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryEmerald,
                unfocusedBorderColor = SurfaceContainerHigh,
                focusedContainerColor = SurfaceContainerLow,
                unfocusedContainerColor = SurfaceContainerLow,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}
