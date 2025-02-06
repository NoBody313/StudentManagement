package com.anomali.studentmanagement.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class StudentResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("nis") val nis: String,
    @SerializedName("nisn") val nisn: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone_number") val phone: String,
    @SerializedName("class_level") val classLevel: String,
    @SerializedName("homeroom_teacher") val homeroomTeacher: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("place_of_birth") val placeOfBirth: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("father_id") val fatherId: Int,
    @SerializedName("mother_id") val motherId: Int,
    @SerializedName("father") val father: Father,
    @SerializedName("mother") val mother: Mother,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class Father(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("born_place") val bornPlace: String,
    @SerializedName("born_date") val bornDate: String,
    @SerializedName("occupation") val occupation: String,
    @SerializedName("address") val address: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class Mother(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("born_place") val bornPlace: String,
    @SerializedName("born_date") val bornDate: String,
    @SerializedName("occupation") val occupation: String,
    @SerializedName("address") val address: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class StudentListResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val students: List<StudentResponseDTO>
)