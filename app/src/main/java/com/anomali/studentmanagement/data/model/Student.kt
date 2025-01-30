package com.anomali.studentmanagement.data.model

data class Student(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String?,
    val dateOfBirth: String,
    val createdAt: String,
    val updatedAt: String
)