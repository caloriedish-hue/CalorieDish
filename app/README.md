# CalorieDish

## Overview

CalorieDish is an Android food information application that allows users to search for dishes and view their nutritional information, especially calorie content.

The goal of the application is to help users make healthier food choices by quickly checking calorie and nutrition information before ordering or consuming a dish.

The application focuses on **simple food search and nutrition awareness** rather than full diet tracking or meal planning.

---

# User Perspective

> "I search for a dish and check its calorie details before ordering or eating it."

---

# Features

### Food Search

Users can search for food items using a text search bar.

### Voice Search

Users can search food items using **voice input** through Android Speech Recognition.

### Nutritional Information

The app retrieves nutrition information including:

* Calories
* Protein
* Fat
* Carbohydrates

### Text-to-Speech

Users can listen to the nutrition information using Android **Text-to-Speech**.

### Save Favorite Foods

Users can save food items to their personal list.

Saved items are stored in:

```
users/{uid}/savedFoods
```

### Search History

Recent searches are stored locally for quick suggestions.

### Offline Access

Previously fetched nutrition data can be viewed offline using cached data.

---

# Technology Stack

* Kotlin
* Android Jetpack Compose
* Firebase Authentication
* Firebase Firestore
* Edamam Nutrition API
* Android Speech Recognition
* Android Text-to-Speech
* Local data caching

---

# External API

Nutrition data is retrieved from the **Edamam Nutrition API**.

API Documentation
https://api.edamam.com/

The API:

* Accepts a food query
* Returns nutritional information
* Provides ingredient-level nutrition details

No user authentication information is sent to the API.

---

# Mobile Device Features

## Voice Input

Allows users to search food items using voice commands.

## Text-to-Speech

Reads nutritional information aloud for accessibility.

---

# Application Screens

## Splash Screen

Purpose: App initialization and session verification.

Displays:

* App logo
* Loading indicator

Behavior:

* If user is logged in → Home Screen
* If not logged in → Authentication Screen

---

## Authentication Screen

Features:

* Email login
* User registration
* Input validation

Behavior:

* Successful login redirects to Home Screen
* Logout redirects back to this screen

---

## Home Screen

Displays:

* Food search bar
* Voice search button
* Search history suggestions

Users can search food items using text or voice.

Selecting a food item opens the **Calorie Details Screen**.

---

## Calorie Details Screen

Displays:

* Food name
* Calories
* Protein
* Fat
* Carbohydrates

Features:

* Text-to-Speech button
* Save to favorites

---

## Settings Screen

Contains:

* Logout functionality

Logout redirects users to the Authentication Screen.

---

# Architecture

The project follows a **simple MVVM architecture** suitable for a small Android application.

Each feature generally contains:

* Screen
* ViewModel
* UiState

---

# Project Structure

```
com.caloriedish
│
├── auth
│   ├── AuthScreen.kt
│   ├── AuthViewModel.kt
│   ├── AuthUiState.kt
│   └── AuthEvent.kt
│
├── splash
│   ├── SplashScreen.kt
│   └── SplashViewModel.kt
│
├── home
│   ├── HomeScreen.kt
│   ├── HomeViewModel.kt
│   ├── HomeUiState.kt
│   ├── HomeEvent.kt
│   └── components
│       └── FoodItemCard.kt
│
├── details
│   ├── FoodDetailsScreen.kt
│   ├── FoodDetailsViewModel.kt
│   └── FoodDetailsUiState.kt
│
├── settings
│   └── SettingsScreen.kt
│
├── data
│   ├── repository
│   │   └── FoodRepository.kt
│   ├── remote
│   │   └── FoodApiService.kt
│   └── model
│       └── FoodItem.kt
│
├── utils
│   ├── SpeechRecognizerHelper.kt
│   └── TextToSpeechManager.kt
│
├── navigation
│   ├── Routes.kt
│   └── AppNavigation.kt
│
└── ui
    └── theme
        ├── Color.kt
        ├── Theme.kt
        ├── Shapes.kt
        ├── Dimens.kt
        └── Type.kt
```

---

# UI Theme

The application uses an **Orange + White theme with Green accents**.

### Primary Color

Orange

### Secondary Color

Peach

### Accent Color

Green

### Background

Soft white for a clean and minimal UI.

---

# Data Persistence

### Firebase Authentication

Used for user login and registration.

### Firestore Database

Stores saved food items.

Example structure:

```
users
 └── uid
     └── savedFoods
```

### Local Storage

Used for:

* Search history
* Cached nutrition data

---

# Mandatory Project Requirements

* APK must include an **application icon**
* GitHub repository name: **CalorieDish**
* Repository must be **private**
* Contributors must include:

    * Manager
    * Nikhil
* Logout must redirect to the login screen
* Search results must appear immediately after query

---

# Documentation Requirements

Screenshots must include:

* Emulator previews
* All application screens
* Firebase Authentication console
* Firestore collections
* Local cache implementation
* GitHub repository page
* API usage
* Development challenges
