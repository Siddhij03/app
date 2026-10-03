package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.ChatMessage
import com.example.model.Recipe
import com.example.ui.NutriPulseViewModel
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.PrimaryLight
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun AssistantScreen(
    viewModel: NutriPulseViewModel,
    modifier: Modifier = Modifier
) {
    val messages by viewModel.chatMessages.collectAsState()
    var inputQuery by remember { mutableStateOf("") }
    var isAudioModeActive by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // AI Telemetry Sensor Hub Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainerLow)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                            Icon(Icons.Default.SmartToy, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                        }
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(text = "Pulse AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Box(
                                    modifier = Modifier
                                        .background(SurfaceContainerHighest, RoundedCornerShape(50))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(text = "v2.4", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextMuted)
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Box(modifier = Modifier.size(6.dp).background(PrimaryFixedDim, CircleShape))
                                Text(text = "Biotelemetry Live Sync", fontSize = 10.sp, color = PrimaryFixedDim)
                            }
                        }
                    }

                    // Audio Mode Button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isAudioModeActive) PrimaryEmerald else SurfaceContainerHigh)
                            .clickable {
                                isAudioModeActive = !isAudioModeActive
                                viewModel.showToast(if (isAudioModeActive) "Pulse Audio Voice Mode active" else "Audio Mode disabled")
                            }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.GraphicEq,
                            contentDescription = null,
                            tint = if (isAudioModeActive) OnPrimary else PrimaryFixedDim,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (isAudioModeActive) "Listening..." else "Audio Mode",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAudioModeActive) OnPrimary else TextPrimary
                        )
                    }
                }

                // Active context chips
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        TelemetryContextChip("⚡", "84% Metabolic Stamina", PrimaryEmerald)
                    }
                    item {
                        TelemetryContextChip("⚠️", "2:15 PM Slump Risk", SecondaryAmber)
                    }
                    item {
                        TelemetryContextChip("🥬", "2 Items Expiring", TextMuted)
                    }
                }
            }
        }

        // Chat Message Stream
        items(messages) { message ->
            ChatMessageBubble(
                message = message,
                onApplyFix = { viewModel.fixEnergyBoost() },
                onAddRecipe = { viewModel.showToast("Smoothie added to Daily Plan! +15 XP") }
            )
        }

        // Medical & Lifestyle Disclaimer Notice
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(SurfaceContainerLow)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
                Text(
                    text = "NutriPulse AI offers metabolic & zero-waste lifestyle projections. Consult your physician for medical decisions.",
                    fontSize = 10.sp,
                    color = TextMuted
                )
            }
        }

        // Quick Suggestion Chips
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "SUGGESTED INQUIRIES",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted,
                    letterSpacing = 0.8.sp
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val suggestions = listOf(
                        "🍌 Use expiring bananas",
                        "⚡ Fix 2:15 PM slump",
                        "🏋️ HIIT carb strategy",
                        "🥗 15-min low-GI dinner"
                    )
                    items(suggestions) { sug ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(SurfaceContainer)
                                .clickable {
                                    viewModel.sendUserMessage(sug)
                                }
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(text = sug, fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }

        // Chat Input Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.3f), RoundedCornerShape(50))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.showToast("Receipt Vision AI & Fridge Scanner activated") },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = "Camera", tint = TextMuted, modifier = Modifier.size(20.dp))
                }

                OutlinedTextField(
                    value = inputQuery,
                    onValueChange = { inputQuery = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask Pulse AI anything (food, energy)...", fontSize = 12.sp, color = TextMuted) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )

                IconButton(
                    onClick = { viewModel.showToast("Voice microphone activated") },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(Icons.Default.Mic, contentDescription = "Mic", tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(PrimaryEmerald)
                        .clickable {
                            if (inputQuery.isNotBlank()) {
                                viewModel.sendUserMessage(inputQuery)
                                inputQuery = ""
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.ArrowUpward, contentDescription = "Send", tint = OnPrimary, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
private fun TelemetryContextChip(icon: String, text: String, color: Color) {
    Row(
        modifier = Modifier
            .background(color.copy(alpha = 0.12f), RoundedCornerShape(50))
            .border(1.dp, color.copy(alpha = 0.3f), RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = icon, fontSize = 11.sp)
        Text(text = text, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
private fun ChatMessageBubble(
    message: ChatMessage,
    onApplyFix: () -> Unit,
    onAddRecipe: () -> Unit
) {
    if (message.isUser) {
        // User Message on the right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp, 4.dp, 16.dp, 16.dp))
                        .background(PrimaryEmerald)
                        .padding(14.dp)
                ) {
                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnPrimary
                    )
                }
                Text(text = message.time, fontSize = 10.sp, color = TextMuted)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(PrimaryEmerald.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(16.dp))
            }
        }
    } else {
        // AI Message on the left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(SurfaceContainerHigh, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.SmartToy, contentDescription = null, tint = PrimaryFixedDim, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(0.92f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Text bubble
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp))
                        .background(SurfaceContainer)
                        .padding(14.dp)
                ) {
                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        lineHeight = 18.sp
                    )
                }

                // If message has Rich Recipe Card
                if (message.recipeCard != null) {
                    val recipe = message.recipeCard
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainer)
                            .padding(14.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(PrimaryEmerald.copy(alpha = 0.15f), RoundedCornerShape(50))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(text = "PRE-WORKOUT FUEL & ZERO-WASTE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Eco, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(text = "Zero-Waste Match", fontSize = 10.sp, color = SecondaryAmber)
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                AsyncImage(
                                    model = recipe.imageUrl,
                                    contentDescription = recipe.title,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column {
                                    Text(text = recipe.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                    Text(text = "Ready in 4 min • 340 kcal • 22g Protein", fontSize = 11.sp, color = TextMuted)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(text = "+3.5h Stamina", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                                        Text(text = "•", fontSize = 10.sp, color = TextMuted)
                                        Text(text = "Uses 2 Bananas", fontSize = 11.sp, color = SecondaryAmber)
                                    }
                                }
                            }

                            // Bullet benefits
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SurfaceContainerLow)
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Icon(Icons.Default.Verified, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(15.dp))
                                    Text(
                                        text = "Sustained Stamina: Fast-acting glucose from ripe banana pairs with slow-burning oats for peak 6:00 PM HIIT block.",
                                        fontSize = 11.sp,
                                        color = TextPrimary
                                    )
                                }
                                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Icon(Icons.Default.DeleteSweep, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(15.dp))
                                    Text(
                                        text = "Kitchen Win: Rescues ripe bananas scheduled to spoil at 8:00 PM tonight.",
                                        fontSize = 11.sp,
                                        color = TextPrimary
                                    )
                                }
                            }

                            // Actions
                            Button(
                                onClick = onAddRecipe,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryEmerald,
                                    contentColor = OnPrimary
                                )
                            ) {
                                Icon(Icons.Default.AddTask, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "Add to Daily Energy Plan (+15 XP)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // If message has Diagnostic Trajectory Graph
                if (message.showDiagnosticGraph) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainer)
                            .padding(14.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "METABOLIC TRAJECTORY FORECAST", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextMuted)
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Box(modifier = Modifier.size(6.dp).background(SecondaryAmber, CircleShape))
                                    Text(text = "-32% Dip", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                                }
                            }

                            // Canvas Graph
                            Canvas(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                            ) {
                                val w = size.width
                                val h = size.height

                                // Dotted grid lines
                                drawLine(
                                    color = SurfaceContainerHighest,
                                    start = Offset(0f, h * 0.3f),
                                    end = Offset(w, h * 0.3f),
                                    strokeWidth = 1.dp.toPx()
                                )

                                // Unmitigated dip curve (Amber)
                                val unmitigated = Path().apply {
                                    moveTo(0f, h * 0.35f)
                                    cubicTo(w * 0.25f, h * 0.3f, w * 0.4f, h * 0.45f, w * 0.5f, h * 0.9f)
                                    cubicTo(w * 0.6f, h * 0.95f, w * 0.75f, h * 0.5f, w, h * 0.4f)
                                }
                                drawPath(
                                    path = unmitigated,
                                    color = SecondaryAmber,
                                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                                )

                                // Mitigated lift curve (Dashed Green)
                                val mitigated = Path().apply {
                                    moveTo(0f, h * 0.35f)
                                    cubicTo(w * 0.25f, h * 0.32f, w * 0.4f, h * 0.38f, w * 0.5f, h * 0.48f)
                                    cubicTo(w * 0.6f, h * 0.45f, w * 0.75f, h * 0.4f, w, h * 0.35f)
                                }
                                drawPath(
                                    path = mitigated,
                                    color = PrimaryEmerald,
                                    style = Stroke(
                                        width = 2.5.dp.toPx(),
                                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f))
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("12:30 PM", fontSize = 10.sp, color = TextMuted)
                                Text("2:15 PM (Slump)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                                Text("4:00 PM", fontSize = 10.sp, color = TextMuted)
                                Text("6:00 PM (Workout)", fontSize = 10.sp, color = TextMuted)
                            }

                            // Tactical Fix Pill
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SurfaceContainerHigh)
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(Icons.Default.WaterDrop, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                                    Column {
                                        Text(text = "350ml Cold Water + 8 Almonds", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                        Text(text = "Flattens afternoon glycemic spike by 65%", fontSize = 10.sp, color = TextMuted)
                                    }
                                }

                                Button(
                                    onClick = onApplyFix,
                                    modifier = Modifier.height(34.dp),
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = PrimaryEmerald.copy(alpha = 0.2f),
                                        contentColor = PrimaryEmerald
                                    ),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text(text = "Apply Fix", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Text(text = "${message.time} • Biometric sync verified", fontSize = 10.sp, color = TextMuted)
            }
        }
    }
}
