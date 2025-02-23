package com.anomali.studentmanagement.ui.screens.admin.management.guru

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anomali.studentmanagement.ui.components.LabeledInputField
import com.anomali.studentmanagement.ui.navigations.TopNavigationNoIcon

@Composable
fun CreateGuruScreen(navController: NavController) {
    var namaLengkap by remember { mutableStateOf("") }
    var nip by remember { mutableStateOf("") }
    var mataPelajaran by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopNavigationNoIcon(navController) }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Data Pribadi",
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
                        title = "Nama Lengkap",
                        placeholder = "Masukkan nama lengkap",
                        value = namaLengkap,
                        onValueChange = { namaLengkap = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isPasswordTextField = false
                    )
                    LabeledInputField(
                        title = "NIP",
                        placeholder = "Masukkan NIP",
                        value = nip,
                        onValueChange = { nip = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isPasswordTextField = false
                    )
                    LabeledInputField(
                        title = "Mata Pelajaran",
                        placeholder = "Masukkan mata pelajaran",
                        value = mataPelajaran,
                        onValueChange = { mataPelajaran = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isPasswordTextField = false
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Autentikasi",
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
                        title = "Email",
                        placeholder = "Masukkan email",
                        value = email,
                        onValueChange = { email = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isPasswordTextField = false
                    )
                    LabeledInputField(
                        title = "Password",
                        placeholder = "Masukkan password",
                        value = password,
                        onValueChange = { password = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPasswordTextField = true
                    )
                    LabeledInputField(
                        title = "Konfirmasi Password",
                        placeholder = "Masukkan konfirmasi password",
                        value = konfirmasiPassword,
                        onValueChange = { konfirmasiPassword = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPasswordTextField = true
                    )
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(24.dp, 12.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFF16A34A), shape = RoundedCornerShape(size = 4.dp))
            ) {
                Text(
                    text = "Create Guru",
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
}