package com.anomali.studentmanagement.data.data_resource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LogoutResponseDTO(
    @SerializedName("message") val message: String
)
