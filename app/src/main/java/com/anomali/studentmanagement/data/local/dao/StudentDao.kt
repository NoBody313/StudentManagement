package com.anomali.studentmanagement.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.anomali.studentmanagement.data.local.entity.StudentEntity

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: StudentEntity)

    @Update
    suspend fun update(student: StudentEntity)

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE id = :studentId")
    suspend fun getStudentById(studentId: Int): StudentEntity?

    @Query("UPDATE students SET isFavorite = :isFavorite WHERE id = :studentId")
    suspend fun updateFavoriteStatus(studentId: Int, isFavorite: Boolean)
}