package com.anomali.studentmanagement.ui.screens.admin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anomali.studentmanagement.R
import com.anomali.studentmanagement.core.routes.AppRoutes
import com.anomali.studentmanagement.core.utils.PreferencesUtils
import com.anomali.studentmanagement.data.model.User
import com.anomali.studentmanagement.data.repository.auth.AuthRepository
import com.anomali.studentmanagement.data.repository.auth.AuthRepositoryImpl
import com.anomali.studentmanagement.ui.navigations.BottomNavigation
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Composable
fun DashboardScreen(
    navController: NavController,
    authRepository: AuthRepository,
    token: String,
    context: Context
) {
    val userState = remember { mutableStateOf<User?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    val handleTokenExpired: () -> Unit = {
        PreferencesUtils.clearTokenFromPreferences(context)
        Toast.makeText(context, "Session expired. Redirecting to Login...", Toast.LENGTH_SHORT)
            .show()
        navController.navigate(AppRoutes.LoginScreen.route) {
            popUpTo(AppRoutes.AdminDashboardScreen.route) { inclusive = true }
        }
    }

    LaunchedEffect(token) {
        try {
            val user = authRepository.getUserData(token)
            if (user != null) {
                println("User data received: ${user.name}")
                userState.value = user
            } else {
                handleTokenExpired()
            }
        } catch (e: Exception) {
            errorMessage = "Error fetching user data: ${e.localizedMessage}"
            if (e is AuthRepositoryImpl.UnauthorizedException) {
                handleTokenExpired()
            }
        }
    }

    val user = userState.value
    val username = user?.name ?: PreferencesUtils.getUserFromPreferences(context)?.name
    ?: "Pengguna tidak ditemukan"

    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(42.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(paddingValues)
                .padding(32.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Welcome to Student Management!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1E3A8A),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "$username 🎉🎉🎉",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF1E3A8A),
                            textAlign = TextAlign.Center,
                        )
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = "image description",
                    tint = Color(0xFF1E3A8A),
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Statistik Data Sekolah",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF1E3A8A),
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Example of the existing statistic cards
                    StatisticCard("3102", "Jumlah Siswa", Color(0xFFBBF7D0), Color(0xFF14532D))
                    StatisticCard("300", "Jumlah Guru", Color(0xFFFEF08A), Color(0xFF713F12))
                    StatisticCard("200", "Jumlah Kelas", Color(0xFFC7D2FE), Color(0xFF312E81))
                }

                // Crash Button
                Button(
                    onClick = {
                        // Trigger the crash
                        FirebaseCrashlytics.getInstance().log("Test Crash Button clicked")
                        throw RuntimeException("Test Crash") // Force a crash
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Test Crash")
                }
            }
        }
    }

    if (errorMessage.isNotEmpty()) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun StatisticCard(value: String, label: String, backgroundColor: Color, textColor: Color) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = value,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = textColor,
                textAlign = TextAlign.Center,
            )
        )

        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = textColor,
                textAlign = TextAlign.Center,
            )
        )
    }
}
