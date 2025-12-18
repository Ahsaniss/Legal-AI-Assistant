package com.legal.aichatbot.presentation.chat.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.legal.aichatbot.utils.VoiceManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput(
    onSendMessage: (String, Boolean) -> Unit, // Added Boolean parameter for isVoiceMode
    isLoading: Boolean,
    isEnglish: Boolean,
    currentLanguage: String = "en"
) {
    var message by remember { mutableStateOf("") }
    var isVoiceMode by remember { mutableStateOf(false) }
    var isListening by remember { mutableStateOf(false) }
    var showVoiceDialog by remember { mutableStateOf(false) }
    var voiceManager by remember { mutableStateOf<VoiceManager?>(null) }
    var showPermissionDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            isVoiceMode = true
            voiceManager = VoiceManager(
                context = context,
                onResult = { text ->
                    message = text
                    showVoiceDialog = false
                    isListening = false
                    // Auto-send when voice input is received
                    if (text.isNotBlank()) {
                        onSendMessage(text, true) // true = voice mode
                        message = ""
                    }
                },
                onError = { error ->
                    showVoiceDialog = false
                    isListening = false
                }
            ).apply {
                setLanguage(currentLanguage)
            }
            // Start listening immediately after permission granted
            showVoiceDialog = true
            isListening = true
            voiceManager?.startListening()
        } else {
            showPermissionDialog = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            voiceManager?.cleanup()
        }
    }

    LaunchedEffect(currentLanguage) {
        voiceManager?.setLanguage(currentLanguage)
    }

    // Voice Dialog with Animation
    if (showVoiceDialog) {
        Dialog(onDismissRequest = {
            voiceManager?.stopListening()
            showVoiceDialog = false
            isListening = false
        }) {
            VoiceListeningPopup(
                isListening = isListening,
                isEnglish = isEnglish,
                onCancel = {
                    voiceManager?.stopListening()
                    showVoiceDialog = false
                    isListening = false
                }
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            val inputTextColor = Color(0xFF4440B7)  // Main color: rgb(68, 64, 183)
            val inputBgColor = Color(0xFFF5F5F5)    // Light gray/white background

            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp)),
                placeholder = {
                    Text(
                        if (isEnglish) "Ask your legal question..." else "اپنا قانونی سوال پوچھیں...",
                        color = inputTextColor.copy(alpha = 0.7f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = inputBgColor,
                    unfocusedContainerColor = inputBgColor,
                    disabledContainerColor = inputBgColor,
                    focusedTextColor = inputTextColor,
                    unfocusedTextColor = inputTextColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                maxLines = 4,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Voice button - opens animated popup
            IconButton(
                onClick = {
                    if (isVoiceMode) {
                        showVoiceDialog = true
                        isListening = true
                        voiceManager?.startListening()
                    } else {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4440B7)),
                enabled = !isLoading
            ) {
                Icon(
                    Icons.Default.Mic,
                    contentDescription = if (isEnglish) "Voice input" else "آواز ان پٹ",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Send button - for text chat
            IconButton(
                onClick = {
                    if (message.isNotBlank()) {
                        onSendMessage(message.trim(), false) // false = text mode
                        message = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                enabled = message.isNotBlank() && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        }
    }

    // Permission dialog
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text(if (isEnglish) "Microphone Permission" else "مائیکروفون کی اجازت") },
            text = {
                Text(
                    if (isEnglish)
                        "Microphone permission is required for voice input. Please grant permission in settings."
                    else
                        "آواز ان پٹ کے لیے مائیکروفون کی اجازت درکار ہے۔ براہ کرم ترتیبات میں اجازت دیں۔"
                )
            },
            confirmButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text(if (isEnglish) "OK" else "ٹھیک ہے")
                }
            }
        )
    }
}

@Composable
fun VoiceListeningPopup(
    isListening: Boolean,
    isEnglish: Boolean,
    onCancel: () -> Unit
) {
    // Pulsing animation for the microphone
    val infiniteTransition = rememberInfiniteTransition(label = "mic_pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .wrapContentHeight(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4440B7)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated microphone icon
            Icon(
                Icons.Default.Mic,
                contentDescription = "Listening",
                modifier = Modifier
                    .size(80.dp)
                    .scale(scale),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (isEnglish) "Listening..." else "سن رہا ہے...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isEnglish) "Speak your question now" else "اب اپنا سوال بولیں",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Cancel button
            OutlinedButton(
                onClick = onCancel,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White)
            ) {
                Text(
                    text = if (isEnglish) "Cancel" else "منسوخ کریں",
                    fontSize = 16.sp
                )
            }
        }
    }
}


