package com.anomali.studentmanagement.data.mapper

import com.anomali.studentmanagement.data.data_resource.remote.dto.response.LoginResponse
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.LoginResponseDTO

fun LoginResponseDTO.toModel(): LoginResponse {
    return LoginResponse(
        token = token,
//        user = user.toModel()
    )
}