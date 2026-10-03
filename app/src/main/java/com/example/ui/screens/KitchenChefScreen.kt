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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Blender
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.PantryItem
import com.example.model.Recipe
import com.example.ui.NutriPulseViewModel
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
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun KitchenChefScreen(
    viewModel: NutriPulseViewModel,
    modifier: Modifier = Modifier
) {
    val pantryItems by viewModel.pantryItems.collectAsState()
    var selectedCategory by remember { mutableStateOf("All (14)") }
    var searchQuery by remember { mutableStateOf("") }
    var chefPromptText by remember { mutableStateOf("") }
    var showAddItemDialog by remember { mutableStateOf(false) }

    var newItemName by remember { mutableStateOf("") }
    var newItemQty by remember { mutableStateOf("1") }
    var newItemEmoji by remember { mutableStateOf("🥑") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBase),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sustainable Impact Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(14.dp))
                            Text(text = "ZERO-WASTE ENGINE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald, letterSpacing = 1.sp)
                        }
                        Text(text = "My Kitchen & Chef AI", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    }

                    Row(
                        modifier = Modifier
                            .background(SurfaceContainerHigh, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.Eco, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                        Text(text = "Stats", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                    }
                }

                // Food Saved This Week Banner
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
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .background(PrimaryEmerald.copy(alpha = 0.15f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Savings, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                                }
                                Column {
                                    Text(text = "Food Saved This Week", fontSize = 11.sp, color = TextSecondary)
                                    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Text(text = "6 items", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                                        Text(text = "(₹380 saved)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .background(SecondaryContainer.copy(alpha = 0.2f), RoundedCornerShape(50))
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(14.dp))
                                Text(text = "5d Streak", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                            }
                        }

                        // Level & XP Progress
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(SurfaceContainerLow)
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(Icons.Default.MilitaryTech, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(14.dp))
                                    Text(text = "Level 3 Zero-Waster", fontSize = 11.sp, color = TextSecondary)
                                }
                                Text(text = "+50 XP to Next Tier", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                            }
                            LinearProgressIndicator(
                                progress = { 0.78f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(50)),
                                color = PrimaryEmerald,
                                trackColor = SurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Expiring Soon Section
        item {
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
                        Box(modifier = Modifier.size(8.dp).background(SecondaryAmber, CircleShape))
                        Text(text = "Expiring Soon", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                    Box(
                        modifier = Modifier
                            .background(SecondaryAmber.copy(alpha = 0.15f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(text = "3 NEEDS COOKED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SecondaryAmber)
                    }
                }

                // Horizontal Swipeable Cards
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    item {
                        UrgentExpiryCard(
                            emoji = "🍌",
                            name = "Organic Bananas",
                            location = "2 left in fruit basket",
                            badgeText = "TODAY",
                            badgeColor = ErrorRed,
                            onCookWithAi = { chefPromptText = "Cook with bananas" }
                        )
                    }
                    item {
                        UrgentExpiryCard(
                            emoji = "🥬",
                            name = "Baby Spinach",
                            location = "180g in crisper drawer",
                            badgeText = "2 Days",
                            badgeColor = SecondaryAmber,
                            onCookWithAi = { chefPromptText = "Cook with baby spinach" }
                        )
                    }
                    item {
                        UrgentExpiryCard(
                            emoji = "🥛",
                            name = "Greek Yogurt",
                            location = "Half tub remaining",
                            badgeText = "3 Days",
                            badgeColor = PrimaryEmerald,
                            onCookWithAi = { chefPromptText = "Cook with greek yogurt" }
                        )
                    }
                }
            }
        }

        // Chef AI Meal Engine Section
        item {
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
                        Icon(Icons.Default.SmartToy, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(20.dp))
                        Text(text = "Chef AI Meal Engine", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                    Box(
                        modifier = Modifier
                            .background(PrimaryEmerald.copy(alpha = 0.15f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(text = "8 In-Stock Ready", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                    }
                }
                Text(
                    text = "Instant recipes prioritized by expiry timeline & sustained stamina",
                    fontSize = 12.sp,
                    color = TextSecondary
                )

                // Recipe Filter Pills
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf("All (3 Ready)", "High Protein (>25g)", "Quick (<15 min)", "Low Energy Crash Risk")) { filter ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (filter == "All (3 Ready)") PrimaryEmerald else SurfaceContainer)
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = filter,
                                fontSize = 11.sp,
                                fontWeight = if (filter == "All (3 Ready)") FontWeight.ExtraBold else FontWeight.Medium,
                                color = if (filter == "All (3 Ready)") OnPrimary else TextSecondary
                            )
                        }
                    }
                }
            }
        }

        // Recipe Card 1: Spinach & Paneer Power Bowl
        item {
            RecipeCardView(
                recipe = viewModel.powerBowlRecipe,
                onCookAction = { viewModel.cookRecipe(viewModel.powerBowlRecipe) }
            )
        }

        // Recipe Card 2: Banana Smoothie
        item {
            RecipeCardView(
                recipe = viewModel.bananaSmoothieRecipe,
                onCookAction = { viewModel.cookRecipe(viewModel.bananaSmoothieRecipe) }
            )
        }

        // Recipe Card 3: Missing Item Substitute Card
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
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(SurfaceVariant, RoundedCornerShape(50))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(text = "Missing 1 Item", fontSize = 10.sp, color = TextMuted)
                                }
                                Text(text = "Smart Sub Ready", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryFixedDim)
                            }
                            Text(text = "Skillet Egg & Veggie Scramble", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "8 min • 290 kcal", fontSize = 11.sp, color = TextMuted)
                            Text(text = "24g Protein", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(18.dp))
                        Text(
                            text = "Missing Sourdough Bread — swap easily with your in-stock Rolled Oats.",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        // Pantry & Fridge Hub
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Pantry & Fridge Hub", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(text = "Live telemetry of stored fuel", fontSize = 11.sp, color = TextMuted)
                    }

                    Button(
                        onClick = { showAddItemDialog = true },
                        modifier = Modifier
                            .height(36.dp)
                            .testTag("add_pantry_item_button"),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryEmerald,
                            contentColor = OnPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Add Item", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Search Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(SurfaceContainer)
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = TextMuted, modifier = Modifier.size(18.dp))
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Search 14 ingredients or scan...", fontSize = 12.sp, color = TextMuted) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                    IconButton(
                        onClick = { viewModel.showToast("Barcode Scanner triggered") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan Barcode", tint = TextMuted, modifier = Modifier.size(18.dp))
                    }
                    IconButton(
                        onClick = { viewModel.showToast("Receipt Vision AI triggered") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.PhotoCamera, contentDescription = "Receipt OCR", tint = TextMuted, modifier = Modifier.size(18.dp))
                    }
                }

                // Category Tabs
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val categories = listOf("All (14)", "Produce (5)", "Dairy & Protein (4)", "Pantry Staples (5)")
                    items(categories) { cat ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (selectedCategory == cat) SurfaceVariant else SurfaceContainer)
                                .clickable { selectedCategory = cat }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 11.sp,
                                fontWeight = if (selectedCategory == cat) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedCategory == cat) TextPrimary else TextSecondary
                            )
                        }
                    }
                }

                // Inventory Items
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    val filtered = pantryItems.filter {
                        searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)
                    }
                    filtered.forEach { item ->
                        InventoryItemRow(
                            item = item,
                            onDecrease = { viewModel.updatePantryQuantity(item.id, -1) },
                            onIncrease = { viewModel.updatePantryQuantity(item.id, 1) }
                        )
                    }
                }
            }
        }

        // Pantry Intelligence & Replenishment Guardrails
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Default.Psychology, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(18.dp))
                    Text(text = "Pantry Intelligence", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                }

                // Don't buy warning
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceContainerHigh)
                        .padding(12.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.DoNotDisturbOn, contentDescription = null, tint = SecondaryAmber, modifier = Modifier.size(18.dp))
                    Column {
                        Text(text = "Avoid Buying: Eggs, Oats & Basmati Rice", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(
                            text = "Telemetry predicts adequate supplies to cover 4 days of meal macros. Buying more risks spoilage.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                }

                // Restock suggestion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceContainer)
                        .padding(12.dp),
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
                                .size(34.dp)
                                .background(PrimaryEmerald.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.AddShoppingCart, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                        }
                        Column {
                            Text(text = "Unlock 4 Recipes with 2 items", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text(text = "Recommended restock: Chia Seeds & Lemons", fontSize = 11.sp, color = TextMuted)
                        }
                    }
                }
            }
        }

        // Conversational AI Prompt Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, PrimaryEmerald.copy(alpha = 0.3f), RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(PrimaryEmerald, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.SmartToy, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(16.dp))
                }

                OutlinedTextField(
                    value = chefPromptText,
                    onValueChange = { chefPromptText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask AI: What can I cook in under 10 min?", fontSize = 12.sp, color = TextMuted) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )

                IconButton(
                    onClick = {
                        if (chefPromptText.isNotBlank()) {
                            viewModel.showToast("Chef AI: Generating custom recipe for \"$chefPromptText\"...")
                            chefPromptText = ""
                        }
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send", tint = PrimaryEmerald, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }

    // Add Item Dialog
    if (showAddItemDialog) {
        AlertDialog(
            onDismissRequest = { showAddItemDialog = false },
            title = { Text("Add to Kitchen Pantry", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = newItemName,
                        onValueChange = { newItemName = it },
                        label = { Text("Item Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newItemEmoji,
                        onValueChange = { newItemEmoji = it },
                        label = { Text("Emoji Icon") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newItemQty,
                        onValueChange = { newItemQty = it },
                        label = { Text("Quantity") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val qty = newItemQty.toIntOrNull() ?: 1
                        if (newItemName.isNotBlank()) {
                            viewModel.addPantryItem(newItemName, newItemEmoji, qty, "")
                            showAddItemDialog = false
                            newItemName = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryEmerald, contentColor = OnPrimary)
                ) {
                    Text("Add Item")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddItemDialog = false }) {
                    Text("Cancel", color = TextMuted)
                }
            },
            containerColor = SurfaceContainer
        )
    }
}

@Composable
private fun UrgentExpiryCard(
    emoji: String,
    name: String,
    location: String,
    badgeText: String,
    badgeColor: Color,
    onCookWithAi: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer)
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = emoji, fontSize = 24.sp)
                Box(
                    modifier = Modifier
                        .background(badgeColor.copy(alpha = 0.2f), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = badgeText, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = badgeColor)
                }
            }

            Column {
                Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = location, fontSize = 11.sp, color = TextMuted)
            }

            Button(
                onClick = onCookWithAi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SurfaceContainerHigh,
                    contentColor = PrimaryEmerald
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Cook with AI", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun RecipeCardView(
    recipe: Recipe,
    onCookAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceContainer)
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Photo Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, ObsidianBase.copy(alpha = 0.8f))
                            )
                        )
                )

                // Badges top
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (recipe.isInStock) {
                        Row(
                            modifier = Modifier
                                .background(PrimaryEmerald, RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(12.dp))
                            Text(text = "100% In Stock", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnPrimary)
                        }
                    }

                    if (recipe.wasteSaverLabel != null) {
                        Box(
                            modifier = Modifier
                                .background(SecondaryAmber, RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(text = recipe.wasteSaverLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2C1700))
                        }
                    }
                }

                // Macro pill bottom
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .background(ObsidianBase.copy(alpha = 0.85f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = TextMuted, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = "${recipe.readyMinutes} min", fontSize = 11.sp, color = TextPrimary)
                        }
                        Text(text = "${recipe.calories} kcal", fontSize = 11.sp, color = TextPrimary)
                    }

                    Box(
                        modifier = Modifier
                            .background(ObsidianBase.copy(alpha = 0.85f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(text = "${recipe.proteinGrams}g Protein", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
                    }
                }
            }

            Column {
                Text(text = recipe.title, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = recipe.subtitle, fontSize = 11.sp, color = TextSecondary)
            }

            // Benefit box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(SurfaceContainerLow)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Default.BatteryChargingFull, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(16.dp))
                    Text(text = recipe.benefitHighlight, fontSize = 11.sp, color = TextPrimary)
                }
                Text(text = recipe.staminaBoost, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryEmerald)
            }

            Button(
                onClick = onCookAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("cook_recipe_button"),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryEmerald,
                    contentColor = OnPrimary
                )
            ) {
                Text(text = "Cook Recipe & Auto-Deduct", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun InventoryItemRow(
    item: PantryItem,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainer)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(SurfaceContainerHigh, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.emoji, fontSize = 20.sp)
            }

            Column {
                Text(text = item.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = item.expiryText,
                        fontSize = 11.sp,
                        color = if (item.isUrgent) ErrorRed else SecondaryAmber,
                        fontWeight = if (item.isUrgent) FontWeight.Bold else FontWeight.Normal
                    )
                    Text(text = "•", fontSize = 10.sp, color = TextMuted)
                    Text(text = item.location, fontSize = 11.sp, color = TextMuted)
                }
            }
        }

        // +/- Adjuster
        Row(
            modifier = Modifier
                .background(SurfaceContainerLow, RoundedCornerShape(50))
                .padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clickable { onDecrease() },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "–", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
            }

            Text(
                text = "${item.quantity}${item.unit}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clickable { onIncrease() },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
            }
        }
    }
}
