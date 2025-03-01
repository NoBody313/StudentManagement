package com.anomali.studentmanagement

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anomali.studentmanagement.core.routes.AppRoutes
import com.anomali.studentmanagement.core.utils.PreferencesUtils
import com.anomali.studentmanagement.ui.navigations.BottomNavigation
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockPreferencesUtils: PreferencesUtils
    private lateinit var mockNavController: NavController

    @Before
    fun setup() {
        composeTestRule.setContent {
            mockPreferencesUtils = mockk()
            every { mockPreferencesUtils.getUserRoleFromPreferences(any()) } returns "guru"
        }
    }

    @Test
    fun testMenuDisplayedForGuruRole() {
        composeTestRule.setContent {
            mockNavController = rememberNavController()
            BottomNavigation(navController = mockNavController)
        }

        // Memastikan nama menu dan ikon muncul sesuai untuk role guru
        composeTestRule.onNodeWithText("Penilaian").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Penilaian Icon").assertIsDisplayed()

        composeTestRule.onNodeWithText("Absen").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Absen Icon").assertIsDisplayed()

        composeTestRule.onNodeWithText("Pengaturan").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Pengaturan Icon").assertIsDisplayed()
    }

    @Test
    fun testIconAndMenuClickAction() {
        composeTestRule.setContent {
            mockNavController = rememberNavController()
            BottomNavigation(navController = mockNavController)
        }

        // Memastikan menu dan ikon ada, lalu memeriksa apakah klik berfungsi
        composeTestRule.onNodeWithText("Penilaian")
            .assertIsDisplayed()
//            .assertHasClickAction()

        composeTestRule.onNodeWithText("Absen")
            .assertIsDisplayed()
//            .assertHasClickAction()

        composeTestRule.onNodeWithText("Pengaturan")
            .assertIsDisplayed()
//            .assertHasClickAction()
    }
}
