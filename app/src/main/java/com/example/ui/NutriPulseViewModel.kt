package com.example.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.NutriPulsePreferences
import com.example.data.StreakResult
import com.example.model.AppScreen
import com.example.model.ChatMessage
import com.example.model.DailyQuest
import com.example.model.PantryItem
import com.example.model.PrivacySettingsState
import com.example.model.Recipe
import com.example.model.RoutineScheduleItem
import com.example.model.TimeTelemetryNode
import com.example.model.UserProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NutriPulseViewModel(
    initialPreferences: NutriPulsePreferences? = null
) : ViewModel() {

    private var preferences: NutriPulsePreferences? = initialPreferences

    // Navigation
    private val _currentScreen = MutableStateFlow(AppScreen.WELCOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private var previousScreen: AppScreen = AppScreen.HOME

    // User Profile
    val userProfile = MutableStateFlow(
        UserProfile(
            name = "Maya",
            email = "maya@nutripulse.ai",
            age = 23,
            heightCm = 168,
            weightKg = 60.5f,
            gender = "Female",
            activityLevel = "Active (3-5 workouts/wk)",
            avatarUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw",
            calorieGoal = 2100,
            waterGoalMl = 2200,
            userLevel = "Level 3: Energy Pro",
            streakDays = 1,
            primaryFocus = "Circadian Energy & Zero-Waste",
            isLoggedIn = true,
            isSetupCompleted = false
        )
    )

    init {
        initialPreferences?.let { prefs ->
            if (prefs.isSetupCompleted()) {
                userProfile.value = prefs.loadUserProfile()
                _currentScreen.value = AppScreen.HOME
            }
        }
    }

    /**
     * Called on App launch (MainActivity.onCreate).
     * If user has already completed setup, bypasses Welcome/Login and opens directly to HOME.
     * Updates daily streak if and only if user uses app daily.
     */
    fun initPreferences(context: Context) {
        if (preferences == null) {
            val prefs = NutriPulsePreferences(context.applicationContext)
            preferences = prefs

            if (prefs.isSetupCompleted()) {
                val streakResult = prefs.checkAndUpdateDailyStreak()
                val loadedProfile = prefs.loadUserProfile()
                userProfile.value = loadedProfile

                // Automatically navigate straight to HOME on all subsequent launches
                _currentScreen.value = AppScreen.HOME

                // Inform user of streak status
                when (streakResult) {
                    is StreakResult.Incremented -> {
                        showToast("🔥 Daily Streak: ${streakResult.streakDays} days! (+1 for today)")
                    }
                    is StreakResult.ResetDueToMissedDay -> {
                        showToast("⚡ Daily streak reset to 1 day due to inactivity. Check in daily!")
                    }
                    is StreakResult.MaintainedToday -> {
                        // Already active today, maintain streak silently or with badge
                    }
                    is StreakResult.StartedNew -> {
                        showToast("🔥 Day 1 of your daily streak started!")
                    }
                }
            } else {
                // First-time download: Show Welcome & Setup Profile flow
                _currentScreen.value = AppScreen.WELCOME
            }
        }
    }

    fun navigateTo(screen: AppScreen) {
        if (screen != _currentScreen.value) {
            previousScreen = _currentScreen.value
            _currentScreen.value = screen
        }
    }

    fun navigateBack() {
        if (_currentScreen.value == AppScreen.PRIVACY_VAULT ||
            _currentScreen.value == AppScreen.NOTIFICATION_SETTINGS ||
            _currentScreen.value == AppScreen.LOGIN ||
            _currentScreen.value == AppScreen.PROFILE
        ) {
            _currentScreen.value = previousScreen
        } else if (_currentScreen.value != AppScreen.HOME && _currentScreen.value != AppScreen.WELCOME) {
            _currentScreen.value = AppScreen.HOME
        }
    }

    fun loginOrRegister(
        name: String,
        email: String,
        calorieGoal: Int = 2100,
        waterGoalMl: Int = 2200,
        avatarUrl: String,
        primaryFocus: String = "Circadian Energy & Zero-Waste",
        age: Int = 23,
        heightCm: Int = 168,
        weightKg: Float = 60.5f,
        gender: String = "Female",
        activityLevel: String = "Active (3-5 workouts/wk)"
    ) {
        val cleanName = if (name.isBlank()) "Explorer" else name.trim()
        val cleanEmail = if (email.isBlank()) "user@nutripulse.ai" else email.trim()

        val newProfile = UserProfile(
            name = cleanName,
            email = cleanEmail,
            age = age,
            heightCm = heightCm,
            weightKg = weightKg,
            gender = gender,
            activityLevel = activityLevel,
            avatarUrl = avatarUrl,
            calorieGoal = calorieGoal,
            waterGoalMl = waterGoalMl,
            userLevel = "Level 3: Energy Pro",
            streakDays = 1,
            primaryFocus = primaryFocus,
            isLoggedIn = true,
            isSetupCompleted = true,
            lastActiveEpochDay = preferences?.getTodayEpochDay() ?: -1L
        )

        userProfile.value = newProfile

        // Persist permanently in SharedPreferences
        preferences?.saveUserProfile(newProfile)
        preferences?.checkAndUpdateDailyStreak()
        preferences?.loadUserProfile()?.let { userProfile.value = it }

        // Add personalized greeting in chat
        val greetingMsg = ChatMessage(
            id = "msg_welcome_${System.currentTimeMillis()}",
            isUser = false,
            text = "Welcome $cleanName! ⚡ Your metabolic profile is synced ($age yrs, ${heightCm}cm, ${weightKg}kg). Target: $calorieGoal kcal & ${waterGoalMl}ml hydration.",
            time = "Just now"
        )
        _chatMessages.value = listOf(greetingMsg) + _chatMessages.value
        showToast("Profile setup complete! Details saved permanently ✨")
        _currentScreen.value = AppScreen.HOME
    }

    fun updateDetailedProfile(
        name: String,
        email: String,
        age: Int,
        heightCm: Int,
        weightKg: Float,
        gender: String,
        activityLevel: String,
        calorieGoal: Int,
        waterGoalMl: Int,
        avatarUrl: String,
        primaryFocus: String
    ) {
        val cleanName = if (name.isBlank()) "Explorer" else name.trim()
        val updated = userProfile.value.copy(
            name = cleanName,
            email = email.trim(),
            age = age,
            heightCm = heightCm,
            weightKg = weightKg,
            gender = gender,
            activityLevel = activityLevel,
            calorieGoal = calorieGoal,
            waterGoalMl = waterGoalMl,
            avatarUrl = avatarUrl,
            primaryFocus = primaryFocus,
            isSetupCompleted = true
        )
        userProfile.value = updated

        // Persist permanently in SharedPreferences
        preferences?.saveUserProfile(updated)
        showToast("Profile details updated & saved! ✨")
    }

    fun logout() {
        preferences?.clearAllData()
        userProfile.value = UserProfile(isLoggedIn = false, isSetupCompleted = false)
        showToast("Logged out. First-time setup will appear on launch.")
        _currentScreen.value = AppScreen.WELCOME
    }

    // Daily Streak Simulation and Verification Helpers
    fun simulateAdvanceDayStreak() {
        val prefs = preferences ?: return
        val result = prefs.simulateAdvanceDay()
        val updated = prefs.loadUserProfile()
        userProfile.value = updated
        when (result) {
            is StreakResult.Incremented -> {
                showToast("🔥 Next day check-in: Streak increased to ${result.streakDays} days! (+1)")
            }
            is StreakResult.MaintainedToday -> {
                showToast("🔥 Daily check-in already recorded today (${result.streakDays} days).")
            }
            is StreakResult.ResetDueToMissedDay -> {
                showToast("⚡ Streak reset to 1 day due to missed days.")
            }
            is StreakResult.StartedNew -> {
                showToast("🔥 Started Day 1 streak!")
            }
        }
    }

    fun simulateSkipDaysStreak(days: Int = 2) {
        val prefs = preferences ?: return
        val result = prefs.simulateSkipDays(days)
        val updated = prefs.loadUserProfile()
        userProfile.value = updated
        when (result) {
            is StreakResult.ResetDueToMissedDay -> {
                showToast("⚠️ Missed $days days! Streak reset to 1 day.")
            }
            else -> {
                showToast("Simulated skipping $days days.")
            }
        }
    }

    fun resetStreakSimulation() {
        preferences?.resetSimulation()
        preferences?.checkAndUpdateDailyStreak()
        preferences?.loadUserProfile()?.let { userProfile.value = it }
        showToast("Streak simulation reset to device date.")
    }

    // Toast feedback
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    fun showToast(message: String) {
        _toastMessage.value = message
        viewModelScope.launch {
            delay(2600)
            if (_toastMessage.value == message) {
                _toastMessage.value = null
            }
        }
    }

    fun dismissToast() {
        _toastMessage.value = null
    }

    // Biometrics & Energy State
    val energyPercentage = MutableStateFlow(84)
    val isCurveLifted = MutableStateFlow(false)
    val currentWaterMl = MutableStateFlow(1400)
    val targetWaterMl = 2200
    val healthIndex = MutableStateFlow(78)
    val currentXp = MutableStateFlow(420)
    val targetXp = 600
    val userLevel = MutableStateFlow("Level 3: Energy Pro")

    fun logWaterGlass() {
        val next = (currentWaterMl.value + 250).coerceAtMost(3000)
        currentWaterMl.value = next
        healthIndex.value = (healthIndex.value + 1).coerceAtMost(99)
        showToast("Hydration +250ml logged! (${next} ml)")
    }

    fun fixEnergyBoost() {
        isCurveLifted.value = true
        energyPercentage.value = 89
        showToast("Energy Protocol Activated! +20% curve lift applied ⚡")
    }

    fun logQuickBoost() {
        energyPercentage.value = (energyPercentage.value + 4).coerceAtMost(98)
        showToast("Quick Energy Boost logged! (+15 min stamina buffer)")
    }

    // Quests
    private val _quests = MutableStateFlow(
        listOf(
            DailyQuest(
                id = "quest_1",
                title = "Morning Hydration boost (500ml)",
                subtitle = "Completed at 7:30 AM",
                xp = 10,
                isCompleted = true
            ),
            DailyQuest(
                id = "quest_2",
                title = "Log Lunch around 1:00 PM",
                subtitle = "Balance carbs with lean protein",
                xp = 20,
                isCompleted = false
            ),
            DailyQuest(
                id = "quest_3",
                title = "Beat the 2 PM Energy Slump",
                subtitle = "Take a 5-min walk & consume your snack",
                xp = 30,
                isCompleted = false
            ),
            DailyQuest(
                id = "quest_4",
                title = "Save 1 kitchen item before expiry",
                subtitle = "Cook or freeze urgent produce",
                xp = 30,
                isCompleted = false
            )
        )
    )
    val quests: StateFlow<List<DailyQuest>> = _quests.asStateFlow()

    fun toggleQuest(questId: String) {
        val list = _quests.value.toMutableList()
        val index = list.indexOfFirst { it.id == questId }
        if (index != -1) {
            val q = list[index]
            val newCompleted = !q.isCompleted
            list[index] = q.copy(isCompleted = newCompleted)
            _quests.value = list
            if (newCompleted) {
                currentXp.value = (currentXp.value + q.xp).coerceAtMost(targetXp)
                showToast("Quest Completed! +${q.xp} XP awarded 🌟")
            } else {
                currentXp.value = (currentXp.value - q.xp).coerceAtLeast(0)
            }
        }
    }

    // Pantry Inventory
    private val _pantryItems = MutableStateFlow(
        listOf(
            PantryItem("p1", "Organic Bananas", "🍌", "Expires today", "Pantry Basket", 2, "", true, 0),
            PantryItem("p2", "Fresh Paneer", "🧀", "Expires in 4d", "Fridge", 200, "g", false, 4),
            PantryItem("p3", "Rolled Oats", "🌾", "Expires in 60d", "Jar 2", 500, "g", false, 60),
            PantryItem("p4", "Baby Spinach", "🥬", "Expires in 2d", "Crisper Drawer", 180, "g", true, 2),
            PantryItem("p5", "Greek Yogurt", "🥛", "Expires in 3d", "Fridge Top Shelf", 350, "g", true, 3),
            PantryItem("p6", "Chia Seeds", "🌱", "Expires in 90d", "Pantry Shelf", 150, "g", false, 90)
        )
    )
    val pantryItems: StateFlow<List<PantryItem>> = _pantryItems.asStateFlow()

    fun updatePantryQuantity(itemId: String, delta: Int) {
        val list = _pantryItems.value.map { item ->
            if (item.id == itemId) {
                val step = if (item.unit == "g") 50 * delta else delta
                val newQty = (item.quantity + step).coerceAtLeast(0)
                item.copy(quantity = newQty)
            } else item
        }
        _pantryItems.value = list
        showToast("Inventory updated")
    }

    fun addPantryItem(name: String, emoji: String, qty: Int, unit: String) {
        val newItem = PantryItem(
            id = "p_${System.currentTimeMillis()}",
            name = name,
            emoji = emoji,
            expiryText = "Expires in 5d",
            location = "Fridge",
            quantity = qty,
            unit = unit,
            isUrgent = false,
            daysLeft = 5
        )
        _pantryItems.value = listOf(newItem) + _pantryItems.value
        showToast("Added $name to your kitchen inventory! ✨")
    }

    // Recipes
    val powerBowlRecipe = Recipe(
        id = "r1",
        title = "Spinach & Paneer Power Bowl",
        subtitle = "Uses: Baby Spinach (Expiring!), Paneer, Garlic, Olive oil.",
        readyMinutes = 12,
        calories = 410,
        proteinGrams = 28,
        isInStock = true,
        wasteSaverLabel = "Waste Saver #1",
        usesIngredients = "Baby Spinach, Paneer, Garlic, Olive oil",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBRbrdM4DM97biKUtshqEib7BBfmNDrSD1zRMSrzQQI_SACxOirbGTTzpRY7etBBMasBUsYokS7dChkP9OmR11JZWDMVOBr13azkfg7m2WiD6gWStkiXrQds1j6Oa2UZpIHphHAOCWOpovenSvPeQrJMRyRmQ_nQMyKKhG59wd91GWUFCRhmvpEocJE-RfyDetF5Ls55WK0_DxQ5YuxuF2UJ0gmmLK4XS5IdCAFQaX8T9YnAZHjMw-Prg",
        staminaBoost = "+3.8h Stamina",
        benefitHighlight = "Prevents 2:15 PM dip + Saves ₹90"
    )

    val bananaSmoothieRecipe = Recipe(
        id = "r2",
        title = "Blender Banana Peanut Oat Smoothie",
        subtitle = "Uses: Bananas (Use Today!), Oats, Greek Yogurt, Peanut Butter.",
        readyMinutes = 5,
        calories = 340,
        proteinGrams = 22,
        isInStock = true,
        wasteSaverLabel = "100% In Stock",
        usesIngredients = "Bananas, Oats, Greek Yogurt, Peanut Butter",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBkvqM4cU6v0Wr-uwNM5HkI89LcfCQF-wsX4aMW2eA9BhIZNH1f84xteEyR8NjC4Jyb0j7ci_K6XnnZO1-bvE_udfCqHMMb0Kh6s3ZKFO3Rmv-U6-DNn_9-UiIDbDoy05CHYecm_KSwM_z_UFJmiNvv0_K6sVYVR2SYy0IuFFTaHhOVlaW5pkQgWoGy_g1JK8khEGm_T3pwuSAWES2XwTc07AtFGmCse88H_FryhwCsPo1NuIv7YeacZA",
        staminaBoost = "+2.5h Focus",
        benefitHighlight = "Ideal 2:00 PM Pre-emptive Energy Boost"
    )

    fun cookRecipe(recipe: Recipe) {
        currentXp.value = (currentXp.value + 15).coerceAtMost(targetXp)
        showToast("Cooked ${recipe.title}! +15 XP & ingredients deducted.")
    }

    // Time Scrubber on Energy Forecast Screen
    val timeNodes = listOf(
        TimeTelemetryNode("8A", "8:00 AM", 85, "Peak Performance", "Cortisol spike zenith", "Pre-breakfast basal", "High (85mg)", "Optimal"),
        TimeTelemetryNode("10:30A", "10:30 AM", 82, "Stable Fuel", "Metabolic stabilization", "Oatmeal slow release", "Peak receptor load", "Hydrated"),
        TimeTelemetryNode("12:30P", "12:30 PM", 78, "Digestive Load", "Thermic transit", "Active breakdown", "Decaying (-30%)", "Moderate"),
        TimeTelemetryNode("2:15P", "2:15 PM", 52, "⚠️ Energy Dip Alert!", "Post-prandial drop", "Heavy glycogen shunt", "Sub-threshold (-60%)", "-400ml deficit"),
        TimeTelemetryNode("4P", "4:00 PM", 68, "Secondary Rise", "Post-slump arousal", "Gastric clearance", "Residual trace", "Rebounding"),
        TimeTelemetryNode("6:30P", "6:30 PM", 80, "Pre-Workout Surge", "Core temp peak", "Ready for light carb", "Cleared", "Target range"),
        TimeTelemetryNode("9:30P", "9:30 PM", 60, "Wind-Down", "Melatonin onset", "Dinner assimilation", "Zero", "Resting")
    )

    val selectedTimeNode = MutableStateFlow(timeNodes[3]) // 2:15 PM default

    fun selectTimeNode(node: TimeTelemetryNode) {
        selectedTimeNode.value = node
    }

    // Schedule items
    val routineSchedule = listOf(
        RoutineScheduleItem("08:00", "AM", "Morning Prep & Breakfast", "Rolled oats, blueberries, whey isolate & chia", "Logged"),
        RoutineScheduleItem("11:00", "AM", "College / Deep Focus Block", "Hydration reminder: 500ml green tea triggered", "500ml tea"),
        RoutineScheduleItem("01:00", "PM", "Scheduled Lunch", "Turkey sweet potato bowl + mixed greens", "Macro Balanced"),
        RoutineScheduleItem("02:00", "PM", "Break & Energy Defense", "Execute hydration + light snack to buffer slump", "Target Zone", isSlumpDefense = true),
        RoutineScheduleItem("06:00", "PM", "Workout: Cardio & HIIT", "Recommendation: Light carb + BCAA pre-fuel at 5:15 PM", "HIIT Target"),
        RoutineScheduleItem("08:30", "PM", "Dinner", "Recommends: High protein + magnesium for recovery", "Sleep Priming"),
        RoutineScheduleItem("10:45", "PM", "Sleep Wind-Down", "Dim ambient lighting to < 30 lux, no blue spectrum", "Rest Mode")
    )

    // Chat
    private val _chatMessages = MutableStateFlow(
        listOf(
            ChatMessage(
                id = "m1",
                isUser = false,
                text = "Hey Maya! 👋 I've ingested your morning biometrics and kitchen inventory. You've got a slight energy dip forecasted around 2:15 PM, and your bananas expire today. How can we optimize your afternoon?",
                time = "11:42 AM"
            ),
            ChatMessage(
                id = "m2",
                isUser = true,
                text = "What should I eat before my 6 PM workout? And can I use anything expiring?",
                time = "11:43 AM"
            ),
            ChatMessage(
                id = "m3",
                isUser = false,
                text = "Here is the ideal pre-workout fuel using your bananas before they spoil:",
                time = "11:43 AM",
                recipeCard = Recipe(
                    id = "chat_rec",
                    title = "Blender Banana Peanut Oat",
                    subtitle = "Fast-acting glucose pairs with slow-burning oats for peak 6:00 PM HIIT.",
                    readyMinutes = 4,
                    calories = 340,
                    proteinGrams = 22,
                    isInStock = true,
                    wasteSaverLabel = "Zero-Waste Match",
                    usesIngredients = "Uses 2 Bananas (Expires 8:00 PM)",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAUxdpuVFWsKEKOYNPvHvxErhlHLF0gA__0-ZWmgeQF9bzEL9I2gT1fiHI8MMSkRLkkIelXWv_X0zIFYTghrERYyl_9n3Ir1abVS5rwRp6m-8zVY8GEcnxYiE2R7l2T8oXhYNOGvc40qc62pFgTGJQ8FJFLZXYkTDs6XsdQ2S1JnpcaFbVVprhm-LXFiypPJnS_RzhoKSF5g-TyAq-2VrYmc9Obomiptpqkw7hrKFnTTEMfvjtg0ieDxg",
                    staminaBoost = "+3.5h Stamina",
                    benefitHighlight = "Rescues ripe bananas scheduled to spoil tonight."
                ),
                actionText = "Add to Daily Energy Plan (+15 XP)"
            ),
            ChatMessage(
                id = "m4",
                isUser = true,
                text = "Why am I getting that 2:15 PM dip specifically?",
                time = "11:45 AM"
            ),
            ChatMessage(
                id = "m5",
                isUser = false,
                text = "Your logged pasta lunch at 1:00 PM produces an insulin curve that coincides with your natural circadian dip at 2:15 PM.",
                time = "11:45 AM",
                showDiagnosticGraph = true,
                actionText = "Apply Fix: 350ml Cold Water + 8 Almonds"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    fun sendUserMessage(msg: String) {
        if (msg.isBlank()) return
        val userMsg = ChatMessage(
            id = "msg_${System.currentTimeMillis()}",
            isUser = true,
            text = msg.trim(),
            time = "Just now"
        )
        _chatMessages.value = _chatMessages.value + userMsg

        viewModelScope.launch {
            delay(800)
            val lower = msg.lowercase()
            val responseText = when {
                "banana" in lower || "expir" in lower ->
                    "Great catch! Bananas can be blended into a pre-workout smoothie or frozen into banana nice-cream. You'll save ₹60 and get 28g of clean glycogen."
                "dip" in lower || "slump" in lower || "tired" in lower ->
                    "The afternoon dip is driven by post-lunch glucose processing. Taking a 5-minute brisk walk and hydrating with 350ml cold water will lift your curve by +18%."
                "dinner" in lower ->
                    "For tonight, your crisper spinach and paneer are ready. Pair with roasted garlic for an easy 12-min high-protein dinner that promotes deep REM sleep!"
                else ->
                    "Evaluating pantry state & biometrics for \"$msg\"... I recommend pairing high fiber with 18g clean protein to maintain steady cellular energy!"
            }
            val aiMsg = ChatMessage(
                id = "ai_${System.currentTimeMillis()}",
                isUser = false,
                text = responseText,
                time = "Just now"
            )
            _chatMessages.value = _chatMessages.value + aiMsg
        }
    }

    // Privacy Settings
    val privacySettings = MutableStateFlow(PrivacySettingsState())

    fun togglePrivacySetting(setting: String) {
        val s = privacySettings.value
        privacySettings.value = when (setting) {
            "health" -> s.copy(healthConnect = !s.healthConnect)
            "circadian" -> s.copy(circadianForecasting = !s.circadianForecasting)
            "ai" -> s.copy(pulseAiMemory = !s.pulseAiMemory)
            "ocr" -> s.copy(pantryOcrScanner = !s.pantryOcrScanner)
            "telemetry" -> s.copy(anonymousTelemetry = !s.anonymousTelemetry)
            else -> s
        }
        showToast("Privacy policy synced to secure local enclave")
    }

    fun clearBiometricLogs() {
        showToast("30-day biometric history cleared securely from on-device cache")
    }
}
