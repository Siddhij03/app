package com.example

import com.example.ui.NutriPulseViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testInitialViewModelState() {
        val vm = NutriPulseViewModel()
        assertEquals(84, vm.energyPercentage.value)
        assertEquals(1400, vm.currentWaterMl.value)
        assertEquals(78, vm.healthIndex.value)
        assertFalse(vm.isCurveLifted.value)
        assertEquals(4, vm.quests.value.size)
    }

    @Test
    fun testLogWaterGlassIncrements() {
        val vm = NutriPulseViewModel()
        vm.logWaterGlass()
        assertEquals(1650, vm.currentWaterMl.value)
        assertEquals(79, vm.healthIndex.value)
    }

    @Test
    fun testFixEnergyBoostLiftsCurve() {
        val vm = NutriPulseViewModel()
        vm.fixEnergyBoost()
        assertTrue(vm.isCurveLifted.value)
        assertEquals(89, vm.energyPercentage.value)
    }

    @Test
    fun testToggleQuestUpdatesXp() {
        val vm = NutriPulseViewModel()
        val initialXp = vm.currentXp.value
        // Toggle uncompleted quest 2 (+20 XP)
        vm.toggleQuest("quest_2")
        assertEquals(initialXp + 20, vm.currentXp.value)
        assertTrue(vm.quests.value.first { it.id == "quest_2" }.isCompleted)
    }

    @Test
    fun testPantryQuantityUpdate() {
        val vm = NutriPulseViewModel()
        val initialBananas = vm.pantryItems.value.first { it.id == "p1" }.quantity
        vm.updatePantryQuantity("p1", 1)
        assertEquals(initialBananas + 1, vm.pantryItems.value.first { it.id == "p1" }.quantity)
    }

    @Test
    fun testUpdateDetailedProfileDetails() {
        val vm = NutriPulseViewModel()
        vm.updateDetailedProfile(
            name = "Siddhi",
            email = "siddhi@example.com",
            age = 25,
            heightCm = 165,
            weightKg = 58.0f,
            gender = "Female",
            activityLevel = "Active (3-5 workouts/wk)",
            calorieGoal = 2200,
            waterGoalMl = 2400,
            avatarUrl = "https://example.com/avatar.png",
            primaryFocus = "Circadian Energy & Zero-Waste"
        )
        val profile = vm.userProfile.value
        assertEquals("Siddhi", profile.name)
        assertEquals("siddhi@example.com", profile.email)
        assertEquals(25, profile.age)
        assertEquals(165, profile.heightCm)
        assertEquals(58.0f, profile.weightKg)
        assertEquals(2200, profile.calorieGoal)
        assertEquals(2400, profile.waterGoalMl)
    }

    @Test
    fun testLoginOrRegisterUpdatesProfile() {
        val vm = NutriPulseViewModel()
        vm.loginOrRegister(
            name = "Alex Rider",
            email = "alex@nutripulse.ai",
            calorieGoal = 2400,
            waterGoalMl = 2600,
            avatarUrl = "https://example.com/alex.png",
            age = 28,
            heightCm = 178,
            weightKg = 72.5f,
            gender = "Male"
        )
        val profile = vm.userProfile.value
        assertEquals("Alex Rider", profile.name)
        assertEquals("alex@nutripulse.ai", profile.email)
        assertEquals(28, profile.age)
        assertEquals(178, profile.heightCm)
        assertEquals(72.5f, profile.weightKg)
        assertTrue(profile.isLoggedIn)
    }

    @Test
    fun testSetupOnlyOnceAndPersistedForSubsequentLaunches() {
        val prefs = com.example.data.NutriPulsePreferences(null)
        // Initially, setup is NOT completed (first app launch after download)
        assertFalse(prefs.isSetupCompleted())

        // User opens app and completes profile setup with their personal info
        val vmFirstLaunch = NutriPulseViewModel(prefs)
        assertEquals(com.example.model.AppScreen.WELCOME, vmFirstLaunch.currentScreen.value)

        vmFirstLaunch.loginOrRegister(
            name = "Siddhi Jadhav",
            email = "siddhi@example.com",
            calorieGoal = 2100,
            waterGoalMl = 2200,
            avatarUrl = "https://example.com/avatar.png",
            age = 24,
            heightCm = 167,
            weightKg = 59.0f,
            gender = "Female"
        )

        // Setup is now completed and persisted
        assertTrue(prefs.isSetupCompleted())
        val savedProfile = prefs.loadUserProfile()
        assertEquals("Siddhi Jadhav", savedProfile.name)
        assertEquals(24, savedProfile.age)
        assertEquals(167, savedProfile.heightCm)
        assertEquals(59.0f, savedProfile.weightKg)
        assertTrue(savedProfile.isSetupCompleted)

        // Subsequent launch: User opens app again later.
        // It must bypass Welcome & Login and go directly to HOME with their saved details!
        val vmSubsequentLaunch = NutriPulseViewModel(prefs)
        assertEquals(com.example.model.AppScreen.HOME, vmSubsequentLaunch.currentScreen.value)
        assertEquals("Siddhi Jadhav", vmSubsequentLaunch.userProfile.value.name)
        assertEquals(24, vmSubsequentLaunch.userProfile.value.age)
        assertEquals(167, vmSubsequentLaunch.userProfile.value.heightCm)
    }

    @Test
    fun testStreakIncrementsOnlyIfUserUsesAppDaily() {
        val prefs = com.example.data.NutriPulsePreferences(null)
        prefs.resetSimulation()

        // Day 1: First check-in
        val res1 = prefs.checkAndUpdateDailyStreak()
        assertTrue(res1 is com.example.data.StreakResult.StartedNew)
        assertEquals(1, (res1 as com.example.data.StreakResult.StartedNew).streakDays)

        // Day 1 again: Re-opening the app on the SAME day should NOT increment streak
        val resSameDay = prefs.checkAndUpdateDailyStreak()
        assertTrue(resSameDay is com.example.data.StreakResult.MaintainedToday)
        assertEquals(1, (resSameDay as com.example.data.StreakResult.MaintainedToday).streakDays)

        // Day 2 (Consecutive daily usage!): Streak increments by +1
        val res2 = prefs.simulateAdvanceDay()
        assertTrue(res2 is com.example.data.StreakResult.Incremented)
        assertEquals(2, (res2 as com.example.data.StreakResult.Incremented).streakDays)

        // Day 3 (Consecutive daily usage!): Streak increments to 3
        val res3 = prefs.simulateAdvanceDay()
        assertTrue(res3 is com.example.data.StreakResult.Incremented)
        assertEquals(3, (res3 as com.example.data.StreakResult.Incremented).streakDays)

        // User misses a day! (Skips 2 days ahead, e.g. Day 5)
        // Since streak is added IF AND ONLY IF used daily, missing a day resets it!
        val resMissed = prefs.simulateSkipDays(2)
        assertTrue(resMissed is com.example.data.StreakResult.ResetDueToMissedDay)
        assertEquals(1, (resMissed as com.example.data.StreakResult.ResetDueToMissedDay).streakDays)
    }

    @Test
    fun testShareAppUrlConfiguration() {
        val url = com.example.util.AppShareConfig.SHARED_APP_URL
        assertEquals("https://ais-pre-lvv5enkatg76ixxbbpze7i-371484522525.asia-southeast1.run.app", url)

        val message = com.example.util.AppShareConfig.getShareMessage("Siddhi")
        assertTrue(message.contains(url))
        assertTrue(message.contains("Shared by Siddhi"))
        assertTrue(message.contains("NutriPulse"))
    }
}
