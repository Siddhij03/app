package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.EventRepeat
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.RoutineScheduleItem
import com.example.model.TimeTelemetryNode
import com.example.ui.NutriPulseViewModel
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.ErrorRed
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
import com.example.ui.theme.TertiaryPurple
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun EnergyForecastScreen(
    viewModel: NutriPulseViewModel,
    modifier: Modifier = Modifier
) {
    val isCurveLifted by viewModel.isCurveLifted.collectAsState()
    val selectedNode by viewModel.selectedTimeNode.collectAsState()
    val timeNodes = viewModel.timeNodes
    val scheduleItems = viewModel.routineSchedule

    val dates = listOf("Yesterday, Oct 23", "Today, Oct 24", "Tomorrow, Oct 25")
    var dateIndex by remember { mutableIntStateOf(1) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header & Date Switcher
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = null,
                            tint = PrimaryEmerald,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Future Energy Forecast",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                    }

                    // Date Switcher
                    Row(
                        modifier = Modifier
                            .background(SurfaceContainer, RoundedCornerShape(50))
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (dateIndex > 0) dateIndex-- },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.ChevronLeft, contentDescription = "Prev", tint = TextMuted, modifier = Modifier.size(18.dp))
                        }
                        Text(
                            text = dates[dateIndex],
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                        IconButton(
                            onClick = { if (dateIndex < dates.size - 1) dateIndex++ },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.ChevronRight, contentDescription = "Next", tint = TextMuted, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                Text(
                    text = "Predict your day. Prepare your meals.",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // Metabolic Velocity Interactive Canvas Curve Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainer)
                    .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(modifier = Modifier.size(8.dp).background(PrimaryEmerald, CircleShape))
                            Text(text = "Metabolic Velocity", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }

                        Box(
                            modifier = Modifier
                                .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                .padding(horizontal = 10.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "Biomarker Simulation",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryEmerald
                            )
                        }
                    }

                    // Interactive Kinetic Curve Graph
                    val dipDepth by animateFloatAsState(
                        targetValue = if (isCurveLifted) 0.5f else 0.85f,
                        label = "dipDepth"
                    )

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                    ) {
                        val w = size.width
                        val h = size.height

                        // Grid benchmark lines
                        drawLine(
                            color = SurfaceContainerHighest,
                            start = Offset(0f, h * 0.25f),
                            end = Offset(w, h * 0.25f),
                            strokeWidth = 1.dp.toPx()
                        )
                        drawLine(
                            color = SurfaceContainerHighest,
                            start = Offset(0f, h * 0.65f),
                            end = Offset(w, h * 0.65f),
                            strokeWidth = 1.dp.toPx()
                        )

                        // Path calculation for circadian wave
                        val path = Path().apply {
                            moveTo(0f, h * 0.45f)
                            cubicTo(w * 0.15f, h * 0.15f, w * 0.25f, h * 0.15f, w * 0.35f, h * 0.25f)
                            cubicTo(w * 0.42f, h * 0.35f, w * 0.50f, h * dipDepth, w * 0.58f, h * dipDepth)
                            cubicTo(w * 0.66f, h * dipDepth, w * 0.72f, h * 0.45f, w * 0.80f, h * 0.20f)
                            cubicTo(w * 0.88f, h * 0.20f, w * 0.95f, h * 0.65f, w, h * 0.70f)
                        }

                        // Gradient fill under the curve
                        val fillPath = Path().apply {
                            addPath(path)
                            lineTo(w, h)
                            lineTo(0f, h)
                            close()
                        }

                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    (if (isCurveLifted) PrimaryEmerald else SecondaryAmber).copy(alpha = 0.35f),
                                    Color.Transparent
                                )
                            )
                        )

                        // Stroke line
                        drawPath(
                            path = path,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    PrimaryEmerald,
                                    if (isCurveLifted) PrimaryEmerald else SecondaryAmber,
                                    PrimaryEmerald,
                                    TextMuted
                                )
                            ),
                            style = Stroke(width = 3.5.dp.toPx(), cap = StrokeCap.Round)
                        )

                        // Slump Pin at 2:15 PM (around x = w * 0.55f)
                        val pinX = w * 0.55f
                        val pinY = h * dipDepth
                        drawCircle(
                            color = if (isCurveLifted) PrimaryEmerald else SecondaryAmber,
                            radius = 6.dp.toPx(),
                            center = Offset(pinX, pinY)
                        )
                        drawCircle(
                            color = ObsidianBase,
                            radius = 2.5.dp.toPx(),
                            center = Offset(pinX, pinY)
                        )
                    }

                    // Time Scrubber Buttons Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        timeNodes.forEach { node ->
                            val isSelected = selectedNode.timeLabel == node.timeLabel
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(if (isSelected) SecondaryContainer else Color.Transparent)
                                    .clickable { viewModel.selectTimeNode(node) }
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = node.timeLabel,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                    color = if (isSelected) Color(0xFF2C1700) else TextSecondary
                                )
                            }
                        }
                    }

                    // Scrubber Telemetry Diagnostic Grid
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(SurfaceContainerHigh)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = selectedNode.fullTime,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = PrimaryEmerald
                                )
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (selectedNode.percentage < 65) SecondaryAmber else PrimaryEmerald,
                                            RoundedCornerShape(50)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${selectedNode.percentage}% ${selectedNode.status}",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF101B24)
                                    )
                                }
                            }
                            Text(
                                text = "DIAGNOSTIC",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMuted,
                                letterSpacing = 0.8.sp
                            )
                        }

                        // 2x2 Telemetry Grid
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DiagnosticItem(
                                icon = Icons.Default.Schedule,
                                title = "Circadian Phase",
                                value = selectedNode.circadianPhase,
                                iconColor = PrimaryEmerald,
                                modifier = Modifier.weight(1f)
                            )
                            DiagnosticItem(
                                icon = Icons.Default.Restaurant,
                                title = "Digestion State",
                                value = selectedNode.digestionState,
                                iconColor = SecondaryAmber,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DiagnosticItem(
                                icon = Icons.Default.Coffee,
                                title = "Caffeine Decay",
                                value = selectedNode.caffeineDecay,
                                iconColor = ErrorRed,
                                modifier = Modifier.weight(1f)
                            )
                            DiagnosticItem(
                                icon = Icons.Default.WaterDrop,
                                title = "Hydration Delta",
                                value = selectedNode.hydrationDelta,
                                iconColor = CyanAccent,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Energy Dip Alert Panel (Proactive Intervention)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, SecondaryAmber.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .background(SecondaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color(0xFF2C1700),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "PROACTIVE INTERVENTION",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryAmber,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = if (isCurveLifted) "Slump Mitigated by Protocol" else "Moderate Slump Expected at 2:15 PM",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextPrimary
                            )
                        }
                    }

                    // Root Cause Analysis Box
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainer)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "ROOT CAUSE ANALYSIS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextMuted,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "High carb lunch scheduled + 4 hours elapsed since morning hydration + natural circadian dip in core body temperature.",
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 16.sp
                        )
                    }

                    // Instant Energy Fix Protocols
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Instant Energy Fix Protocol",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        ProtocolItem(
                            icon = Icons.Default.WaterDrop,
                            title = "Hydrate Electrolytes",
                            desc = "400ml cold water + pinch of pink salt",
                            gain = "+8%",
                            color = PrimaryEmerald
                        )
                        ProtocolItem(
                            icon = Icons.Default.Fastfood,
                            title = "Smart Sustain Snack",
                            desc = "1 Banana + 1 tbsp Peanut Butter (180 kcal, Low GI)",
                            gain = "+12%",
                            color = SecondaryAmber
                        )
                        ProtocolItem(
                            icon = Icons.Default.WbSunny,
                            title = "10-Min Outdoor Stride",
                            desc = "Photobiotic reset via natural spectrum lux",
                            gain = "+15%",
                            color = TertiaryPurple
                        )
                    }

                    // Boost Button
                    Button(
                        onClick = { viewModel.fixEnergyBoost() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("boost_curve_button"),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isCurveLifted) SurfaceContainerHighest else PrimaryEmerald,
                            contentColor = if (isCurveLifted) TextPrimary else OnPrimary
                        )
                    ) {
                        Icon(
                            imageVector = if (isCurveLifted) Icons.Default.DoneAll else Icons.Default.Bolt,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isCurveLifted) "Protocol Active (+20% Lifted) ✓" else "Log Fix & Boost Predicted Energy (+20% curve lift)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }

        // Synchronized Schedule (Meal & Routine)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SYNCHRONIZED SCHEDULE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryEmerald,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "Meal & Routine Schedule",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.EventRepeat,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    scheduleItems.forEach { item ->
                        ScheduleTimelineRow(item)
                    }
                }
            }
        }

        // Tonight's Sleep Forecast
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
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Bedtime, contentDescription = null, tint = TertiaryPurple, modifier = Modifier.size(22.dp))
                            Text(text = "Tonight's Sleep Forecast", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }

                        Box(
                            modifier = Modifier
                                .background(SurfaceContainerHigh, RoundedCornerShape(50))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(text = "82% Restful", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                        }
                    }

                    LinearProgressIndicator(
                        progress = { 0.82f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(50)),
                        color = TertiaryPurple,
                        trackColor = SurfaceContainerHighest
                    )

                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                        Text(
                            text = "Dinner scheduled at 8:30 PM gives a 2h 15m digestion buffer before sleep. Cut caffeine intake after 3:00 PM to maximize slow-wave delta cycles.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        // Medical disclaimer pill
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLow, RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚠️ NutriPulse forecasts are algorithmic estimations based on user logs and circadian science, not a medical diagnosis.",
                    fontSize = 10.sp,
                    color = TextMuted
                )
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
private fun DiagnosticItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceContainerLow)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(16.dp))
        Column {
            Text(text = title, fontSize = 10.sp, color = TextMuted)
            Text(text = value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary, maxLines = 1)
        }
    }
}

@Composable
private fun ProtocolItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String,
    gain: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer)
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
                    .size(32.dp)
                    .background(color.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
            }
            Column {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = desc, fontSize = 11.sp, color = TextMuted, maxLines = 1)
            }
        }
        Text(text = gain, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
private fun ScheduleTimelineRow(item: RoutineScheduleItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (item.isSlumpDefense) SurfaceContainerHigh else SurfaceContainer)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = item.time,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (item.isSlumpDefense) SecondaryAmber else PrimaryEmerald
            )
            Text(text = item.amPm, fontSize = 10.sp, color = TextMuted)
        }

        Box(
            modifier = Modifier
                .width(3.dp)
                .height(34.dp)
                .background(if (item.isSlumpDefense) SecondaryAmber else PrimaryEmerald, RoundedCornerShape(2.dp))
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                if (item.tag != null) {
                    Text(
                        text = item.tag,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (item.isSlumpDefense) SecondaryAmber else PrimaryEmerald
                    )
                }
            }
            Text(
                text = item.subtitle,
                fontSize = 11.sp,
                color = TextSecondary,
                maxLines = 1
            )
        }
    }
}
