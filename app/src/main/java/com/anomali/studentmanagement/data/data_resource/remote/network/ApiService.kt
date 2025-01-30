package com.anomali.studentmanagement.data.data_resource.remote.network

import com.anomali.studentmanagement.data.data_resource.remote.dto.request.LoginRequest
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.LoginResponseDTO
import com.anomali.studentmanagement.data.data_resource.remote.dto.request.RegisterRequest
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.LogoutResponseDTO
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.StudentDTO
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.UserDTO
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.UserResponse
import com.anomali.studentmanagement.data.model.Student
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponseDTO>

    @GET("api/user")
    suspend fun getUser(@Header("Authorization") token: String): Response<UserDTO>

    @POST("api/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<LogoutResponseDTO>

    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>

    @GET("api/students")
    suspend fun getStudents(): Response<List<StudentDTO>>

    @GET("api/students/{id}")
    suspend fun getStudentById(@Path("id") id: Int): Response<StudentDTO>

    @POST("api/students")
    suspend fun createStudent(@Body student: Student): Response<StudentDTO>

    @PUT("api/students/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: Student): Response<StudentDTO>

    @DELETE("api/students/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Unit>
}