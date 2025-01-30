package com.anomali.studentmanagement.core.routes

sealed class AppRoutes(val route: String) {

    object LoginScreen : AppRoutes("login")
    object RegisterScreen : AppRoutes("register")
    object LogoutScreen : AppRoutes("logout")

    object DashboardScreen : AppRoutes("dashboard")
    object ProfileScreen : AppRoutes("profile")
    object StudentListScreen : AppRoutes("students")

    object CreateStudentScreen : AppRoutes("students/create")
    object StudentDetailScreen : AppRoutes("students/{studentId}") {
        fun createRoute(studentId: String) = "students/$studentId"
    }
    object EditStudentScreen : AppRoutes("students/edit/{studentId}?isEdit={isEdit}") {
        fun createRoute(studentId: String, isEdit: Boolean = true) = "students/edit/$studentId?isEdit=$isEdit"
    }
}