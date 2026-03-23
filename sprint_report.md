Sprint 1 – 2026/March/08

User Story:
As a user, I want a stable and well-structured app so that I can navigate and use features smoothly.

Work Done:

    Initialized project and configured Firebase (Auth + Firestore)
    Added google-services.json and required dependencies (Room, Retrofit, Navigation, ViewModel)
    Implemented custom theme (colors, dimensions, shapes)
    Set up app navigation using AppNavigationHost
    Created Application class for dependency initialization
    Built DependencyContainer for managing app dependencies
    Defined navigation routes

Outcome:

    Project is fully initialized with backend integration
    Navigation structure is in place
    App has a consistent UI design system
    Dependency management is organized and scalable 

Sprint 2 – 2026/March/12

User Story:
As a user, I want to sign up and log in so that I can access personalized features.

Work Done:

    Implemented SignUp flow (UI, ViewModel, UiState)
    Added user profile support and sign-out functionality
    Built Settings screen with ViewModel and UI state
    Implemented Login screen with ViewModel
    Created reusable UI components (CustomButton, CustomText)

Outcome:

    Users can register and log in successfully
    Settings screen is functional
    Authentication flow is complete and reusable components improve UI consistency

Sprint 3 – 2026/March/17

User Story:
As a user, I want a smooth app structure so that data loads efficiently and screens work correctly.

Work Done:

    Implemented repository layer (AuthRepository)
    Added authentication use cases (Login, Logout, Register)
    Built Bottom Navigation (BottomBar, BottomNavigationItem)
    Created Home screen with ViewModel and UI state
    Implemented Detail screen with ViewModel and argument parsing
    Added TopBars and structured UI states

Outcome:

    Clean architecture (UI → UseCase → Repository) is established
    Navigation between core screens works properly
    Data handling is structured and scalable 

Sprint 4 – 2026/March/20

User Story:
As a user, I want to view recipes and nutritional information so that I can make informed food choices.

Work Done:

    Built Recipe screen with ViewModel and UI state
    Fetched recipe data from database
    Implemented DishRepository and Calorie API service
    Created data pipeline for calorie information retrieval
    Added nutrition models and API response handling
    Implemented Text-to-Speech for nutrition details
    Added food suggestions feature

Outcome:

    Users can view recipes and nutritional data
    External API integration is functional
    App provides interactive features like voice feedback and suggestions

Sprint 5 – 2026/March/23

User Story:
As a user, I want my data (favorites and searches) to persist and sync so that I have a seamless experience across sessions.

Work Done:

    Implemented use cases for favorites and recent searches
    Built local persistence using Room database
    Added Custom Search Bar UI and app branding
    Refactored navigation and improved UI layouts
    Added required permissions (network, audio)
    Integrated Firebase sync for favorite dishes
    Implemented LoadOnLoginUseCase to sync data on login
    Created Firebase data source and JSON mappers

Outcome:

    User data persists locally and syncs with Firebase
    Search and favorites features are fully functional
    UI is polished and navigation is improved
    App is stable, complete, and production-ready









