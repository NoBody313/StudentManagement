package com.anomali.studentmanagement.data.repository

import com.anomali.studentmanagement.data.data_resource.remote.network.ApiService
import com.anomali.studentmanagement.data.mapper.toModel
import com.anomali.studentmanagement.data.model.Student
import retrofit2.Response

interface StudentRepository {
    suspend fun getStudents(): Response<List<Student>>
    suspend fun getStudentById(id: Int): Response<Student>
    suspend fun createStudent(student: Student): Response<Student>
    suspend fun updateStudent(id: Int, student: Student): Response<Student>
    suspend fun deleteStudent(id: Int): Response<Unit>
}

class StudentRepositoryImpl(private val apiService: ApiService) : StudentRepository {
    override suspend fun getStudents(): Response<List<Student>> {
        val response = apiService.getStudents()
        return if (response.isSuccessful) {
            Response.success(response.body()!!.map { it.toModel() })
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun getStudentById(id: Int): Response<Student> {
        val response = apiService.getStudentById(id)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun createStudent(student: Student): Response<Student> {
        val response = apiService.createStudent(student)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun updateStudent(id: Int, student: Student): Response<Student> {
        val response = apiService.updateStudent(id, student)
        return if (response.isSuccessful) {
            Response.success(response.body()!!.toModel())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun deleteStudent(id: Int): Response<Unit> {
        return apiService.deleteStudent(id)
    }
}