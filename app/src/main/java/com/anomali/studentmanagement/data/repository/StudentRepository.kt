package com.anomali.studentmanagement.data.repository

import android.content.Context
import com.anomali.studentmanagement.data.mapper.toModel
import com.anomali.studentmanagement.data.model.Student
import com.anomali.studentmanagement.data.remote.dto.response.StudentListResponse
import com.anomali.studentmanagement.data.remote.network.RetrofitInstance
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

interface StudentRepository {
    suspend fun getStudents(): Response<StudentListResponse>
    suspend fun getStudentById(id: Int): Response<Student>
    suspend fun createStudent(student: Student): Response<Student>
    suspend fun updateStudent(id: Int, student: Student): Response<Student>
    suspend fun deleteStudent(id: Int): Response<Unit>
}

class StudentRepositoryImpl(private val context: Context) : StudentRepository {
    init {
        RetrofitInstance.initRetrofit(context)
    }

    private val studentService = RetrofitInstance.getStudentService()

    override suspend fun getStudents(): Response<StudentListResponse> {
        return try {
            val response = studentService.getStudents()
            if (response.isSuccessful) {
                response
            } else {
                Response.error(response.code(), response.errorBody()!!)
            }
        } catch (e: Exception) {
            Response.error(500, "Internal Server Error".toResponseBody(null))
        }
    }

    override suspend fun getStudentById(id: Int): Response<Student> {
        val response = studentService.getStudentById(id)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun createStudent(student: Student): Response<Student> {
        val response = studentService.createStudent(student)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun updateStudent(id: Int, student: Student): Response<Student> {
        val response = studentService.updateStudent(id, student)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun deleteStudent(id: Int): Response<Unit> {
        return studentService.deleteStudent(id)
    }
}