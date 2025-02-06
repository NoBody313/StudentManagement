package com.anomali.studentmanagement.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anomali.studentmanagement.core.routes.AppRoutes
import com.anomali.studentmanagement.core.utils.PreferencesUtils
import com.anomali.studentmanagement.core.utils.PreferencesUtils.getTokenFromPreferences
import com.anomali.studentmanagement.data.repository.AuthRepositoryImpl
import com.anomali.studentmanagement.data.repository.StudentRepositoryImpl
import com.anomali.studentmanagement.ui.screens.DashboardScreen
import com.anomali.studentmanagement.ui.screens.ProfileScreen
import com.anomali.studentmanagement.ui.screens.auth.LoginScreen
import com.anomali.studentmanagement.ui.screens.auth.RegisterScreen
import com.anomali.studentmanagement.ui.screens.students.CreateEditStudentScreen
import com.anomali.studentmanagement.ui.screens.students.StudentDetailScreen
import com.anomali.studentmanagement.ui.screens.students.StudentListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val token = getTokenFromPreferences(context)
    val authRepository = AuthRepositoryImpl(context = context)
    val studentRepository = StudentRepositoryImpl(context = context)

    NavHost(
        navController,
        startDestination = if (token.isNotEmpty()) AppRoutes.DashboardScreen.route else AppRoutes.LoginScreen.route
    ) {
        composable(AppRoutes.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.DashboardScreen.route) {
                        popUpTo(AppRoutes.LoginScreen.route) { inclusive = true }
                    }
                },
                navController = navController
            )
        }
        composable(AppRoutes.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                authRepository = AuthRepositoryImpl(context)
            )
        }
        composable(AppRoutes.LogoutScreen.route) {
            PreferencesUtils.clearTokenFromPreferences(context)
            navController.navigate(AppRoutes.LoginScreen.route)
        }

        composable(AppRoutes.DashboardScreen.route) {
            DashboardScreen(
                navController = navController,
                authRepository = authRepository,
                context = context,
                token = token
            )
        }

        composable(AppRoutes.StudentListScreen.route) {
            StudentListScreen(
                navController = navController,
                studentRepository = studentRepository
            )
        }
        composable(AppRoutes.ProfileScreen.route) {
            ProfileScreen(
                navController,
                authRepository = authRepository,
                token = token,
                onLoggedOut = {
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                },
            )
        }
        composable(AppRoutes.StudentDetailScreen.route) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")
            if (studentId != null) {
                StudentDetailScreen(navController, studentId)
            } else {
                navController.popBackStack()
            }
        }

        composable(
            route = AppRoutes.EditStudentScreen.route,
            arguments = listOf(
                navArgument("studentId") { type = NavType.StringType },
                navArgument("isEdit") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")
            val isEdit = backStackEntry.arguments?.getBoolean("isEdit") ?: true
            if (studentId != null) {
                CreateEditStudentScreen(navController, isEdit = isEdit, studentId = studentId)
            } else {
                // Handle case when studentId is null, e.g., navigate back or show an error
                navController.popBackStack()
            }
        }

        composable(AppRoutes.CreateStudentScreen.route) {
            CreateEditStudentScreen(navController, isEdit = false, studentId = null)
        }

        composable(
            route = AppRoutes.EditStudentScreen.route,
            arguments = listOf(
                navArgument("studentId") { type = NavType.StringType },
                navArgument("isEdit") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")
            val isEdit = backStackEntry.arguments?.getBoolean("isEdit") ?: true
            if (studentId != null) {
                CreateEditStudentScreen(navController, isEdit = isEdit, studentId = studentId)
            } else {
                // Handle case when studentId is null, e.g., navigate back or show an error
                navController.popBackStack()
            }
        }
    }
}