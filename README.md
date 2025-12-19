# ⚖️ Legal AI Assistant – Android Application

📋 **Table of Contents**
- [Overview](#-overview)
- [Problem Statement](#-problem-statement)
- [Key Features](#-key-features)
- [Screenshots](#-screenshots)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [System Requirements](#-system-requirements)
- [Installation & Setup](#-installation--setup)
- [Security & Privacy](#-security--privacy)
- [Future Enhancements](#-future-enhancements)
- [Academic Relevance](#-academic-relevance)
- [Project Structure](#-project-structure)
- [License](#-license)
- [Contributing](#-contributing)
- [Support](#-support)

---

## 📖 Overview
**Legal AI Assistant** is an innovative Android application designed to make legal information accessible to everyone, regardless of their background. By leveraging advanced Artificial Intelligence, the app provides users with instant answers to legal queries in simple, conversational language. Users can interact with the app using both text-based chat and voice commands, making it convenient and user-friendly.
The application aims to bridge the gap between complex legal jargon and everyday understanding by providing:
Interactive Chat Interface: Users can ask legal questions naturally, and the AI responds with clear and concise explanations.
Voice-Based Assistance: Speak your queries and receive spoken or text-based responses, enabling hands-free operation.
Comprehensive Legal Knowledge: Covers various areas of law such as civil, criminal, family, property, and consumer law.
Simplified Legal Concepts: Breaks down complicated legal terminology into plain, understandable language.
Real-Time Responses: Provides instant answers to help users make informed decisions without delays.
Resource Links: Suggests relevant legal documents, government portals, and reference materials for deeper understanding.
User-Friendly Interface: Designed with a simple and intuitive layout for easy navigation by users of all ages.
Secure and Private: Ensures user queries and data remain confidential and are not shared externally.
This app is ideal for individuals seeking quick legal guidance, students studying law, and anyone interested in understanding their rights and responsibilities without the need for a lawyer for basic inquiries.

<p align="center">
  <img src="screenshots/Capture.PNG" width="200" alt="Legal AI Assistant Logo"/>
</p>

⚠️ **Disclaimer**  
This application provides legal information for educational and informational purposes only and does not replace professional legal advice. Always consult a licensed legal professional for official guidance.

---

## 📌 Problem Statement
Access to legal information is often limited due to:

- Complex legal language  
- High consultation costs  
- Lack of awareness of legal rights  
- Difficulty understanding legal procedures  

This application aims to bridge the gap between legal knowledge and the general public using AI technology.

---
## 🎥 App Demo Video

<p align="center">
  <a href="https://youtu.be/tNlRmMEVF8g" target="_blank">
    <img src="https://img.youtube.com/vi/tNlRmMEVF8g/0.jpg" width="800"/>
  </a>
</p>

## 🚀 Key Features

### 🧠 AI-Powered Legal Chat
- Ask legal questions in natural language  
- Context-aware responses using **Google Gemini AI**  
- Simple and structured answers  

### 💬 Text-Based Chat Interface
- Modern UI built with **Jetpack Compose**  
- Persistent chat history  
- Smooth and responsive experience  

### 🎙 Voice Interaction
- Speech-to-Text for user queries  
- Text-to-Speech for AI responses  
- Hands-free usage support  

### 🔐 User Management (Optional)
- Firebase Authentication  
- Secure login and sessions  
- Cloud-based user data  

### 💾 Data Storage
- Room database for local storage  
- DataStore Preferences for settings  
- Firebase Firestore (optional cloud storage)  

---

## 🖼 Screenshots
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.16.00 PM.jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.16.00 PM (1).jpeg" width="250" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM (2).jpeg" width="250" alt="Settings Screen"/>
</p>
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM.jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM.jpeg" width="250" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.59 PM (1).jpeg" width="250" alt="Settings Screen"/>
</p>
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.58 PM.jpeg" width="250" alt="Chat Screen"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.58 PM (1).jpeg" width="250" alt="Voice Mode"/>
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.57 PM.jpeg" width="250" alt="Settings Screen"/>
</p>
<p align="center">
  <img src="screenshots/WhatsApp Image 2025-12-19 at 5.15.57 PM (2).jpeg" width="250" alt="Chat Screen"/>
</p>

---

## 🏗 System Architecture

### High-Level Architecture
<p align="center">
  <img src="screenshots/legal-ai-architecture.png" width="550" alt="Legal AI Assistant Logo"/>
</p>


## Project Description
The Legal AI Chatbot is an Android application...

### AI Workflow
1. User submits a legal query (text or voice)  
2. Voice input is converted to text  
3. Query is sent to the Gemini AI model  
4. AI processes and generates a response  
5. Response appears in the chat interface  
6. Optional voice output via Text-to-Speech  

---

## 🛠 Tech Stack

### Android Development
- **Language:** Kotlin (Primary), Java (Limited)  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** MVVM  
- **State Management:** ViewModel + StateFlow  

### AI & Networking
- Retrofit & OkHttp  
- Google Gemini AI SDK  

### Storage & Backend
- Room Database  
- DataStore Preferences  
- Firebase Authentication & Firestore (Optional)  

### Other Libraries
- Coil (Image Loading)  
- Android Speech API  
- Android Text-to-Speech Engine  

---

## 📋 System Requirements

### Development Environment
- Android Studio Narwhal 3 Feature Drop (2025.1.3+)  
- JDK 17 or higher  

### Android Configuration
- **minSdk:** 26 (Android 8.0)  
- **targetSdk:** 34 (Android 14)  
- **compileSdk:** 34  

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Ahsaniss/Legal-AI-Assistant.git
cd Legal-AI-Assistant
2️⃣ Configure Gemini API Key
Create or edit local.properties in the root directory:

properties

GEMINI_API_KEY=YOUR_API_KEY_HERE
⚠️ Do not commit local.properties to GitHub

3️⃣ Firebase Setup (Optional)
Create a Firebase project

Add Android app with correct applicationId

Download google-services.json

Place it inside the app/ directory

4️⃣ Build & Run
Open project in Android Studio

Sync Gradle files

Build the project

Run on emulator or physical device

🔐 Security & Privacy
API keys stored securely in local.properties

Firebase security rules protect user data

No sensitive legal data stored without consent

Encrypted AI API communication

🔮 Future Enhancements
Legal document upload & analysis (PDF, DOCX)

Multi-language support

Offline AI fallback

Lawyer / Client role-based access

Court case tracking system

Jurisdiction-specific AI tuning

🎓 Academic Relevance
This project demonstrates:

AI integration in mobile applications

Voice-based human–computer interaction

Secure Android app architecture (MVVM)

Real-world legal problem solving

Suitable for:

Final Year Projects (FYP)

Android & AI portfolios

Research and internship evaluations

🗂 Project Structure
bash
Copy code
Legal-AI-Assistant/
├── app/
│   ├── src/main/java/com/example/legalaiassistant/
│   │   ├── data/           # Data layer
│   │   ├── domain/         # Business logic
│   │   ├── presentation/   # UI & ViewModels
│   │   ├── di/             # Dependency Injection
│   │   └── utils/          # Utilities
│   ├── res/
│   └── AndroidManifest.xml
├── screenshots/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
📄 License
All Rights Reserved © 2025 Muhammad Ahsan Raza

🤝 Contributing
This is a personal project. Suggestions and constructive feedback are welcome while respecting the project scope and license.

📞 Support
Check existing GitHub issues

Review project documentation

Ensure proper API key configuration
---






