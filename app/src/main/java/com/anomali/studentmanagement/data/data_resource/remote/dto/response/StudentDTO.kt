package com.anomali.studentmanagement.data.data_resource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class StudentDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)