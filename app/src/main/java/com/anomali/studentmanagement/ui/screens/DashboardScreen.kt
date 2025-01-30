package com.anomali.studentmanagement.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anomali.studentmanagement.core.utils.PreferencesUtils
import com.anomali.studentmanagement.data.model.User
import com.anomali.studentmanagement.data.repository.AuthRepository
import com.anomali.studentmanagement.ui.navigations.BottomNavigation

@Composable
fun DashboardScreen(
    navController: NavController,
    authRepository: AuthRepository,
    token: String,
    context: Context
) {
    val userState = remember { mutableStateOf<User?>(null) }

    LaunchedEffect(token) {
        val user = authRepository.getUserData(token)
        if (user != null) {
            println("User data received: ${user.name}")
        } else {
            println("No user data found.")
        }
        userState.value = user
    }

    val user = userState.value
    val username = user?.name ?: PreferencesUtils.getUserFromPreferences(context)?.name ?: "Pengguna tidak ditemukan"

    Scaffold(
        bottomBar = { BottomNavigation(navController, 0) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selamat datang, $username!",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Semoga harimu menyenangkan 🎉",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
