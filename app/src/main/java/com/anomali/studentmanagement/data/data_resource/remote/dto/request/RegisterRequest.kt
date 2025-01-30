package com.anomali.studentmanagement.data.data_resource.remote.dto.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)