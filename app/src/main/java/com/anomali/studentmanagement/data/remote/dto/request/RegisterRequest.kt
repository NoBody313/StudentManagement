package com.anomali.studentmanagement.data.remote.dto.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)
