package com.anomali.studentmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mothers")
data class MotherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val studentId: Int,
    val name: String,
    val phoneNumber: String,
    val bornPlace: String,
    val bornDate: String,
    val occupation: String,
    val address: String
)
