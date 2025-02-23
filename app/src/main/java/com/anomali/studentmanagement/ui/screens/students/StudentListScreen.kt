package com.anomali.studentmanagement.ui.screens.students

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anomali.studentmanagement.data.local.dao.StudentDao
import com.anomali.studentmanagement.data.local.entity.StudentEntity
import com.anomali.studentmanagement.data.mapper.toEntity
import com.anomali.studentmanagement.data.mapper.toModel
import com.anomali.studentmanagement.data.model.Student
import com.anomali.studentmanagement.data.repository.StudentRepository
import com.anomali.studentmanagement.ui.components.StudentListItem
import com.anomali.studentmanagement.ui.navigations.BottomNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun StudentListScreen(
    navController: NavHostController,
    studentRepository: StudentRepository,
    studentDao: StudentDao
) {
    var students by remember { mutableStateOf<List<Student>>(emptyList()) } // Data from DTO
    var favoriteStudents by remember { mutableStateOf<List<StudentEntity>>(emptyList()) } // Data from DAO
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun refreshData() {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading = true
            try {
                val currentFavoriteStudents = studentDao.getAllStudents()
                favoriteStudents = currentFavoriteStudents
                val response = studentRepository.getStudents()
                if (response.isSuccessful) {
                    val studentListResponse = response.body()
                    val studentList =
                        studentListResponse?.students?.map { it.toModel() } ?: emptyList()
                    studentListResponse?.students?.forEach { studentDto ->
                        val studentEntity = studentDto.toEntity()
                        val existingStudent = currentFavoriteStudents.find { it.id == studentEntity.id }
                        if (existingStudent == null) {
                            studentDao.insert(studentEntity)
                        } else {
                            val updatedStudentEntity = studentEntity.copy(isFavorite = existingStudent.isFavorite)
                            studentDao.update(updatedStudentEntity)
                        }
                    }
                    val updatedFavoriteStudents = studentDao.getAllStudents()
                    favoriteStudents = updatedFavoriteStudents
                    students = studentList
                } else {
                    errorMessage = "Gagal mengambil data"
                }
            } catch (e: Exception) {
                errorMessage = "Terjadi kesalahan: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(true) {
        refreshData()
    }

    Scaffold(
        bottomBar = { BottomNavigation(navController, 1) }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 32.dp)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Data-Data Siswa",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                if (students.isNotEmpty()) {
                    students.forEach { student ->
                        val isFavorite =
                            favoriteStudents.any { it.id == student.id && it.isFavorite }
                        StudentListItem(
                            student = student,
                            isFavorite = isFavorite,
                            onFavoriteChanged = { newIsFavorite ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    studentRepository.updateFavoriteStatus(
                                        student.id,
                                        newIsFavorite
                                    )
                                    val updatedFavoriteStudents = studentDao.getAllStudents()
                                    favoriteStudents = updatedFavoriteStudents
                                }
                            },
                            studentRepository = studentRepository
                        )
                    }
                }
            }
        }
    }
}