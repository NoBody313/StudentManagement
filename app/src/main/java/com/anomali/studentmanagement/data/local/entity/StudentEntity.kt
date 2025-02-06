package com.anomali.studentmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    @Relation(parentColumn = "id", entityColumn = "studentId") val father: FatherEntity,
    @Relation(parentColumn = "id", entityColumn = "studentId") val mother: MotherEntity,
    val createdAt: String,
    val updatedAt: String,
)
