package com.anomali.studentmanagement.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anomali.studentmanagement.R
import com.anomali.studentmanagement.core.routes.AppRoutes

@Composable
fun BottomNavigation(navController: NavController, pages: Int = 0) {
    val menuItems = listOf(
        Triple(
            AppRoutes.AdminDashboardScreen,
            R.drawable.home_on,
            R.drawable.home_off
        ) to "Dashboard",
        Triple(
            AppRoutes.ManagementScreen,
            R.drawable.management_on,
            R.drawable.management_off
        ) to "Manajemen",
        Triple(
            AppRoutes.FavoriteListScreen,
            R.drawable.add_on,
            R.drawable.add_off
        ) to "Tambah Cepat",
        Triple(
            AppRoutes.ManagementScheduleScreen,
            R.drawable.calendar_on,
            R.drawable.calendar_off
        ) to "Jadwal",
        Triple(
            AppRoutes.LogoutScreen,
            R.drawable.settings_on,
            R.drawable.settings_off
        ) to "Pengaturan"
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
                        painter = painterResource(id = if (selected) fillIcon else outlineIcon),
                        contentDescription = label
                    )
                },
                selected = selected,
                label = { Text(label, fontSize = 9.sp) },
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