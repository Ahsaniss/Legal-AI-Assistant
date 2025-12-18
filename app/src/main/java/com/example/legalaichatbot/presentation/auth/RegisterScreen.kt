package com.example.legalaichatbot.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.legal.aichatbot.R
import com.legal.aichatbot.utils.NetworkResult
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.legal.aichatbot.presentation.auth.AuthViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var displayName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var selectedLanguage by rememberSaveable { mutableStateOf("en") }

    val authState by viewModel.authState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        when (authState) {
            is NetworkResult.Success -> {
                onRegisterSuccess()
                viewModel.resetAuthState()
            }
            else -> {}
        }

        if (authState is NetworkResult.Loading) {
            delay(1000)
            viewModel.resetAuthState()
        }
    }

    val isEnglish = selectedLanguage == "en"

    // App theme colors
    val mainColor = Color(0xFF4440B7)   // rgb(68,64,183)
    val lightBackground = Color(0xFFE3EAFC) // #e3eafc

    CompositionLocalProvider(
        LocalLayoutDirection provides if (isEnglish) LayoutDirection.Ltr else LayoutDirection.Rtl
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

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

            val scrollState = rememberScrollState()

            key(selectedLanguage) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(24.dp)
                        .imePadding()
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))

                        // Logo (project image)
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = if (isEnglish) "Logo" else "لوگو",
                            modifier = Modifier.size(70.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (isEnglish) "Create Account" else "اکاؤنٹ بنائیں",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = mainColor
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Display Name
                        OutlinedTextField(
                            value = displayName,
                            onValueChange = { displayName = it },
                            label = { Text(if (isEnglish) "Full Name" else "مکمل نام") },
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null, tint = mainColor)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
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

                        Spacer(modifier = Modifier.height(12.dp))

                        // Email
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(if (isEnglish) "Email" else "ای میل") },
                            leadingIcon = {
                                Icon(Icons.Default.Email, contentDescription = null, tint = mainColor)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
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

                        Spacer(modifier = Modifier.height(12.dp))

                        // Password
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(if (isEnglish) "Password" else "پاس ورڈ") },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = mainColor)
                            },
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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
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

                        Spacer(modifier = Modifier.height(12.dp))

                        // Confirm Password
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(if (isEnglish) "Confirm Password" else "پاس ورڈ کی تصدیق کریں") },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = mainColor)
                            },
                            trailingIcon = {
                                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                    Icon(
                                        imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        tint = mainColor
                                    )
                                }
                            },
                            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
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

                        Spacer(modifier = Modifier.height(8.dp))

                        // Error Message
                        if (errorMessage.isNotEmpty() || authState is NetworkResult.Error) {
                            Text(
                                text = errorMessage.ifEmpty { (authState as? NetworkResult.Error)?.message ?: "" },
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Register Button
                        Button(
                            onClick = {
                                errorMessage = ""
                                when {
                                    displayName.isEmpty() -> errorMessage = if (isEnglish) "Name is required" else "نام درکار ہے"
                                    email.isEmpty() -> errorMessage = if (isEnglish) "Email is required" else "ای میل درکار ہے"
                                    password.length < 6 -> errorMessage = if (isEnglish) "Password must be at least 6 characters" else "پاس ورڈ کم از کم 6 حروف کا ہونا چاہیے"
                                    password != confirmPassword -> errorMessage = if (isEnglish) "Passwords don't match" else "پاس ورڈ مماثل نہیں ہیں"
                                    else -> {
                                        viewModel.register(email, password, displayName)
                                        onNavigateToLogin()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            enabled = authState !is NetworkResult.Loading,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = mainColor,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            if (authState is NetworkResult.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(if (isEnglish) "Register" else "رجسٹر کریں")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Login Link (moved below the register button)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = if (isEnglish) "Already have an account? " else "پہلے سے اکاؤنٹ ہے؟ ",
                                color = mainColor.copy(alpha = 0.9f)
                            )
                            TextButton(onClick = onNavigateToLogin) {
                                Text(
                                    text = if (isEnglish) "Login" else "لاگ ان کریں",
                                    fontWeight = FontWeight.Bold,
                                    color = mainColor
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
