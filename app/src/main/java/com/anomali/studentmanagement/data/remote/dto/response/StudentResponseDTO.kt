package com.anomali.studentmanagement.data.remote.dto.response

import com.google.gson.annotations.SerializedName

//Di sini name nya harus sama ama respon di JSON nya
data class StudentResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("user") val user: UserDTO,
    @SerializedName("nis") val nis: String,
    @SerializedName("nisn") val nisn: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("place_of_birth") val placeOfBirth: String,
    @SerializedName("class_id") val classId: Int,
    @SerializedName("classes") val classes: ClassesDTO?,
    @SerializedName("gender") val gender: String,
    @SerializedName("father") val father: Father,
    @SerializedName("mother") val mother: Mother,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class StudentListResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("user") val user: UserDTO,
    @SerializedName("nis") val nis: String,
    @SerializedName("nisn") val nisn: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("place_of_birth") val placeOfBirth: String,
    @SerializedName("class_id") val classId: Int,
    @SerializedName("classes") val classes: ClassesDTO,
    @SerializedName("gender") val gender: String,
    @SerializedName("father_id") val fatherId: Int,
    @SerializedName("mother_id") val motherId: Int,
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
    @SerializedName("data") val students: List<StudentListResponseDTO>,
)

data class StudentCreateResponse(
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: UserDTO,
    @SerializedName("student") val students: StudentResponseDTO,
    @SerializedName("father") val father: Father,
    @SerializedName("mother") val mother: Mother
)