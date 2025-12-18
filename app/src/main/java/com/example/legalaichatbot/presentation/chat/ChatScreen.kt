package com.legal.aichatbot.presentation.chat

import androidx.compose.foundation.BorderStroke
import com.legal.aichatbot.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.legal.aichatbot.data.model.LegalCategory
import com.legal.aichatbot.presentation.auth.AuthViewModel
import com.legal.aichatbot.presentation.chat.components.ChatInput
import com.legal.aichatbot.presentation.chat.components.MessageBubble
import com.legal.aichatbot.utils.VoiceManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onLogout: () -> Unit,
    chatViewModel: ChatViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val messages by chatViewModel.messages.collectAsState()
    val isLoading by chatViewModel.isLoading.collectAsState()
    val currentLanguage by chatViewModel.currentLanguage.collectAsState()
    val context = LocalContext.current

    var showLanguageDialog by remember { mutableStateOf(false) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var ttsManager by remember { mutableStateOf<VoiceManager?>(null) }
    var lastMessageWasVoice by remember { mutableStateOf(false) } // Track if last message was via voice

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val isEnglish = currentLanguage == "en"

    // Initialize TTS for speaking responses
    DisposableEffect(Unit) {
        ttsManager = VoiceManager(
            context = context,
            onResult = {},
            onError = {}
        ).apply {
            setLanguage(currentLanguage)
        }
        onDispose {
            ttsManager?.cleanup()
        }
    }

    // Update TTS language when language changes
    LaunchedEffect(currentLanguage) {
        ttsManager?.setLanguage(currentLanguage)
    }

    // Speak the last bot message ONLY if it was a voice conversation
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
                // Speak bot's response ONLY if the user used voice mode
                val lastMessage = messages.lastOrNull()
                if (lastMessage?.isUser == false && lastMessageWasVoice) {
                    ttsManager?.speak(lastMessage.text)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp
            ) {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.g),
                                contentDescription = "Logo",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(Modifier.width(12.dp))

                        }
                    },
                    actions = {
                        IconButton(onClick = { showLanguageDialog = true }) {
                            Icon(
                                Icons.Default.Language,
                                contentDescription = if (isEnglish) "Change Language" else "زبان تبدیل کریں",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = { showCategoryDialog = true }) {
                            Icon(
                                Icons.Default.Category,
                                contentDescription = if (isEnglish) "Categories" else "زمرے",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = { chatViewModel.clearChat() }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = if (isEnglish) "Clear Chat" else "چیٹ صاف کریں",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = { showLogoutDialog = true }) {
                            Icon(
                                Icons.AutoMirrored.Filled.Logout,
                                contentDescription = if (isEnglish) "Logout" else "لاگ آؤٹ",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        bottomBar = {
            ChatInput(
                onSendMessage = { msg, isVoice ->
                    chatViewModel.sendMessage(msg)
                    lastMessageWasVoice = isVoice // Track if this was a voice message
                },
                isLoading = isLoading,
                isEnglish = isEnglish,
                currentLanguage = currentLanguage
            )
        }
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (messages.isEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Spacer(Modifier.height(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Legal AI Assistant Logo",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = if (isEnglish) "Welcome to Legal AI Assistant" else "قانونی AI معاون میں خوش آمدید",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)
                        textAlign = TextAlign.Center
                    )


                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = if (isEnglish)
                            "Get expert legal guidance on various matters"
                        else
                            "مختلف معاملات پر ماہرانہ قانونی رہنمائی حاصل کریں",
                        fontSize = 15.sp,
                        color = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)

                        textAlign = TextAlign.Center

                    )

                    Spacer(Modifier.height(32.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = if (isEnglish) Alignment.Start else Alignment.End
                        ) {
                            Text(
                                text = if (isEnglish) "Available Categories" else "دستیاب زمرے",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(Modifier.height(12.dp))

                            listOf(
                                "Family Law" to "خاندانی قانون",
                                "Labor Law" to "لیبر قانون",
                                "Criminal Law" to "فوجداری قانون",
                                "Property Law" to "جائیداد کا قانون",
                                "Contract Law" to "معاہدہ قانون",
                                "Business Law" to "کاروباری قانون"
                            ).forEach { (en, ur) ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = if (isEnglish) Arrangement.Start else Arrangement.End
                                ) {
                                    Text(
                                        text = "• ${if (isEnglish) en else ur}",
                                        fontSize = 15.sp,
                                        color = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(28.dp))

                    Text(
                        text = if (isEnglish) "Try asking:" else "یہ پوچھنے کی کوشش کریں:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)
                    )

                    Spacer(Modifier.height(12.dp))

                    SuggestionChip(
                        onClick = {
                            chatViewModel.sendMessage(
                                if (isEnglish) "What is family law?" else "خاندانی قانون کیا ہے؟"
                            )
                        },
                        label = {
                            Text(
                                text = if (isEnglish) "What is family law?" else "خاندانی قانون کیا ہے؟",
                                color = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)
                                fontSize = 14.sp
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color(0xFFF5F5F5), // Light gray/white background
                            labelColor = Color(0xFF4440B7)      // Main color: rgb(68, 64, 183)
                        ),
                        shape = RoundedCornerShape(20.dp), // Smooth chip shape
                        border = BorderStroke(1.dp, Color(0xFF4440B7)) // Main color border
                    )


                    Spacer(Modifier.height(10.dp))

                    SuggestionChip(
                        onClick = {
                            chatViewModel.sendMessage(
                                if (isEnglish) "Tell me about labor rights" else "مجھے لیبر حقوق کے بارے میں بتائیں"
                            )
                        },
                        label = {
                            Text(
                                text = if (isEnglish) "Tell me about labor rights" else "لیبر حقوق کے بارے میں بتائیں",
                                fontSize = 14.sp,
                                color = Color(0xFF4440B7) // Main color: rgb(68, 64, 183)
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color(0xFFF5F5F5), // Light gray/white background
                            labelColor = Color(0xFF4440B7)      // Main color: rgb(68, 64, 183)
                        ),
                        shape = RoundedCornerShape(20.dp), // ✅ Smooth chip edges
                        border = BorderStroke(1.dp, Color(0xFF4440B7)) // Main color border
                    )

                    Spacer(Modifier.height(40.dp))
                }

            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 12.dp)
                ) {
                    items(messages) { message ->
                        MessageBubble(message)
                    }
                }
            }
        }
    }

    /** Language Selection Dialog */
    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = {
                Text(
                    if (isEnglish) "Select Language" else "زبان منتخب کریں",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4440B7) // Main color: rgb(68, 64, 183)
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    // English Button
                    OutlinedButton(
                        onClick = {
                            chatViewModel.setLanguage("en")
                            showLanguageDialog = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color(0xFF4440B7)), // Main color border
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White, // White background
                            contentColor = Color(0xFF4440B7) // Main color text
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("English", fontSize = 16.sp)
                    }

                    // Urdu Button
                    OutlinedButton(
                        onClick = {
                            chatViewModel.setLanguage("ur")
                            showLanguageDialog = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color(0xFF4440B7)), // Main color border
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White, // White background
                            contentColor = Color(0xFF4440B7) // Main color text
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("اردو", fontSize = 16.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { showLanguageDialog = false },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF4440B7) // Main color for close button
                    )
                ) {
                    Text(if (isEnglish) "Close" else "بند کریں")
                }
            },
            containerColor = Color.White // White dialog background
        )
    }


    /** Category Dialog */
    if (showCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showCategoryDialog = false },
            title = {
                Text(
                    text = if (isEnglish) "Legal Categories" else "قانونی زمرے",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4440B7) // Main color: rgb(68, 64, 183)
                )
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LegalCategory.entries.forEach { category ->
                        OutlinedButton(
                            onClick = {
                                val prompt = if (isEnglish)
                                    "Tell me about ${category.displayNameEn}"
                                else
                                    "${category.displayNameUr} کے بارے میں بتائیں"

                                chatViewModel.sendMessage(prompt)
                                showCategoryDialog = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            border = BorderStroke(1.dp, Color(0xFF4440B7)), // Main color border
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White, // White background
                                contentColor = Color(0xFF4440B7) // Main color text
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (isEnglish) category.displayNameEn else category.displayNameUr,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { showCategoryDialog = false },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF4440B7) // Main color for close button
                    )
                ) {
                    Text(if (isEnglish) "Close" else "بند کریں")
                }
            },
            containerColor = Color.White, // White dialog background
            shape = RoundedCornerShape(18.dp)
        )
    }


    /** Logout Dialog */
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = if (isEnglish) "Logout" else "لاگ آؤٹ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4440B7) // Main color: rgb(68, 64, 183)
                )
            },
            text = {
                Text(
                    text = if (isEnglish) "Are you sure you want to logout?" else "کیا آپ واقعی لاگ آؤٹ کرنا چاہتے ہیں؟",
                    fontSize = 15.sp,
                    color = Color(0xFF4440B7) // Main color text
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        authViewModel.logout()
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4440B7), // Main color: rgb(68, 64, 183)
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isEnglish) "Yes" else "ہاں")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showLogoutDialog = false },
                    border = BorderStroke(1.dp, Color(0xFF4440B7)), // Main color border
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White, // White background
                        contentColor = Color(0xFF4440B7) // Main color text
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isEnglish) "Cancel" else "منسوخ")
                }
            },
            containerColor = Color.White, // White dialog background
            shape = RoundedCornerShape(18.dp)
        )
    }

}