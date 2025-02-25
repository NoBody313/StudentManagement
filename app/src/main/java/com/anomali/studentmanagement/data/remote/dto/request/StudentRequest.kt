package com.anomali.studentmanagement.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class StudentRequest(
    val nis: String,
    val nisn: String,
    @SerializedName("class_id") val classId: Int,
    val dateOfBirth: String,
    val placeOfBirth: String,
    val gender: String,
    val father: FatherRequest,
    val mother: MotherRequest,
    val user: UserRequest
)

data class FatherRequest(
    val name: String,
    val phoneNumber: String,
    val bornPlace: String,
    val bornDate: String,
    val occupation: String,
    val address: String
)

data class MotherRequest(
    val name: String,
    val phoneNumber: String,
    val bornPlace: String,
    val bornDate: String,
    val occupation: String,
    val address: String
)

data class UserRequest(
    val name: String,
    val email: String,
    val password: String
)
