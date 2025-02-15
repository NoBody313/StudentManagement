package com.anomali.studentmanagement.data.model

data class Student(
    val id: Int,
    val name: String,
    val nis: String,
    val nisn: String,
    val email: String,
    val phoneNumber: String,
    val classLevel: String,
    val homeroomTeacher: String,
    val dateOfBirth: String,
    val placeOfBirth: String,
    val gender: String,
    val father: Father,
    val mother: Mother,
    val createdAt: String,
    val updatedAt: String,
)