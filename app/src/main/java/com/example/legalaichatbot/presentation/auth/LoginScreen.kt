package com.legal.aichatbot.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.legal.aichatbot.R
import com.legal.aichatbot.utils.NetworkResult
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }

    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is NetworkResult.Success -> {
                onLoginSuccess()
                viewModel.resetAuthState()
            }
            else -> {}
        }
        if (authState is NetworkResult.Loading) {
            kotlinx.coroutines.delay(1000)
            viewModel.resetAuthState()
        }
    }

    val isEnglish = selectedLanguage == "en"

    // App colors
    val mainColor = Color(0xFF4440B7)   // rgb(68,64,183)
    val lightBackground = Color(0xFFE3EAFC) // #e3eafc

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Language chips pinned to top-right
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChip(
                selected = selectedLanguage == "en",
                onClick = { selectedLanguage = "en" },
                label = { Text("English") },
                modifier = Modifier.padding(end = 8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = lightBackground,
                    labelColor = mainColor,
                    iconColor = mainColor,
                    selectedContainerColor = mainColor,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.White,
                    selectedTrailingIconColor = Color.White
                )
            )
            FilterChip(
                selected = selectedLanguage == "ur",
                onClick = { selectedLanguage = "ur" },
                label = { Text("اردو") },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = lightBackground,
                    labelColor = mainColor,
                    iconColor = mainColor,
                    selectedContainerColor = mainColor,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.White,
                    selectedTrailingIconColor = Color.White
                )
            )
        }

        // Centered login content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (isEnglish) "Legal Assistant" else "قانونی معاون",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = mainColor
            )

            Text(
                text = if (isEnglish) "Your Virtual Legal Guide" else "آپ کا ورچوئل قانونی رہنما",
                fontSize = 15.sp,
                color = mainColor
            )

            Spacer(modifier = Modifier.height(36.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(if (isEnglish) "Email" else "ای میل") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = mainColor) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = lightBackground,
                    unfocusedContainerColor = lightBackground,
                    cursorColor = mainColor,
                    focusedBorderColor = mainColor,
                    unfocusedBorderColor = mainColor.copy(alpha = 0.4f),
                    focusedLeadingIconColor = mainColor,
                    unfocusedLeadingIconColor = mainColor.copy(alpha = 0.6f),
                    focusedTrailingIconColor = mainColor,
                    unfocusedTrailingIconColor = mainColor.copy(alpha = 0.6f),
                    focusedLabelColor = mainColor,
                    unfocusedLabelColor = mainColor.copy(alpha = 0.6f),
                    focusedPlaceholderColor = mainColor.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = mainColor.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(if (isEnglish) "Password" else "پاس ورڈ") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = mainColor) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = mainColor
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = lightBackground,
                    unfocusedContainerColor = lightBackground,
                    cursorColor = mainColor,
                    focusedBorderColor = mainColor,
                    unfocusedBorderColor = mainColor.copy(alpha = 0.4f),
                    focusedLeadingIconColor = mainColor,
                    unfocusedLeadingIconColor = mainColor.copy(alpha = 0.6f),
                    focusedTrailingIconColor = mainColor,
                    unfocusedTrailingIconColor = mainColor.copy(alpha = 0.6f),
                    focusedLabelColor = mainColor,
                    unfocusedLabelColor = mainColor.copy(alpha = 0.6f),
                    focusedPlaceholderColor = mainColor.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = mainColor.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(12.dp)
            )

            if (authState is NetworkResult.Error) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = (authState as NetworkResult.Error).message ?: "Error",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.login(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = authState !is NetworkResult.Loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainColor,
                    contentColor = Color.White
                )
            ) {
                if (authState is NetworkResult.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = if (isEnglish) "Login" else "لاگ ان", fontSize = 17.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = if (isEnglish) "Don't have an account? " else "اکاؤنٹ نہیں ہے؟ ",
                    color = mainColor.copy(alpha = 0.9f)
                )
                TextButton(onClick = onNavigateToRegister) {
                    Text(
                        text = if (isEnglish) "Register" else "رجسٹر کریں",
                        fontWeight = FontWeight.Bold,
                        color = mainColor
                    )
                }
            }
        }
    }
}
