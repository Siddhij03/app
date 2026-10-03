package com.example.model

enum class AppScreen {
    WELCOME,
    LOGIN,
    HOME,
    MEALS,
    ENERGY,
    KITCHEN,
    PROFILE,
    PRIVACY_VAULT,
    NOTIFICATION_SETTINGS
}

data class UserProfile(
    val name: String = "Maya",
    val email: String = "maya@nutripulse.ai",
    val age: Int = 23,
    val heightCm: Int = 168,
    val weightKg: Float = 60.5f,
    val gender: String = "Female",
    val activityLevel: String = "Active (3-5 workouts/wk)",
    val avatarUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw",
    val calorieGoal: Int = 2100,
    val waterGoalMl: Int = 2200,
    val userLevel: String = "Level 3: Energy Pro",
    val streakDays: Int = 5,
    val primaryFocus: String = "Circadian Energy & Zero-Waste",
    val isLoggedIn: Boolean = true,
    val isSetupCompleted: Boolean = false,
    val lastActiveEpochDay: Long = -1L
)

data class PresetAvatar(
    val id: String,
    val name: String,
    val url: String
)

data class DailyQuest(
    val id: String,
    val title: String,
    val subtitle: String,
    val xp: Int,
    val isCompleted: Boolean
)

data class PantryItem(
    val id: String,
    val name: String,
    val emoji: String,
    val expiryText: String,
    val location: String,
    val quantity: Int,
    val unit: String = "",
    val isUrgent: Boolean = false,
    val daysLeft: Int = 1
)

data class Recipe(
    val id: String,
    val title: String,
    val subtitle: String,
    val readyMinutes: Int,
    val calories: Int,
    val proteinGrams: Int,
    val isInStock: Boolean,
    val wasteSaverLabel: String?,
    val usesIngredients: String,
    val imageUrl: String,
    val staminaBoost: String,
    val benefitHighlight: String,
    val missingItemNotice: String? = null
)

data class ChatMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val time: String,
    val recipeCard: Recipe? = null,
    val showDiagnosticGraph: Boolean = false,
    val actionText: String? = null
)

data class TimeTelemetryNode(
    val timeLabel: String,
    val fullTime: String,
    val percentage: Int,
    val status: String,
    val circadianPhase: String,
    val digestionState: String,
    val caffeineDecay: String,
    val hydrationDelta: String
)

data class RoutineScheduleItem(
    val time: String,
    val amPm: String,
    val title: String,
    val subtitle: String,
    val tag: String?,
    val isSlumpDefense: Boolean = false
)

data class PrivacySettingsState(
    val healthConnect: Boolean = true,
    val circadianForecasting: Boolean = true,
    val pulseAiMemory: Boolean = true,
    val pantryOcrScanner: Boolean = true,
    val anonymousTelemetry: Boolean = false
)
