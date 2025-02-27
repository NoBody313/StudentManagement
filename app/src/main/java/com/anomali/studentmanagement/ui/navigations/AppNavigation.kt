package com.anomali.studentmanagement.ui.navigations

//import com.anomali.studentmanagement.ui.screens.favorites.FavoriteListScreen
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
import com.anomali.studentmanagement.data.local.database.StudentDatabase
import com.anomali.studentmanagement.data.repository.admin.AuthRepositoryImpl
import com.anomali.studentmanagement.data.repository.admin.ClassesRepositoryImpl
import com.anomali.studentmanagement.data.repository.admin.ScheduleRepositoryImpl
import com.anomali.studentmanagement.data.repository.admin.StudentRepositoryImpl
import com.anomali.studentmanagement.data.repository.admin.SubjectRepositoryImpl
import com.anomali.studentmanagement.data.repository.admin.TeacherRepositoryImpl
import com.anomali.studentmanagement.data.repository.teacher.GradeRepositoryImpl
import com.anomali.studentmanagement.ui.screens.ProfileScreen
import com.anomali.studentmanagement.ui.screens.admin.DashboardScreen
import com.anomali.studentmanagement.ui.screens.admin.ManagementScreen
import com.anomali.studentmanagement.ui.screens.admin.management.classes.ClassesScreen
import com.anomali.studentmanagement.ui.screens.admin.management.classes.CreateKelasScreen
import com.anomali.studentmanagement.ui.screens.admin.management.classes.DetailClassesScreen
import com.anomali.studentmanagement.ui.screens.admin.management.student.CreateStudentScreen
import com.anomali.studentmanagement.ui.screens.admin.management.student.DetailStudentScreen
import com.anomali.studentmanagement.ui.screens.admin.management.student.StudentScreen
import com.anomali.studentmanagement.ui.screens.admin.management.subject.CreateSubjectScreen
import com.anomali.studentmanagement.ui.screens.admin.management.subject.DetailSubjectScreen
import com.anomali.studentmanagement.ui.screens.admin.management.subject.SubjectScreen
import com.anomali.studentmanagement.ui.screens.admin.management.teacher.CreateTeacherScreen
import com.anomali.studentmanagement.ui.screens.admin.management.teacher.DetailTeacherScreen
import com.anomali.studentmanagement.ui.screens.admin.management.teacher.GuruScreen
import com.anomali.studentmanagement.ui.screens.admin.schedule.CreateScheduleScreen
import com.anomali.studentmanagement.ui.screens.admin.schedule.DetailScheduleScreen
import com.anomali.studentmanagement.ui.screens.admin.schedule.ScheduleScreen
import com.anomali.studentmanagement.ui.screens.auth.LoginScreen
import com.anomali.studentmanagement.ui.screens.auth.RegisterScreen
import com.anomali.studentmanagement.ui.screens.students.CreateEditStudentScreen
import com.anomali.studentmanagement.ui.screens.teacher.TeacherDashboardScreen
import com.anomali.studentmanagement.ui.screens.teacher.grade.GradeCreateScreen
import com.anomali.studentmanagement.ui.screens.teacher.grade.GradeScreen

