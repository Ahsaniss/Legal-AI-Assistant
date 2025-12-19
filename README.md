# Legal AI Assistant - Android Application

📋 **Table of Contents**
- [Overview](#overview)
- [Problem Statement](#problem-statement)
- [Key Features](#key-features)
- [Screenshots](#screenshots)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Security & Privacy](#security--privacy)
- [Future Enhancements](#future-enhancements)
- [Academic Relevance](#academic-relevance)
- [License](#license)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [Support](#support)

---

## 📖 Overview
**Legal AI Assistant** is an advanced Android application that leverages Artificial Intelligence to provide users with accessible legal information through an interactive chat and voice-based interface. The application simplifies complex legal concepts and allows users to ask legal questions in natural language.

<p align="center">
  <img src="screenshots/app_logo.png" width="200" alt="Legal AI Assistant Logo"/>
</p>

⚠️ **Disclaimer**  
Important: This application provides legal information for educational and informational purposes only and does not replace professional legal advice. AI-generated responses may not always be accurate or up to date. Users should consult a licensed legal professional for official legal advice.

---

## 📌 Problem Statement
Access to legal information is often limited due to:

- Complexity of legal language
- High cost of professional consultation
- Lack of awareness of basic legal rights
- Difficulty understanding legal procedures and documents

This app bridges the gap between legal knowledge and the general public using modern AI technology.

---

## 🚀 Key Features

### 🧠 AI-Powered Legal Chat
- Ask legal questions in natural language
- Context-aware AI responses using Google Gemini
- Structured, easy-to-understand replies

### 💬 Text-Based Chat Interface
- Clean and modern UI built with Jetpack Compose
- Persistent chat history
- Smooth conversation flow

### 🎙 Voice Interaction
- Speech-to-Text input via microphone
- Optional Text-to-Speech (TTS) responses
- Hands-free interaction mode

### 🔐 User Management (Optional)
- Firebase Authentication
- Secure user sessions
- Firestore-based user data storage

### 💾 Storage Solutions
- DataStore Preferences for settings
- Room database for local persistence
- Firestore for cloud storage (optional)

---

## 🖼 Screenshots
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.16.00 PM.jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.16.00 PM (1).jpeg" width="250" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM (2).jpeg" width="250" alt="Settings Screen"/>
</p>
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM (1).jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM.jpeg" width="250" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.58 PM.jpeg" width="250" alt="Settings Screen"/>
</p>
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.58 PM (1).jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.57 PM.jpeg" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.57 PM (2).jpeg" width="250" alt="Settings Screen"/>
</p>

---

## 🏗 System Architecture

### High-Level Architecture
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

yaml
Copy code

### AI Workflow
1. User enters a legal query (text or voice)
2. Voice input is converted to text (Speech-to-Text)
3. Query is sent to the Gemini AI model
4. AI processes the request and generates a response
5. Response is displayed in chat
6. If voice mode is enabled, response is spoken aloud (TTS)

---

## 🛠 Tech Stack

### Android Development
- Language: Kotlin (Primary), Java (Limited)
- UI Framework: Jetpack Compose (Material 3)
- Architecture: MVVM Pattern
- State Management: ViewModel + StateFlow

### Networking & AI
- Networking: Retrofit + OkHttp
- AI Model: Google Gemini (`com.google.ai.client.generativeai`)

### Storage & Backend
- Local Storage: Room, DataStore Preferences
- Cloud Services: Firebase Auth & Firestore (Optional)

### Additional Libraries
- Image Loading: Coil
- Speech-to-Text: Android Speech API
- Text-to-Speech: Android TTS Engine

---

## 📋 System Requirements

### Development Environment
- Android Studio Narwhal 3 Feature Drop | 2025.1.3 or later
- JDK 17 or later

### Android Configuration
- minSdk: 26 (Android 8.0)
- targetSdk: 34 (Android 14)
- compileSdk: 34

---

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository
```bash
git clone https://github.com/Ahsaniss/Legal-AI-Assistant.git
cd Legal-AI-Assistant
2️⃣ Configure Gemini API Key
Obtain API key from Google AI Studio

Create or edit local.properties in the project root:

properties
Copy code
GEMINI_API_KEY=YOUR_API_KEY_HERE
⚠️ Important: Do NOT commit local.properties to version control

3️⃣ Firebase Setup (Optional)
Create a Firebase project at Firebase Console

Add Android app with correct applicationId

Download google-services.json and place it in the app/ directory

4️⃣ Build and Run
Open project in Android Studio

Sync Gradle files

Build project (Build → Make Project)

Run on emulator or physical device

🔐 Security & Privacy
API keys are stored securely in local.properties (excluded from Git)

Firebase rules protect user data

No sensitive legal data is stored without user consent

All communications with Gemini API are encrypted

🔮 Future Enhancements
Legal document upload & analysis (PDF, DOCX)

Multi-language support for diverse users

Offline AI fallback responses

Role-based access (Lawyer / Client modes)

Court case tracking system

Fine-tuned legal AI model for specific jurisdictions

🎓 Academic Relevance
This project demonstrates practical implementation of:

AI integration in mobile applications

Voice-based human–computer interaction

Secure Android app architecture using MVVM

Real-world problem solving using AI technology

Suitable for:

Final Year Projects (FYP)

AI & Android development portfolios

Research & internship evaluations

📄 License
All Rights Reserved © 2025 Muhammad Ahsan Raza

<p align="center"> <b>Built with ❤️ using modern Android development practices</b><br> <img src="https://img.shields.io/badge/Platform-Android-green"/> <img src="https://img.shields.io/badge/Language-Kotlin-blue"/> <img src="https://img.shields.io/badge/AI-Google%20Gemini-orange"/> <img src="https://img.shields.io/badge/Architecture-MVVM-purple"/> </p>
🗂 Project Structure
bash
Copy code
Legal-AI-Assistant/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/legalaiassistant/
│   │   │   │   ├── data/           # Data layer (Room, Repositories)
│   │   │   │   ├── domain/         # Business logic
│   │   │   │   ├── presentation/   # UI Layer (Compose, ViewModels)
│   │   │   │   ├── di/             # Dependency Injection
│   │   │   │   └── utils/          # Utility classes
│   │   │   ├── res/                # Resources
│   │   │   └── AndroidManifest.xml
│   │   └── debug/                  # Debug configurations
│   └── build.gradle.kts
├── gradle/
├── screenshots/                    # App screenshots
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
🤝 Contributing
While this is a personal project, suggestions and feedback are welcome. Please ensure you respect the project's scope and licensing.

📞 Support
For issues, questions, or suggestions:

Check existing GitHub issues

Review the documentation

Ensure proper API key configuration

pgsql
Copy code

---

✅ This **now includes every single text section, icons, images, tables, code blocks, headings, lists, and all your content**, ready to paste into `README.md`.

If you want, I can also **add collapsible sections for screenshots and code blocks** to make it look extra clean on GitHub.  

Do you want me to do that?