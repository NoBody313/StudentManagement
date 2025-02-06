package com.anomali.studentmanagement.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.anomali.studentmanagement.core.routes.AppRoutes

@Composable
fun BottomNavigation(navController: NavController, pages: Int = 0) {
    val menuItems = listOf(
        Triple(
            AppRoutes.DashboardScreen,
            Icons.Filled.Home,
            Icons.Outlined.Home
        ) to "Dashboard",
        Triple(
            AppRoutes.StudentListScreen,
            Icons.AutoMirrored.Filled.List,
            Icons.AutoMirrored.Outlined.List
        ) to "Students",
        Triple(
            AppRoutes.FavoriteListScreen,
            Icons.Filled.Favorite,
            Icons.Outlined.Favorite
        ) to "Favorite",
        Triple(
            AppRoutes.ProfileScreen,
            Icons.Filled.Person,
            Icons.Outlined.Person
        ) to "Profile"
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        menuItems.forEachIndexed { index, item ->
            val (routeIcon, label) = item
            val (route, fillIcon, outlineIcon) = routeIcon
            val selected = pages == index

            NavigationBarItem(
                icon = {
                    Icon(
                        if (selected) fillIcon else outlineIcon,
                        contentDescription = label
                    )
                },
                selected = selected,
                label = { Text(label) },
                alwaysShowLabel = true,
                colors = NavigationBarItemColors(
                    selectedIconColor = Color(0xFF2563EB),
                    selectedTextColor = Color(0xFF2563EB),
                    selectedIndicatorColor = Color.Unspecified,
                    unselectedIconColor = Color(0xFF2563EB),
                    unselectedTextColor = Color(0xFF2563EB),
                    disabledIconColor = Color.Unspecified,
                    disabledTextColor = Color.Unspecified
                ),
                onClick = {
                    navController.navigate(route.route) { launchSingleTop = true }
                },
            )
        }
    }
}