package com.anomali.studentmanagement.ui.screens.students

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.anomali.studentmanagement.ui.navigations.BottomNavigation

@Composable
fun StudentListScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigation(navController, 1) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) { }
    }
}