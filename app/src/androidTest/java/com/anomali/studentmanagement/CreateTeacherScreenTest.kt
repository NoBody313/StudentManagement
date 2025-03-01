package com.anomali.studentmanagement

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.anomali.studentmanagement.core.routes.AppRoutes
import com.anomali.studentmanagement.data.model.User
import com.anomali.studentmanagement.data.remote.dto.response.auth.LoginResponseDTO
import com.anomali.studentmanagement.data.repository.auth.AuthRepository
import com.anomali.studentmanagement.ui.screens.auth.LoginScreen
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.eq

@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockAuthRepository: AuthRepository
    private lateinit var mockNavController: NavController

    private val testEmail = "test@example.com"
    private val testPassword = "password123"
    private val testToken = "mocked_token"

    @Before
    fun setUp() {
        mockAuthRepository = mockk()
        mockNavController = mockk(relaxed = true)

        composeTestRule.setContent {
            LoginScreen(
                navController = mockNavController,
                onLoginSuccess = {}
            )
        }
    }

    @Test
    fun testLoginWithValidCredentials_navigatesToAdminDashboard() = runTest {
        val loginResponse = LoginResponseDTO(testToken)
        val user = User(
            id = 1,
            name = "Test User",
            email = "test@example.com",
            role = "admin",
            createdAt = "2024-01-01T00:00:00Z",
            updatedAt = "2024-01-01T00:00:00Z"
        )
        coEvery { mockAuthRepository.login(eq(testEmail), eq(testPassword)) } returns loginResponse
        coEvery { mockAuthRepository.getUserData(eq(testToken)) } returns user

        composeTestRule.onNodeWithText("Email").performTextInput(testEmail)
        composeTestRule.onNodeWithText("Password").performTextInput(testPassword)
        composeTestRule.onNodeWithText("Login").performClick()

        advanceUntilIdle()

        verify(exactly = 1) { mockNavController.navigate(AppRoutes.AdminDashboardScreen.route) }
    }
}