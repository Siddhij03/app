package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.UserProfile
import java.util.Calendar

sealed class StreakResult {
    data class StartedNew(val streakDays: Int) : StreakResult()
    data class MaintainedToday(val streakDays: Int) : StreakResult()
    data class Incremented(val streakDays: Int, val previousStreak: Int) : StreakResult()
    data class ResetDueToMissedDay(val streakDays: Int, val previousStreak: Int) : StreakResult()
}

class NutriPulsePreferences(context: Context?) {

    private val prefs: SharedPreferences? = context?.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    // In-memory fallback if Context is null (e.g., pure unit tests without Android Context)
    private val inMemoryMap = mutableMapOf<String, Any?>()

    companion object {
        const val PREFS_NAME = "nutripulse_user_preferences"

        private const val KEY_SETUP_COMPLETED = "key_setup_completed"
        private const val KEY_IS_LOGGED_IN = "key_is_logged_in"
        private const val KEY_NAME = "key_name"
        private const val KEY_EMAIL = "key_email"
        private const val KEY_AGE = "key_age"
        private const val KEY_HEIGHT = "key_height"
        private const val KEY_WEIGHT = "key_weight"
        private const val KEY_GENDER = "key_gender"
        private const val KEY_ACTIVITY_LEVEL = "key_activity_level"
        private const val KEY_AVATAR_URL = "key_avatar_url"
        private const val KEY_CALORIE_GOAL = "key_calorie_goal"
        private const val KEY_WATER_GOAL = "key_water_goal"
        private const val KEY_USER_LEVEL = "key_user_level"
        private const val KEY_STREAK_DAYS = "key_streak_days"
        private const val KEY_PRIMARY_FOCUS = "key_primary_focus"
        private const val KEY_LAST_ACTIVE_EPOCH_DAY = "key_last_active_epoch_day"
        private const val KEY_SIMULATED_DAY_OFFSET = "key_simulated_day_offset"
    }

    fun isSetupCompleted(): Boolean {
        return if (prefs != null) {
            prefs.getBoolean(KEY_SETUP_COMPLETED, false)
        } else {
            (inMemoryMap[KEY_SETUP_COMPLETED] as? Boolean) ?: false
        }
    }

    fun saveUserProfile(profile: UserProfile) {
        if (prefs != null) {
            prefs.edit()
                .putBoolean(KEY_SETUP_COMPLETED, profile.isSetupCompleted)
                .putBoolean(KEY_IS_LOGGED_IN, profile.isLoggedIn)
                .putString(KEY_NAME, profile.name)
                .putString(KEY_EMAIL, profile.email)
                .putInt(KEY_AGE, profile.age)
                .putInt(KEY_HEIGHT, profile.heightCm)
                .putFloat(KEY_WEIGHT, profile.weightKg)
                .putString(KEY_GENDER, profile.gender)
                .putString(KEY_ACTIVITY_LEVEL, profile.activityLevel)
                .putString(KEY_AVATAR_URL, profile.avatarUrl)
                .putInt(KEY_CALORIE_GOAL, profile.calorieGoal)
                .putInt(KEY_WATER_GOAL, profile.waterGoalMl)
                .putString(KEY_USER_LEVEL, profile.userLevel)
                .putInt(KEY_STREAK_DAYS, profile.streakDays)
                .putString(KEY_PRIMARY_FOCUS, profile.primaryFocus)
                .putLong(KEY_LAST_ACTIVE_EPOCH_DAY, profile.lastActiveEpochDay)
                .apply()
        } else {
            inMemoryMap[KEY_SETUP_COMPLETED] = profile.isSetupCompleted
            inMemoryMap[KEY_IS_LOGGED_IN] = profile.isLoggedIn
            inMemoryMap[KEY_NAME] = profile.name
            inMemoryMap[KEY_EMAIL] = profile.email
            inMemoryMap[KEY_AGE] = profile.age
            inMemoryMap[KEY_HEIGHT] = profile.heightCm
            inMemoryMap[KEY_WEIGHT] = profile.weightKg
            inMemoryMap[KEY_GENDER] = profile.gender
            inMemoryMap[KEY_ACTIVITY_LEVEL] = profile.activityLevel
            inMemoryMap[KEY_AVATAR_URL] = profile.avatarUrl
            inMemoryMap[KEY_CALORIE_GOAL] = profile.calorieGoal
            inMemoryMap[KEY_WATER_GOAL] = profile.waterGoalMl
            inMemoryMap[KEY_USER_LEVEL] = profile.userLevel
            inMemoryMap[KEY_STREAK_DAYS] = profile.streakDays
            inMemoryMap[KEY_PRIMARY_FOCUS] = profile.primaryFocus
            inMemoryMap[KEY_LAST_ACTIVE_EPOCH_DAY] = profile.lastActiveEpochDay
        }
    }