//import com.anomali.studentmanagement.ui.screens.students.StudentListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val token = getTokenFromPreferences(context)
    val authRepository = AuthRepositoryImpl(context = context)
    val studentRepository = StudentRepositoryImpl(
        context = context
    )
    val classesRepository = ClassesRepositoryImpl(context = context)
    val subjectRepository = SubjectRepositoryImpl(context = context)
    val teacherRepository = TeacherRepositoryImpl(context = context)
    val scheduleRepository = ScheduleRepositoryImpl(context = context)
    val gradeRepository = GradeRepositoryImpl(context = context)
    val studentDao = StudentDatabase.getDatabase(context).studentDao()


    NavHost(
        navController,
        startDestination = if (token.isNotEmpty()) AppRoutes.TeacherDashboardScreen.route else AppRoutes.LoginScreen.route
    ) {
        composable(AppRoutes.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.AdminDashboardScreen.route) {
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

        // Admin
        composable(AppRoutes.AdminDashboardScreen.route) {
            DashboardScreen(
                navController = navController,
                authRepository = authRepository,
                context = context,
                token = token
            )
        }
        // Management
        composable(AppRoutes.ManagementScreen.route) {
            ManagementScreen(
                navController = navController
            )
        }
        composable(AppRoutes.ManagementTeacherScreen.route) {
            GuruScreen(
                teacherRepository = teacherRepository,
                navController = navController
            )
        }
        composable(AppRoutes.ManagementStudentScreen.route) {
            StudentScreen(
                navController = navController,
                studentRepository = studentRepository
            )
        }
        composable(AppRoutes.ManagementClassScreen.route) {
            ClassesScreen(
                navController = navController,
                classesRepository = classesRepository
            )
        }
        composable(AppRoutes.ManagementSubjectScreen.route) {
            SubjectScreen(
                navController = navController,
                subjectRepository = subjectRepository
            )
        }
        composable(AppRoutes.ManagementScheduleScreen.route) {
             ScheduleScreen(
                 navController = navController,
                 scheduleRepository = scheduleRepository
             )
        }

        // Create
        composable(AppRoutes.CreateSubjectScreen.route) {
            CreateSubjectScreen(
                navController = navController,
                subjectRepository = subjectRepository
            )
        }

        composable(AppRoutes.CreateClassScreen.route) {
            CreateKelasScreen(
                navController = navController,
                classesRepository = classesRepository
            )
        }

        composable(AppRoutes.CreateStudentScreen.route) {
            CreateStudentScreen(
                navController = navController,
                studentRepository = studentRepository,
                classesRepository = classesRepository
            )
        }
        composable(AppRoutes.CreateTeacherScreen.route) {
            CreateTeacherScreen(
                navController = navController,
                teacherRepository = teacherRepository,
            )
        }

        composable(AppRoutes.CreateScheduleScreen.route) {
            CreateScheduleScreen(
                navController = navController,
                scheduleRepository = scheduleRepository,
                classRepository = classesRepository,
                subjectRepository = subjectRepository,
                teacherRepository = teacherRepository
            )
        }

        // Detail
        composable(AppRoutes.DetailSubjectScreen.route) {
            val subjectId = it.arguments?.getString("subjectId")?.toIntOrNull() ?: 0
            DetailSubjectScreen(
                navController = navController,
                subjectRepository = subjectRepository,
                subjectId = subjectId
            )
        }

        composable(AppRoutes.DetailClassScreen.route) {
            val classesId = it.arguments?.getString("classesId")?.toIntOrNull() ?: 0
            DetailClassesScreen(
                navController = navController,
                classesRepository = classesRepository,
                classesId = classesId
            )
        }

        composable(AppRoutes.DetailStudentScreen.route) {
            val studentId = it.arguments?.getString("studentId")?.toIntOrNull() ?: 0
            DetailStudentScreen(
                navController = navController,
                studentRepository = studentRepository,
                classesRepository = classesRepository,
                studentId = studentId,
            )
        }

        composable(AppRoutes.DetailTeacherScreen.route) {
            val teacherId = it.arguments?.getString("teacherId")?.toIntOrNull() ?: 0
            DetailTeacherScreen(
                navController = navController,
                teacherRepository = teacherRepository,
                teacherId = teacherId,
            )
        }

        composable(AppRoutes.DetailScheduleScreen.route) {
            val scheduleId = it.arguments?.getString("scheduleId")?.toIntOrNull() ?: 0
            DetailScheduleScreen(
                navController = navController,
                scheduleRepository = scheduleRepository,
                classRepository = classesRepository,
                subjectRepository = subjectRepository,
                teacherRepository = teacherRepository,
                scheduleId = scheduleId
            )
        }

//        composable(AppRoutes.FavoriteListScreen.route) {
//            FavoriteListScreen(
//                navController = navController,
//                studentRepository = studentRepository,
//                studentDao = studentDao
//            )
//        }
        composable(AppRoutes.ProfileScreen.route) {
            ProfileScreen(
                navController,
                authRepository = authRepository,
                token = token,
                onLoggedOut = {
                    navController.navigate(AppRoutes.LoginScreen.route) {
                        popUpTo(AppRoutes.ProfileScreen.route) { inclusive = true }
                    }
                },
            )
        }
//        composable(AppRoutes.StudentDetailScreen.route) { backStackEntry ->
//            val studentId = backStackEntry.arguments?.getString("studentId")
//            if (studentId != null) {
//                StudentDetailScreen(navController, studentId)
//            } else {
//                navController.popBackStack()
//            }
//        }

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
                navController.popBackStack()
            }
        }

//        composable(AppRoutes.CreateStudentScreen.route) {
//            CreateEditStudentScreen(navController, isEdit = false, studentId = null)
//        }

        // Teacher
        composable(AppRoutes.TeacherDashboardScreen.route) {
            TeacherDashboardScreen(
                navController = navController,
                authRepository = authRepository,
                context = context,
                token = token
            )
        }

        composable(AppRoutes.GradeScreen.route) {
            GradeScreen(
                navController = navController,
                studentRepository = studentRepository,
                subjectRepository = subjectRepository,
                teacherRepository = teacherRepository,
                gradeRepository = gradeRepository
            )
        }

        composable(AppRoutes.GradeCreateScreen.route) {
            GradeCreateScreen(
                navController = navController,
                studentRepository = studentRepository,
                subjectRepository = subjectRepository,
                teacherRepository = teacherRepository,
                gradeRepository = gradeRepository
            )
        }
    }
}