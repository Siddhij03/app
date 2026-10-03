package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.AppScreen
import com.example.model.DailyQuest
import com.example.ui.NutriPulseViewModel
import com.example.util.AppShareConfig
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SecondaryAmber
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceVariant
import com.example.ui.theme.TertiaryPurple
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    viewModel: NutriPulseViewModel,
    onNavigateToKitchen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val energyPct by viewModel.energyPercentage.collectAsState()
    val isCurveLifted by viewModel.isCurveLifted.collectAsState()
    val waterMl by viewModel.currentWaterMl.collectAsState()
    val quests by viewModel.quests.collectAsState()
    val currentXp by viewModel.currentXp.collectAsState()
    val healthIndex by viewModel.healthIndex.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val context = LocalContext.current

    fun triggerShareApp() {
        AppShareConfig.shareApp(context, userProfile.name)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Interactive Demo Banner
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceContainerHigh)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = PrimaryEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Interactive Demo Active — Tap cards, tasks & loggers",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        maxLines = 1
                    )
                }

                Box(
                    modifier = Modifier
                        .background(SurfaceContainer, RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Live Mock",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                }
            }
        }

        // Welcome Greeting
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Good morning, ${userProfile.name} 👋",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary
                    )

                    Row(
                        modifier = Modifier
                            .background(SecondaryContainer, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MilitaryTech,
                            contentDescription = null,
                            tint = Color(0xFF2C1700),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "LVL 3",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C1700)
                        )
                    }
                }
                Text(
                    text = "Here's your personal energy forecast for today.",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // Hero Energy Forecast Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, SurfaceContainerHighest, RoundedCornerShape(24.dp))
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // Upper Row: Bio-Telemetry & Kinetic Dial
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(7.dp)
                                        .background(PrimaryEmerald, CircleShape)
                                )
                                Text(
                                    text = "BIO-TELEMETRY FEED",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryFixedDim,
                                    letterSpacing = 0.8.sp
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "$energyPct%",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = if (isCurveLifted) "Protected ⚡" else "Strong Start",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryEmerald
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                                    contentDescription = null,
                                    tint = SecondaryAmber,
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = "Peak cellular efficiency detected",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        // Radial Kinetic Energy Dial
                        Box(
                            modifier = Modifier.size(92.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val animatedProgress by animateFloatAsState(
                                targetValue = energyPct / 100f,
                                label = "energyDial"
                            )

                            Canvas(modifier = Modifier.fillMaxSize()) {
                                // Background circle
                                drawCircle(
                                    color = SurfaceVariant.copy(alpha = 0.6f),
                                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                                )
                                // Active progress arc
                                drawArc(
                                    color = PrimaryEmerald,
                                    startAngle = -90f,
                                    sweepAngle = 360f * animatedProgress,
                                    useCenter = false,
                                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.AutoFixHigh,
                                    contentDescription = null,
                                    tint = PrimaryEmerald,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "OPTIMAL",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryEmerald,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }

                    // Alert: Predicted Energy Dip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(SecondaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timelapse,
                                contentDescription = null,
                                tint = Color(0xFF2C1700),
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Predicted Energy Dip",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Box(
                                    modifier = Modifier
                                        .background(SecondaryAmber.copy(alpha = 0.2f), RoundedCornerShape(50))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (isCurveLifted) "Prevented" else "Moderate",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCurveLifted) PrimaryEmerald else SecondaryAmber
                                    )
                                }
                            }
                            Text(
                                text = "Expected around 1:45 PM based on circadian cycle",
                                fontSize = 11.sp,
                                color = TextSecondary,
                                maxLines = 1
                            )
                        }
                    }

                    // Immediate Smart Recommendation Widget
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainer)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.TipsAndUpdates,
                                contentDescription = null,
                                tint = SecondaryAmber,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "QUICK PRE-EMPTIVE FIX",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryAmber,
                                letterSpacing = 0.8.sp
                            )
                        }
                        Text(
                            text = "🥜 Fuel with Banana + peanut butter and hydrate with 300ml water before 1:30 PM.",
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 16.sp
                        )
                    }

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { viewModel.fixEnergyBoost() },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("btn_fix_energy"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryEmerald,
                                contentColor = OnPrimary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoFixHigh,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Fix My Energy", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { viewModel.logQuickBoost() },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("btn_log_boost"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SurfaceContainer,
                                contentColor = SecondaryAmber
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Log Boost", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Daily System Health Score Widget
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SYSTEM HEALTH INDEX",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextMuted,
                            letterSpacing = 0.8.sp
                        )
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "$healthIndex",
                                fontSize = 34.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "/ 100",
                                fontSize = 16.sp,
                                color = TextMuted
                            )
                            Row(
                                modifier = Modifier
                                    .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowUpward,
                                    contentDescription = null,
                                    tint = PrimaryFixedDim,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "+4 pts",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryFixedDim
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(SurfaceContainerHigh, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MonitorHeart,
                            contentDescription = null,
                            tint = PrimaryEmerald,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }

                // Breakdown Pills
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        HealthScorePill(Icons.Default.Restaurant, "Nutrition", "85", PrimaryEmerald)
                    }
                    item {
                        HealthScorePill(Icons.Default.WaterDrop, "Hydration", "${(waterMl * 100 / 2200).coerceAtMost(100)}", SecondaryAmber)
                    }
                    item {
                        HealthScorePill(Icons.Default.Bedtime, "Sleep", "82", TertiaryPurple)
                    }
                    item {
                        HealthScorePill(Icons.Default.DirectionsRun, "Activity", "74", SecondaryAmber)
                    }
                }
            }
        }

        // Today's Biometrics Header & Horizontal Scroll Stream
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Today's Biometrics",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Live Syncing",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Card 1: Hydration Tracker
                    item {
                        Box(
                            modifier = Modifier
                                .width(250.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(SurfaceContainer)
                                .padding(16.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(SurfaceContainerHigh, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.WaterDrop,
                                                contentDescription = null,
                                                tint = PrimaryFixedDim,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Text(text = "Hydration", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                    }
                                    Text(text = "Goal: 2.2L", fontSize = 11.sp, color = TextMuted)
                                }

                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        verticalAlignment = Alignment.Bottom,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "%,d".format(waterMl),
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = TextPrimary
                                        )
                                        Text(text = "/ 2,200 ml", fontSize = 12.sp, color = TextMuted)
                                    }

                                    LinearProgressIndicator(
                                        progress = { (waterMl.toFloat() / 2200f).coerceIn(0f, 1f) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(7.dp)
                                            .clip(RoundedCornerShape(50)),
                                        color = PrimaryFixedDim,
                                        trackColor = SurfaceContainerHighest
                                    )
                                }

                                Button(
                                    onClick = { viewModel.logWaterGlass() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(38.dp)
                                        .testTag("btn_log_water"),
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = SurfaceContainerHigh,
                                        contentColor = PrimaryFixedDim
                                    )
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "+250ml Glass", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    // Card 2: Caloric & Macro Density
                    item {
                        Box(
                            modifier = Modifier
                                .width(280.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(SurfaceContainer)
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
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(SurfaceContainerHigh, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.LocalDining,
                                                contentDescription = null,
                                                tint = SecondaryAmber,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Text(text = "Nutrition Engine", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                    }
                                    Text(text = "1,650 / 2,100 kcal", fontSize = 11.sp, color = TextMuted)
                                }

                                MacroBar("Protein", "85g / 120g", 0.7f, PrimaryEmerald)
                                MacroBar("Carbs", "190g / 240g", 0.79f, SecondaryAmber)
                                MacroBar("Healthy Fats", "48g / 65g", 0.73f, TertiaryPurple)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Remaining: 450 kcal", fontSize = 11.sp, color = TextMuted)
                                    Text(text = "On Target", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                                }
                            }
                        }
                    }

                    // Card 3: Sleep Rest
                    item {
                        Box(
                            modifier = Modifier
                                .width(240.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(SurfaceContainer)
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
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(SurfaceContainerHigh, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Bedtime,
                                                contentDescription = null,
                                                tint = TertiaryPurple,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Text(text = "Sleep Rest", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                    }
                                    Text(text = "84% deep", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                                }

                                Column {
                                    Text(text = "7h 45m", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                                    Text(text = "REM: 1h 52m • Optimal recovery", fontSize = 11.sp, color = TextMuted)
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(14.dp))
                                    Text(text = "Awoke during light phase", fontSize = 11.sp, color = PrimaryEmerald)
                                }
                            }
                        }
                    }

                    // Card 4: Daily Steps
                    item {
                        Box(
                            modifier = Modifier
                                .width(240.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(SurfaceContainer)
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
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(SurfaceContainerHigh, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.DirectionsWalk,
                                                contentDescription = null,
                                                tint = SecondaryAmber,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Text(text = "Activity", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                    }
                                    Text(text = "75%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                                }

                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text(text = "6,420", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                                    LinearProgressIndicator(
                                        progress = { 0.75f },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(6.dp)
                                            .clip(RoundedCornerShape(50)),
                                        color = SecondaryAmber,
                                        trackColor = SurfaceContainerHighest
                                    )
                                }

                                Text(text = "2,080 steps to 8.5k goal", fontSize = 11.sp, color = TextMuted)
                            }
                        }
                    }
                }
            }
        }

        // Gamified Daily Quests
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header with XP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.Stars, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(22.dp))
                        Text(text = "Daily Quests", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }

                    Box(
                        modifier = Modifier
                            .background(SurfaceContainerHigh, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "$currentXp / 600 XP",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryEmerald
                        )
                    }
                }

                // Level Progress Bar
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    LinearProgressIndicator(
                        progress = { (currentXp.toFloat() / 600f).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                            .clip(RoundedCornerShape(50)),
                        color = PrimaryEmerald,
                        trackColor = SurfaceContainerHighest
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Level 3: Energy Pro", fontSize = 11.sp, color = TextSecondary)
                        Text(text = "${600 - currentXp} XP to Level 4", fontSize = 11.sp, color = TextSecondary)
                    }
                }

                // Quest items list
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    quests.forEach { quest ->
                        QuestItemRow(
                            quest = quest,
                            onToggle = { viewModel.toggleQuest(quest.id) }
                        )
                    }
                }
            }
        }

        // Save My Food Alert Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
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
                                    .background(SecondaryContainer, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Inventory2,
                                    contentDescription = null,
                                    tint = Color(0xFF2C1700),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column {
                                Text(text = "Save My Food Alert", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text(text = "🥬 2 ingredients need attention soon!", fontSize = 11.sp, color = SecondaryAmber, fontWeight = FontWeight.SemiBold)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(SecondaryAmber, CircleShape)
                        )
                    }

                    // Ingredient pills
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier
                                .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Alarm, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(14.dp))
                            Text(text = "Bananas", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text(text = "(Use today!)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                        }

                        Row(
                            modifier = Modifier
                                .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = TextMuted, modifier = Modifier.size(14.dp))
                            Text(text = "Baby Spinach", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text(text = "(In 2 days)", fontSize = 11.sp, color = TextMuted)
                        }
                    }

                    // AI Suggestion Box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Default.SmartToy, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                            Text(
                                text = "AI Suggestion: Power Green Smoothie Bowl",
                                fontSize = 12.sp,
                                color = TextPrimary
                            )
                        }

                        Row(
                            modifier = Modifier
                                .clickable { onNavigateToKitchen() }
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(text = "Cook with AI", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }
        }

        // Curated Power Snack Hero Photo Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainerHigh)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBKFguKFjREHCVlNL2ce9RoUtPs8kUS97VXNumhM69JjfpF3ZmJIJHdegVamPF6jgrRKUlqQMYseAPxwn0I8jgq8wY1f2zCa_zJNQqkE3zzDKP8zlkZU9cD0NESJzm7LX9jVNhOksLIDJBIZKmOysUN6zzcU8NM3Y8b5UAgXIIMyU2o6dHCrdgU8THUHm_eZZNdh-SWVo8Wck6yWq1-K4vE0DEykYhNdRhUez70BL2Dy2NyNsz3vjlQtA",
                    contentDescription = "Spinach Peanut Power Snack",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ObsidianBase.copy(alpha = 0.5f),
                                    ObsidianBase.copy(alpha = 0.95f)
                                )
                            )
                        )
                )

                // Details
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = "CURATED POWER SNACK",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryEmerald,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Spinach Peanut Pre-Boost",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(ObsidianBase.copy(alpha = 0.8f), RoundedCornerShape(50))
                            .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "240 kcal • 14g Pro",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                }
            }
        }

        // Share NutriPulse with Friends & Family Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                                    .size(38.dp)
                                    .background(PrimaryEmerald.copy(alpha = 0.15f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = null,
                                    tint = PrimaryEmerald,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "Share NutriPulse with Friends",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "Friends can use the live web app in their browser",
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .background(PrimaryEmerald.copy(alpha = 0.15f), RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Live Link",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryEmerald
                            )
                        }
                    }

                    // Live Web App URL Display Box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "INSTANT ACCESS URL",
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { triggerShareApp() },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("home_share_app_button"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryEmerald,
                                contentColor = OnPrimary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Send Link",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
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
                                .testTag("home_copy_link_button"),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                            border = BorderStroke(1.dp, SurfaceContainerHighest)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Copy Link",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Disclaimer
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "⚡ Forecasts are AI estimates based on your patterns, not medical advice.",
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
            Spacer(modifier = Modifier.height(72.dp)) // padding for bottom nav
        }
    }
}

@Composable
private fun HealthScorePill(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, score: String, color: Color) {
    Row(
        modifier = Modifier
            .background(SurfaceContainerHigh, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(15.dp))
        Text(text = label, fontSize = 11.sp, color = TextPrimary)
        Text(text = score, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
private fun MacroBar(label: String, amount: String, progress: Float, color: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 11.sp, color = TextSecondary)
            Text(text = amount, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(50)),
            color = color,
            trackColor = SurfaceContainerHighest
        )
    }
}

@Composable
private fun QuestItemRow(
    quest: DailyQuest,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (quest.isCompleted) SurfaceContainerLow else SurfaceContainerHigh)
            .clickable { onToggle() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (quest.isCompleted) PrimaryEmerald else SurfaceContainerHighest)
                    .border(
                        1.dp,
                        if (quest.isCompleted) PrimaryEmerald else TextMuted,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (quest.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = OnPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Column {
                Text(
                    text = quest.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    textDecoration = if (quest.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text = quest.subtitle,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }
        }

        Text(
            text = "+${quest.xp} XP",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (quest.isCompleted) PrimaryFixedDim else PrimaryEmerald
        )
    }
}
