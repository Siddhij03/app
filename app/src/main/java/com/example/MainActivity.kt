package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppScreen
import com.example.ui.NutriPulseViewModel
import com.example.ui.components.NutriPulseBottomNav
import com.example.ui.components.NutriPulseTopBar
import com.example.ui.screens.AssistantScreen
import com.example.ui.screens.EnergyForecastScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.KitchenChefScreen
import com.example.ui.screens.LoginProfileScreen
import com.example.ui.screens.NotificationSetupScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.PrivacyVaultScreen
import com.example.ui.screens.WelcomeLaunchScreen
import com.example.ui.theme.NutriPulseTheme
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.PrimaryEmerald
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TextPrimary

class MainActivity : ComponentActivity() {
    private val viewModel: NutriPulseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initPreferences(this)
        enableEdgeToEdge()
        setContent {
            NutriPulseTheme {
                NutriPulseApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NutriPulseApp(viewModel: NutriPulseViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()

    // Android back button handling
    BackHandler(enabled = currentScreen != AppScreen.HOME && currentScreen != AppScreen.WELCOME) {
        viewModel.navigateBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBase)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = ObsidianBase,
            topBar = {
                if (currentScreen != AppScreen.WELCOME && currentScreen != AppScreen.LOGIN) {
                    NutriPulseTopBar(
                        currentScreen = currentScreen,
                        userProfile = userProfile,
                        onAvatarClick = { viewModel.navigateTo(AppScreen.PROFILE) },
                        modifier = Modifier.statusBarsPadding()
                    )
                }
            },
            bottomBar = {
                if (currentScreen != AppScreen.WELCOME &&
                    currentScreen != AppScreen.LOGIN &&
                    currentScreen != AppScreen.PROFILE &&
                    currentScreen != AppScreen.PRIVACY_VAULT &&
                    currentScreen != AppScreen.NOTIFICATION_SETTINGS
                ) {
                    NutriPulseBottomNav(
                        currentScreen = currentScreen,
                        onTabSelected = { screen -> viewModel.navigateTo(screen) }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentScreen) {
                    AppScreen.WELCOME -> {
                        WelcomeLaunchScreen(
                            onOpenLogin = { viewModel.navigateTo(AppScreen.LOGIN) },
                            onLaunchDemo = {
                                viewModel.loginOrRegister(
                                    name = "Maya",
                                    email = "maya@nutripulse.ai",
                                    calorieGoal = 2100,
                                    waterGoalMl = 2200,
                                    avatarUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBCGrXBvtYz6w17RZ2_Fu-SffHzdS_N2LouOJQjz2AjLEEmA6uHs0qu3-49lJRBzxztcksXqOkyUD1reXS6-Zs-k9Hv7ZQNw6A0Zjq9A-KONiTw48BydI-dRHC0_qzrhDtklIaEw4dpg3DUoO5xU0WwQsyKeEQ7phMs6YZeTxcjJX9_pA--888_WdHKVkdKoH_VtC2A8makMNFwKnS5lcDsG4BCY-gefCgpwriurP9WVHqrRcpWFB2Muw"
                                )
                            }
                        )
                    }

                    AppScreen.LOGIN -> {
                        LoginProfileScreen(
                            viewModel = viewModel,
                            onBack = { viewModel.navigateBack() }
                        )
                    }

                    AppScreen.PROFILE -> {
                        ProfileScreen(
                            viewModel = viewModel,
                            onBack = { viewModel.navigateBack() },
                            onOpenPrivacyVault = { viewModel.navigateTo(AppScreen.PRIVACY_VAULT) }
                        )
                    }

                    AppScreen.HOME -> {
                        HomeScreen(
                            viewModel = viewModel,
                            onNavigateToKitchen = { viewModel.navigateTo(AppScreen.KITCHEN) }
                        )
                    }

                    AppScreen.MEALS -> {
                        AssistantScreen(viewModel = viewModel)
                    }

                    AppScreen.ENERGY -> {
                        EnergyForecastScreen(viewModel = viewModel)
                    }

                    AppScreen.KITCHEN -> {
                        KitchenChefScreen(viewModel = viewModel)
                    }

                    AppScreen.PRIVACY_VAULT -> {
                        PrivacyVaultScreen(
                            viewModel = viewModel,
                            onBack = { viewModel.navigateBack() },
                            onOpenNotificationSetup = { viewModel.navigateTo(AppScreen.NOTIFICATION_SETTINGS) }
                        )
                    }

                    AppScreen.NOTIFICATION_SETTINGS -> {
                        NotificationSetupScreen(
                            viewModel = viewModel,
                            onBack = { viewModel.navigateBack() }
                        )
                    }
                }
            }
        }

        // Cyber-Telemetry Floating Toast Notification
        AnimatedVisibility(
            visible = toastMessage != null,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 16.dp, start = 20.dp, end = 20.dp)
        ) {
            toastMessage?.let { msg ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(SurfaceContainerHighest)
                        .border(1.dp, PrimaryEmerald.copy(alpha = 0.4f), RoundedCornerShape(50))
                        .shadow(16.dp, RoundedCornerShape(50), spotColor = PrimaryEmerald)
                        .clickable { viewModel.dismissToast() }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(PrimaryEmerald, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = OnPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = msg,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}
