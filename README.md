# ⚖️ Legal AI Assistant (Android)

Legal AI Assistant is an advanced Android application that leverages Artificial Intelligence to provide users with accessible legal information through an interactive chat and voice-based interface. The application is designed to simplify complex legal concepts and allow users to ask legal questions in natural language, either by typing or speaking.

This project focuses on combining modern Android development, AI-powered natural language understanding, and voice technologies to create a smart, user-friendly legal assistance system.

> **⚠️ Disclaimer:** This application provides legal information for educational and informational purposes only and does not replace professional legal advice.

## 📌 Problem Statement

Access to legal information is often limited due to:
*   Complexity of legal language
*   High consultation costs
*   Lack of awareness of basic legal rights

Many individuals struggle to understand legal procedures, documents, or terminology. There is a need for an intelligent system that can bridge the gap between legal knowledge and the general public using modern technology.

## 🎯 Project Objectives

*   Provide instant AI-based responses to legal queries
*   Enable voice-based interaction for better accessibility
*   Simplify legal information using natural language
*   Maintain user privacy and secure data handling
*   Build a scalable and modular Android architecture

## 🚀 Key Features

### 🧠 AI-Powered Legal Chat
*   Ask legal questions in natural language
*   Context-aware AI responses
*   Structured and easy-to-understand replies

### 💬 Text-Based Chat Interface
*   Clean and modern chat UI
*   Message history support
*   Smooth scrolling conversation view

### 🎙 Voice Interaction
*   Speech-to-text input using mic button
*   Optional text-to-speech (TTS) responses
*   Hands-free interaction mode

### 🔐 User Management (Optional)
*   Firebase Authentication
*   Secure user sessions
*   Firestore-based data storage

### 💾 Local & Cloud Storage
*   DataStore Preferences for settings
*   Room database for local persistence
*   Firestore for cloud-based data (if enabled)

## 🖼 Screenshots (UI Preview)

📌 Add screenshots after running the app

![Chat Screen](screenshots/chat_screen.png) ![Voice Mode](screenshots/voice_mode.png) ![Settings Screen](screenshots/settings.png)

**📁 Recommended folder structure:**
```
screenshots/
├── chat_screen.png
├── voice_mode.png
└── settings.png
```

## 🏗 System Architecture

### High-Level Architecture
```
User
 │
 │ Text / Voice Input
 ▼
Android UI (Jetpack Compose)
 │
 │ API Request
 ▼
AI Service Layer (Gemini API)
 │
 │ AI Response
 ▼
ViewModel → UI State
 │
 │ Optional Storage
 ▼
Room / Firestore
```

### 🧩 AI Workflow
1.  User enters a legal query (text or voice)
2.  Voice input is converted to text (Speech-to-Text)
3.  Text query is sent to the Gemini AI model
4.  AI processes and generates a legal response
5.  Response is displayed as text
6.  If voice mode is enabled, response is converted to speech (TTS)

### 🎙 Voice Mode – UX Flow
1.  Tap microphone button to activate voice input
2.  Visual mic animation indicates recording
3.  Captured speech is converted into text
4.  AI generates response
5.  If voice reply mode is enabled, AI response is spoken aloud
6.  User can switch voice mode on/off anytime

## 🛠 Tech Stack

### Android
*   **Language:** Kotlin (primary), Java (limited)
*   **UI Framework:** Jetpack Compose (Material 3)
*   **Architecture:** MVVM
*   **State Management:** ViewModel + StateFlow

### Networking & AI
*   **API Client:** Retrofit + OkHttp
*   **AI Model:** Google Gemini (com.google.ai.client.generativeai)

### Storage
*   **Local:** Room Database, DataStore Preferences
*   **Cloud:** Firebase Firestore (optional)

### Other Libraries
*   **Image Loading:** Coil
*   **Authentication:** Firebase Auth
*   **Text-to-Speech:** Android TTS
*   **Speech Recognition:** Android Speech API

## 📋 System Requirements

### Development Environment
*   Android Studio Narwhal 3 Feature Drop | 2025.1.3
*   JDK 17

### Android Configuration
*   **minSdk:** 26
*   **targetSdk:** 34
*   **compileSdk:** 34

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository
```bash
git clone https://github.com/Ahsaniss/Legal-AI-Assistant.git
cd Legal-AI-Assistant
```

### 2️⃣ Configure Gemini API Key
Create or edit `local.properties` (root directory):
```properties
GEMINI_API_KEY=YOUR_API_KEY_HERE
```
> ❗ Do NOT commit `local.properties` to GitHub.

### 3️⃣ Firebase Setup (Optional)
1.  Create a Firebase project
2.  Add Android app with correct `applicationId`
3.  Download `google-services.json`
4.  Place it inside the `app/` folder

### ▶ Build & Run
1.  Open project in Android Studio
2.  Sync Gradle files
3.  Run app on emulator or physical device

## 🔐 Security & Privacy Considerations
*   API keys are stored securely in `local.properties`
*   Sensitive files are excluded using `.gitignore`
*   User data is handled via Firebase security rules
*   No legal data is permanently stored without consent

## 🔮 Future Enhancements
*   Legal document upload & analysis (PDF, DOCX)
*   Multi-language support
*   Offline AI fallback responses
*   Role-based access (Lawyer / Client)
*   Court case tracking system
*   Legal chatbot fine-tuned on local laws

## 🎓 Academic Relevance
This project demonstrates:
*   AI integration in mobile applications
*   Voice-based human–computer interaction
*   Secure Android app architecture
*   Real-world problem solving using AI

**Suitable for:**
*   Final Year Project (FYP)
*   Research demonstration
*   AI & Android portfolio

## ⚠️ Disclaimer
This application is not a substitute for professional legal services. The information provided is generated by an AI model and may not always be accurate or up to date. Users should consult a licensed legal professional for official legal advice.

## 📄 License
All Rights Reserved © 2025 Muhammad Ahsan Raza

