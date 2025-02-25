package com.anomali.studentmanagement.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anomali.studentmanagement.data.model.User
import com.anomali.studentmanagement.data.repository.admin.AuthRepository
import com.anomali.studentmanagement.ui.navigations.BottomNavigation
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController,
    authRepository: AuthRepository,
    token: String,
    onLoggedOut: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavigation(navController, 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile Information",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            val user = remember { mutableStateOf<User?>(null) }

            LaunchedEffect(token) {
                user.value = authRepository.getUserData(token)
            }

            if (user.value != null) {
                Text(
                    text = "Name: ${user.value!!.name}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Email: ${user.value!!.email}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            } else {
                Text(
                    text = "Data user tidak tersedia.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            authRepository.logout(
                                token = token
                            )
                            onLoggedOut()
                        } catch (e: Exception) {
                            println("Logout failed: ${e.message}")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
