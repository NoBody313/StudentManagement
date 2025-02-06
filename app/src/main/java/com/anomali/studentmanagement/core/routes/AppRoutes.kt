package com.anomali.studentmanagement.core.routes

sealed class AppRoutes(val route: String) {
    object LoginScreen : AppRoutes("login")
    object RegisterScreen : AppRoutes("register")
    object LogoutScreen : AppRoutes("logout")
    object DashboardScreen : AppRoutes("dashboard")
    object StudentListScreen : AppRoutes("students")
    object ProfileScreen : AppRoutes("profile")
    object StudentDetailScreen : AppRoutes("student/{studentId}") {
        fun createRoute(studentId: String) = "student/$studentId"
    }

    object CreateStudentScreen : AppRoutes("create_student")
    object EditStudentScreen : AppRoutes("edit_student/{studentId}/{isEdit}") {
        fun createRoute(studentId: String, isEdit: Boolean) = "edit_student/$studentId/$isEdit"
    }

    object FavoriteListScreen : AppRoutes("favorite") // Rute baru untuk FavoriteListScreen
}