package com.anomali.studentmanagement

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import com.anomali.studentmanagement.data.remote.api.SubjectService
import com.anomali.studentmanagement.data.remote.dto.request.SubjectRequest
import com.anomali.studentmanagement.data.remote.dto.response.SubjectDTO
import com.anomali.studentmanagement.data.remote.dto.response.SubjectResponseDTO
import com.anomali.studentmanagement.data.repository.admin.SubjectRepository
import com.anomali.studentmanagement.data.repository.admin.SubjectRepositoryImpl
import com.anomali.studentmanagement.data.repository.auth.AuthRepository
import com.anomali.studentmanagement.ui.screens.auth.LoginScreen
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockAuthRepository: AuthRepository
    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        mockAuthRepository = mockk()
        mockNavController = mockk(relaxed = true)

        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = {}, navController = mockNavController)
        }
    }

    @Test
    fun testLoginWithValidCredentials() {
        // Mock a successful login response
        val loginResponse = mockk<AuthRepository.LoginResponse>()
        val user = mockk<AuthRepository.User>()
        val token = "mocked_token"
        val role = "admin"

        every { mockAuthRepository.login(any(), any()) } returns loginResponse
        every { mockAuthRepository.getUserData(any()) } returns user
        every { user.role } returns role

        // Simulate filling in valid credentials
        composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")

        // Simulate pressing login button
        composeTestRule.onNodeWithText("Login").performClick()

        // Verify that the correct navigation happens after a successful login
        verify { mockNavController.navigate(AppRoutes.AdminDashboardScreen.route) }
    }
}