    fun loadUserProfile(): UserProfile {
        return if (prefs != null) {
            val setupDone = prefs.getBoolean(KEY_SETUP_COMPLETED, false)
            val defaultName = if (setupDone) "Explorer" else "Maya"
            val defaultEmail = if (setupDone) "user@nutripulse.ai" else "maya@nutripulse.ai"

            UserProfile(
                name = prefs.getString(KEY_NAME, defaultName) ?: defaultName,
                email = prefs.getString(KEY_EMAIL, defaultEmail) ?: defaultEmail,
                age = prefs.getInt(KEY_AGE, 23),
                heightCm = prefs.getInt(KEY_HEIGHT, 168),
                weightKg = prefs.getFloat(KEY_WEIGHT, 60.5f),
                gender = prefs.getString(KEY_GENDER, "Female") ?: "Female",
                activityLevel = prefs.getString(KEY_ACTIVITY_LEVEL, "Active (3-5 workouts/wk)") ?: "Active (3-5 workouts/wk)",
                avatarUrl = prefs.getString(
                    KEY_AVATAR_URL,
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw"
                ) ?: "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw",
                calorieGoal = prefs.getInt(KEY_CALORIE_GOAL, 2100),
                waterGoalMl = prefs.getInt(KEY_WATER_GOAL, 2200),
                userLevel = prefs.getString(KEY_USER_LEVEL, "Level 3: Energy Pro") ?: "Level 3: Energy Pro",
                streakDays = prefs.getInt(KEY_STREAK_DAYS, 1),
                primaryFocus = prefs.getString(KEY_PRIMARY_FOCUS, "Circadian Energy & Zero-Waste") ?: "Circadian Energy & Zero-Waste",
                isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, setupDone),
                isSetupCompleted = setupDone,
                lastActiveEpochDay = prefs.getLong(KEY_LAST_ACTIVE_EPOCH_DAY, -1L)
            )
        } else {
            val setupDone = (inMemoryMap[KEY_SETUP_COMPLETED] as? Boolean) ?: false
            UserProfile(
                name = (inMemoryMap[KEY_NAME] as? String) ?: if (setupDone) "Explorer" else "Maya",
                email = (inMemoryMap[KEY_EMAIL] as? String) ?: if (setupDone) "user@nutripulse.ai" else "maya@nutripulse.ai",
                age = (inMemoryMap[KEY_AGE] as? Int) ?: 23,
                heightCm = (inMemoryMap[KEY_HEIGHT] as? Int) ?: 168,
                weightKg = (inMemoryMap[KEY_WEIGHT] as? Float) ?: 60.5f,
                gender = (inMemoryMap[KEY_GENDER] as? String) ?: "Female",
                activityLevel = (inMemoryMap[KEY_ACTIVITY_LEVEL] as? String) ?: "Active (3-5 workouts/wk)",
                avatarUrl = (inMemoryMap[KEY_AVATAR_URL] as? String) ?: "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw",
                calorieGoal = (inMemoryMap[KEY_CALORIE_GOAL] as? Int) ?: 2100,
                waterGoalMl = (inMemoryMap[KEY_WATER_GOAL] as? Int) ?: 2200,
                userLevel = (inMemoryMap[KEY_USER_LEVEL] as? String) ?: "Level 3: Energy Pro",
                streakDays = (inMemoryMap[KEY_STREAK_DAYS] as? Int) ?: 1,
                primaryFocus = (inMemoryMap[KEY_PRIMARY_FOCUS] as? String) ?: "Circadian Energy & Zero-Waste",
                isLoggedIn = (inMemoryMap[KEY_IS_LOGGED_IN] as? Boolean) ?: setupDone,
                isSetupCompleted = setupDone,
                lastActiveEpochDay = (inMemoryMap[KEY_LAST_ACTIVE_EPOCH_DAY] as? Long) ?: -1L
            )
        }
    }

    /**
     * Streak algorithm:
     * Streaks increment daily if and only if the user uses the app every consecutive calendar day.
     * - If lastActiveEpochDay == today: user already checked in today -> streak unchanged.
     * - If lastActiveEpochDay == today - 1: user used app yesterday -> streak increments by +1!
     * - If lastActiveEpochDay < today - 1: user missed 1 or more days -> streak resets to 1.
     * - If lastActiveEpochDay == -1: first day -> streak is 1.
     */
    fun checkAndUpdateDailyStreak(): StreakResult {
        val today = getTodayEpochDay()
        val currentProfile = loadUserProfile()
        val lastDay = currentProfile.lastActiveEpochDay
        val currentStreak = currentProfile.streakDays.coerceAtLeast(1)

        val result: StreakResult
        val newStreak: Int

        when {
            lastDay == -1L -> {
                // First session
                newStreak = 1
                result = StreakResult.StartedNew(newStreak)
            }
            lastDay == today -> {
                // Already checked in today
                newStreak = currentStreak
                result = StreakResult.MaintainedToday(newStreak)
            }
            lastDay == today - 1L -> {
                // Consecutive daily usage!
                newStreak = currentStreak + 1
                result = StreakResult.Incremented(streakDays = newStreak, previousStreak = currentStreak)
            }
            else -> {
                // Missed one or more days! Streak resets to 1
                newStreak = 1
                result = StreakResult.ResetDueToMissedDay(streakDays = newStreak, previousStreak = currentStreak)
            }
        }

        val updatedProfile = currentProfile.copy(
            streakDays = newStreak,
            lastActiveEpochDay = today
        )
        saveUserProfile(updatedProfile)

        return result
    }

    fun getTodayEpochDay(): Long {
        val cal = Calendar.getInstance()
        val offset = getSimulatedDayOffset()
        if (offset != 0) {
            cal.add(Calendar.DAY_OF_YEAR, offset)
        }
        val millis = cal.timeInMillis + cal.timeZone.getOffset(cal.timeInMillis)
        return millis / (24 * 60 * 60 * 1000L)
    }

    fun getSimulatedDayOffset(): Int {
        return if (prefs != null) {
            prefs.getInt(KEY_SIMULATED_DAY_OFFSET, 0)
        } else {
            (inMemoryMap[KEY_SIMULATED_DAY_OFFSET] as? Int) ?: 0
        }
    }

    fun setSimulatedDayOffset(offset: Int) {
        if (prefs != null) {
            prefs.edit().putInt(KEY_SIMULATED_DAY_OFFSET, offset).apply()
        } else {
            inMemoryMap[KEY_SIMULATED_DAY_OFFSET] = offset
        }
    }

    fun simulateAdvanceDay(): StreakResult {
        val nextOffset = getSimulatedDayOffset() + 1
        setSimulatedDayOffset(nextOffset)
        return checkAndUpdateDailyStreak()
    }

    fun simulateSkipDays(daysToSkip: Int = 2): StreakResult {
        val nextOffset = getSimulatedDayOffset() + daysToSkip
        setSimulatedDayOffset(nextOffset)
        return checkAndUpdateDailyStreak()
    }

    fun resetSimulation() {
        setSimulatedDayOffset(0)
    }

    fun clearAllData() {
        if (prefs != null) {
            prefs.edit().clear().apply()
        } else {
            inMemoryMap.clear()
        }
    }
}
