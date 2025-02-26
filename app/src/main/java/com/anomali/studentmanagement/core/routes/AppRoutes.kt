package com.anomali.studentmanagement.core.routes

sealed class AppRoutes(val route: String) {
    object LoginScreen : AppRoutes("login")
    object RegisterScreen : AppRoutes("register")
    object LogoutScreen : AppRoutes("logout")
//    object DashboardScreen : AppRoutes("dashboard")
    object StudentListScreen : AppRoutes("students")
    object ProfileScreen : AppRoutes("profile")
    object StudentDetailScreen : AppRoutes("student/{studentId}") {
        fun createRoute(studentId: String) = "student/$studentId"
    }

//    object CreateStudentScreen : AppRoutes("create_student")
    object EditStudentScreen : AppRoutes("edit_student/{studentId}/{isEdit}") {
        fun createRoute(studentId: String, isEdit: Boolean) = "edit_student/$studentId/$isEdit"
    }

    object FavoriteListScreen : AppRoutes("favorite")

    object AdminDashboardScreen : AppRoutes("admin/dashboard")
    // Management
    object ManagementScreen : AppRoutes("admin/management")
    object ManagementGuruScreen : AppRoutes("admin/management/guru")
    object ManagementSiswaScreen : AppRoutes("admin/management/siswa")
    object ManagementKelasScreen : AppRoutes("admin/management/kelas")
    object ManagementSubjectScreen : AppRoutes("admin/management/subject")
    // Create
    object CreateGuruScreen : AppRoutes("admin/management/guru/create")
    object CreateStudentScreen : AppRoutes("admin/management/siswa/create")
    object CreateKelasScreen : AppRoutes("admin/management/kelas/create")
    object CreateSubjectScreen : AppRoutes("admin/management/subject/create")
    // Detail
    object DetailGuruScreen : AppRoutes("admin/management/guru/detail/{teacherId}") {
        fun teacherCreateRoute(teacherId: Int) = "admin/management/guru/detail/$teacherId"
    }
    object DetailSiswaScreen : AppRoutes("admin/management/siswa/detail/{studentId}") {
        fun siswaCreateRoute(studentId: Int) = "admin/management/siswa/detail/$studentId"
    }
    object DetailKelasScreen : AppRoutes("admin/management/kelas/detail/{classesId}") {
        fun classesCreateRoute(classesId: Int) = "admin/management/kelas/detail/$classesId"
    }
    object DetailSubjectScreen : AppRoutes("admin/management/subject/detail/{subjectId}") {
        fun subjectCreateRoute(subjectId: Int) = "admin/management/subject/detail/$subjectId"
    }

}