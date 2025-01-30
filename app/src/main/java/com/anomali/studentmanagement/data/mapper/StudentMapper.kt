package com.anomali.studentmanagement.data.mapper

import com.anomali.studentmanagement.data.data_resource.remote.dto.response.StudentDTO
import com.anomali.studentmanagement.data.model.Student

fun StudentDTO.toModel(): Student {
    return Student(
        id = id,
        name = name,
        email = email,
        phone = phone,
        dateOfBirth = dateOfBirth,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}