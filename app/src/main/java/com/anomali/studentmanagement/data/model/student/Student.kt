package com.anomali.studentmanagement.data.model.student

import com.anomali.studentmanagement.data.model.Classes
import com.anomali.studentmanagement.data.model.User

data class Student(
    val id: Int?,
    val nis: String,
    val nisn: String,
    val date_of_birth: String,
    val place_of_birth: String,
    val gender: String,
    val class_id: Classes,
    val user: User,
    val createdAt: String,
    val updatedAt: String,
)