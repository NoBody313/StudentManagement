package com.anomali.studentmanagement.data.data_resource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserDTO
)