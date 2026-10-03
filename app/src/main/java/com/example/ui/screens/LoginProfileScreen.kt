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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.PresetAvatar
import com.example.ui.NutriPulseViewModel
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun LoginProfileScreen(
    viewModel: NutriPulseViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentProfile by viewModel.userProfile.collectAsState()

    var isSignUpTab by remember { mutableStateOf(true) }
    var nameInput by remember { mutableStateOf(currentProfile.name) }
    var emailInput by remember { mutableStateOf(currentProfile.email) }
    var passwordInput by remember { mutableStateOf("••••••••") }
    var passwordVisible by remember { mutableStateOf(false) }

    var ageInput by remember { mutableStateOf(currentProfile.age.toString()) }
    var heightInput by remember { mutableStateOf(currentProfile.heightCm.toString()) }
    var weightInput by remember { mutableStateOf(currentProfile.weightKg.toString()) }
    var genderInput by remember { mutableStateOf(currentProfile.gender) }

    var selectedCalorieGoal by remember { mutableIntStateOf(currentProfile.calorieGoal) }
    var selectedWaterGoal by remember { mutableIntStateOf(currentProfile.waterGoalMl) }
    var selectedFocus by remember { mutableStateOf(currentProfile.primaryFocus) }

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

    var selectedAvatarUrl by remember { mutableStateOf(currentProfile.avatarUrl) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Back Button
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

                // Demo fill button
                OutlinedButton(
                    onClick = {
                        nameInput = "Maya"
                        emailInput = "maya@nutripulse.ai"
                        ageInput = "23"
                        heightInput = "168"
                        weightInput = "60.5"
                        genderInput = "Female"
                        selectedCalorieGoal = 2100
                        selectedWaterGoal = 2200
                        selectedAvatarUrl = presetAvatars[0].url
                        viewModel.showToast("Demo profile loaded")
                    },
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryEmerald),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(PrimaryEmerald.copy(alpha = 0.4f))
                    )
                ) {
                    Text("Fill Demo Info", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Header Title
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = if (isSignUpTab) "Personalize Your Vault" else "Welcome Back",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary
                )
                Text(
                    text = "Sync your personal biometric baseline, dietary goals, and zero-waste telemetry.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        // Mode Switcher (Sign Up vs Log In)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .background(SurfaceContainer)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(50))
                        .background(if (isSignUpTab) PrimaryEmerald else Color.Transparent)
                        .clickable { isSignUpTab = true }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "New User Profile",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSignUpTab) OnPrimary else TextMuted
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(50))
                        .background(if (!isSignUpTab) PrimaryEmerald else Color.Transparent)
                        .clickable { isSignUpTab = false }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Log In",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (!isSignUpTab) OnPrimary else TextMuted
                    )
                }
            }
        }

        // Avatar Selection Carousel
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "SELECT YOUR AVATAR",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted,
                    letterSpacing = 1.sp
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(presetAvatars) { avatar ->
                        val isSelected = selectedAvatarUrl == avatar.url
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .clickable { selectedAvatarUrl = avatar.url }
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(58.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = if (isSelected) 2.5.dp else 1.dp,
                                        color = if (isSelected) PrimaryEmerald else SurfaceContainerHighest,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
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
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Selected",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }

                            Text(
                                text = avatar.name,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) PrimaryEmerald else TextMuted
                            )
                        }
                    }
                }
            }
        }

        // Credentials & Name Input
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Name Input
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Your Full Name", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_user_name"),
                        placeholder = { Text("e.g. Siddhi Jadhav", color = TextMuted, fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryEmerald,
                            unfocusedBorderColor = SurfaceContainerHigh,
                            focusedContainerColor = SurfaceContainer,
                            unfocusedContainerColor = SurfaceContainer,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }

                // Email Input
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Email Address", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    OutlinedTextField(
                        value = emailInput,
                        onValueChange = { emailInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_user_email"),
                        placeholder = { Text("e.g. siddhi@example.com", color = TextMuted, fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryEmerald,
                            unfocusedBorderColor = SurfaceContainerHigh,
                            focusedContainerColor = SurfaceContainer,
                            unfocusedContainerColor = SurfaceContainer,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }

                // Password / Access Token
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Security Key / Password", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    OutlinedTextField(
                        value = passwordInput,
                        onValueChange = { passwordInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_user_password"),
                        placeholder = { Text("Enter secure password", color = TextMuted, fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password",
                                    tint = TextMuted
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryEmerald,
                            unfocusedBorderColor = SurfaceContainerHigh,
                            focusedContainerColor = SurfaceContainer,
                            unfocusedContainerColor = SurfaceContainer,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }

                // Biometrics: Age, Height, Weight
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "Age (yrs)", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        OutlinedTextField(
                            value = ageInput,
                            onValueChange = { ageInput = it },
                            placeholder = { Text("23", color = TextMuted, fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryEmerald,
                                unfocusedBorderColor = SurfaceContainerHigh,
                                focusedContainerColor = SurfaceContainer,
                                unfocusedContainerColor = SurfaceContainer,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            modifier = Modifier.testTag("input_user_age")
                        )
                    }

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "Height (cm)", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        OutlinedTextField(
                            value = heightInput,
                            onValueChange = { heightInput = it },
                            placeholder = { Text("168", color = TextMuted, fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryEmerald,
                                unfocusedBorderColor = SurfaceContainerHigh,
                                focusedContainerColor = SurfaceContainer,
                                unfocusedContainerColor = SurfaceContainer,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            modifier = Modifier.testTag("input_user_height")
                        )
                    }

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "Weight (kg)", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        OutlinedTextField(
                            value = weightInput,
                            onValueChange = { weightInput = it },
                            placeholder = { Text("60.5", color = TextMuted, fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryEmerald,
                                unfocusedBorderColor = SurfaceContainerHigh,
                                focusedContainerColor = SurfaceContainer,
                                unfocusedContainerColor = SurfaceContainer,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            modifier = Modifier.testTag("input_user_weight")
                        )
                    }
                }

                // Biological Sex
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Biological Sex (For Basal Burn Telemetry)", fontSize = 11.sp, color = TextMuted)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Female", "Male", "Other").forEach { g ->
                            val isSelected = genderInput == g
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
                                    .clickable { genderInput = g }
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
            }
        }

        // Nutrition & Hydration Goals Tuning
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(18.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "TARGET BIOMETRICS & GOALS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryEmerald,
                    letterSpacing = 1.sp
                )

                // Calorie Target Picker
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Daily Calorie Target", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                        Text(text = "$selectedCalorieGoal kcal", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(1800, 2100, 2400).forEach { cal ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (selectedCalorieGoal == cal) SecondaryAmber.copy(alpha = 0.2f) else SurfaceContainerHigh)
                                    .border(
                                        1.dp,
                                        if (selectedCalorieGoal == cal) SecondaryAmber else Color.Transparent,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable { selectedCalorieGoal = cal }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$cal kcal",
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedCalorieGoal == cal) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedCalorieGoal == cal) SecondaryAmber else TextSecondary
                                )
                            }
                        }
                    }
                }

                // Hydration Goal Picker
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Daily Hydration Goal", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                        Text(text = "$selectedWaterGoal ml", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(1800, 2200, 2600).forEach { water ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (selectedWaterGoal == water) PrimaryEmerald.copy(alpha = 0.2f) else SurfaceContainerHigh)
                                    .border(
                                        1.dp,
                                        if (selectedWaterGoal == water) PrimaryEmerald else Color.Transparent,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable { selectedWaterGoal = water }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${water / 1000f} L",
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedWaterGoal == water) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedWaterGoal == water) PrimaryEmerald else TextSecondary
                                )
                            }
                        }
                    }
                }

                // Primary Objective Focus
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Primary Focus Goal", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                    val foci = listOf("Circadian Energy & Zero-Waste", "Lean Muscle & HIIT", "Deep Sleep & Recovery")
                    foci.forEach { focus ->
                        val isSelected = selectedFocus == focus
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) SurfaceContainerHigh else SurfaceContainerLow)
                                .clickable { selectedFocus = focus }
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = focus,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) PrimaryEmerald else TextSecondary
                            )
                            if (isSelected) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }

        // Action Submit Button
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        val parsedAge = ageInput.toIntOrNull() ?: 23
                        val parsedHeight = heightInput.toIntOrNull() ?: 168
                        val parsedWeight = weightInput.toFloatOrNull() ?: 60.5f

                        viewModel.loginOrRegister(
                            name = nameInput,
                            email = emailInput,
                            calorieGoal = selectedCalorieGoal,
                            waterGoalMl = selectedWaterGoal,
                            avatarUrl = selectedAvatarUrl,
                            primaryFocus = selectedFocus,
                            age = parsedAge,
                            heightCm = parsedHeight,
                            weightKg = parsedWeight,
                            gender = genderInput
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("submit_login_profile_button"),
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
                            text = if (isSignUpTab) "Save Profile & Enter NutriPulse" else "Log In & Sync Biometrics",
                            fontSize = 15.sp,
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

                Text(
                    text = "🔒 Your data is processed 100% on-device and stored with zero-knowledge encryption.",
                    fontSize = 10.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
