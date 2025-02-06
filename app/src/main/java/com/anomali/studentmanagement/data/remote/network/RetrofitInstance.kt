package com.anomali.studentmanagement.data.remote.network

import android.content.Context
import com.anomali.studentmanagement.core.utils.AuthInterceptor
import com.anomali.studentmanagement.data.remote.api.AuthService
import com.anomali.studentmanagement.data.remote.api.StudentService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.220.95:8000/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private lateinit var retrofit: Retrofit

    fun initRetrofit(context: Context) {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getAuthService(): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    fun getStudentService(): StudentService {
        return retrofit.create(StudentService::class.java)
    }
}