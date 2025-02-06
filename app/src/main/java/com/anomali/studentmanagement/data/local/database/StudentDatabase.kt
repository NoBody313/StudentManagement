package com.anomali.studentmanagement.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anomali.studentmanagement.data.local.dao.StudentDao
import com.anomali.studentmanagement.data.local.entity.FatherEntity
import com.anomali.studentmanagement.data.local.entity.MotherEntity
import com.anomali.studentmanagement.data.local.entity.StudentEntity

@Database(
    entities = [StudentEntity::class, FatherEntity::class, MotherEntity::class],
    version = 1,
    exportSchema = false
)

abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        private const val DATABASE_NAME = "student_database"

        @Volatile
        private var instance: StudentDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StudentDatabase {
            if (instance == null) {
                synchronized(StudentDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance as StudentDatabase
        }
    }
}