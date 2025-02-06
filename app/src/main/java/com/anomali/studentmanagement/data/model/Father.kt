package com.anomali.studentmanagement.data.model

import java.util.Date

data class Father(
    val id: Int,
    val name: String,
    val phoneNumber: Int,
    val bornPlace: Date,
    val bornDate: String,
    val occupation: String,
    val address: String
)
