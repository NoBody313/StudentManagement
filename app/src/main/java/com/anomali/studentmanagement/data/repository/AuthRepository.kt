package com.anomali.studentmanagement.data.repository

import android.content.Context
import com.anomali.studentmanagement.core.utils.PreferencesUtils
import com.anomali.studentmanagement.data.data_resource.remote.dto.request.LoginRequest
import com.anomali.studentmanagement.data.data_resource.remote.dto.request.RegisterRequest
import com.anomali.studentmanagement.data.data_resource.remote.dto.response.LoginResponse
import com.anomali.studentmanagement.data.data_resource.remote.network.ApiService
import com.anomali.studentmanagement.data.data_resource.remote.network.RetrofitInstance
import com.anomali.studentmanagement.data.mapper.toModel
import com.anomali.studentmanagement.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun getUserData(token: String): User?
    suspend fun logout(token: String)
    suspend fun register(name: String, email: String, password: String)
}

class AuthRepositoryImpl(
    private val context: Context
) : AuthRepository {
    private val apiService: ApiService by lazy {
        RetrofitInstance.getRetrofitInstance(context)
    }

    override suspend fun register(name: String, email: String, password: String) {
        withContext(Dispatchers.IO) {
            apiService.register(RegisterRequest(name, email, password))
        }
    }

    override suspend fun login(email: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                response.body()?.toModel()?.let { loginResponse ->
                    PreferencesUtils.saveTokenToPreferences(context, loginResponse.token)
                    return@let loginResponse
                } ?: throw Exception("Empty response body")
            } else {
                throw Exception("Login failed: ${response.code()} ${response.message()}")
            }
        }
    }

    override suspend fun getUserData(token: String): User? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUser("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let { userDTO ->
                        val user = userDTO.toModel()
                        PreferencesUtils.saveUserToPreferences(context, user)
                        user
                    }
                } else {
                    println("Error: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("Exception: ${e.message}")
                null
            }
        }
    }

    override suspend fun logout(token: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.logout("Bearer $token")
                if (response.isSuccessful) {
                    PreferencesUtils.clearTokenFromPreferences(context)
                    println("Logout berhasil. Token telah dihapus.")
                    val clearedToken = PreferencesUtils.getTokenFromPreferences(context)
                    println("Token setelah logout: $clearedToken")
                } else {
                    println("Logout gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                println("Error during logout: ${e.message}")
            }
        }
    }
}