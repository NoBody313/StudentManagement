package com.anomali.studentmanagement.data.mapper

import com.anomali.studentmanagement.data.data_resource.remote.dto.response.UserDTO
import com.anomali.studentmanagement.data.model.User

fun UserDTO.toModel(): User {
    return User(
        id = id,
        name = name,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}