package com.anomali.studentmanagement.data.remote.api

import com.anomali.studentmanagement.data.remote.dto.response.StudentResponseDTO
import com.anomali.studentmanagement.data.model.Student
import com.anomali.studentmanagement.data.remote.dto.response.StudentListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentService {

    @GET("api/students")
    suspend fun getStudents(): Response<StudentListResponse>

    @GET("api/students/{id}")
    suspend fun getStudentById(@Path("id") id: Int): Response<StudentResponseDTO>

    @POST("api/students")
    suspend fun createStudent(@Body student: Student): Response<StudentResponseDTO>

    @PUT("api/students/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: Student): Response<StudentResponseDTO>

    @DELETE("api/students/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Unit>
}