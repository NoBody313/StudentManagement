package com.anomali.studentmanagement.ui.screens.admin.management.subject

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anomali.studentmanagement.data.model.Subject
import com.anomali.studentmanagement.data.repository.SubjectRepository
import com.anomali.studentmanagement.ui.components.LabeledInputField
import com.anomali.studentmanagement.ui.navigations.TopNavigationNoIcon
import kotlinx.coroutines.launch

@Composable
fun DetailSubjectScreen(
    navController: NavController,
    subjectRepository: SubjectRepository,
    subjectId: Int
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var subject by remember { mutableStateOf<Subject?>(null) }
    var subjectName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(subjectId) {
        isLoading = true
        try {
            val response = subjectRepository.getSubjectById(subjectId)
            if (response.isSuccessful) {
                subject = response.body()
                subjectName = subject?.name ?: ""
            } else {
                errorMessage = "Error: ${response.message()}"
            }
        } catch (e: Exception) {
            errorMessage = "Error: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = { TopNavigationNoIcon(navController) },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(paddingValues)
                .padding(32.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Edit ${subject?.name}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF1E3A8A),
                    )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start,
                ) {
                    LabeledInputField(
                        title = "Nama pelajaran",
                        placeholder = "Masukkan nama pelajaran",
                        value = subjectName,
                        onValueChange = { subjectName = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isPasswordTextField = false
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                val response = subjectRepository.deleteSubject(subjectId)
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Pelajaran berhasil dihapus!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                } else {
                                    errorMessage = "Error: ${response.message()}"
                                }
                            } catch (e: Exception) {
                                errorMessage = "Error: ${e.message}"
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = "Hapus Pelajaran",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Button(
                    onClick = {
                        if (subjectName.isNotEmpty()) {
                            coroutineScope.launch {
                                isLoading = true
                                try {
                                    subjectRepository.updateSubject(
                                        id = subjectId,
                                        name = subjectName
                                    )
                                    Toast.makeText(
                                        context,
                                        "Pelajaran berhasil diubah!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                } catch (e: Exception) {
                                    errorMessage = "Error: ${e.message}"
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF16A34A),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "Ubah Pelajaran",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
        if (errorMessage != null) {
            Text(text = errorMessage!!, color = Color.Red)
        }
    }
}