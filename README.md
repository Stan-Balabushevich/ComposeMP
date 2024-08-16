MovieApp

MovieApp is a cross-platform application developed using Compose Multiplatform. The app is designed to run on Android, iOS, and Desktop platforms, utilizing modern development practices such as Clean Architecture and MVVM. The app fetches movie data from The Movie Database (TMDb) API, stores favorites locally using Room Database, and provides a seamless user experience across multiple platforms.
Features

    Cross-platform: Developed using Compose Multiplatform, making the app compatible with Android, iOS, and Desktop platforms.
    API Integration: Uses Ktor for making network requests to the TMDb API.
    Local Storage: Implements Room Database for local data storage, adapted for each platform.
    Dependency Injection: Utilizes Koin for managing dependencies across all platforms.
    Architecture: Follows Clean Architecture principles with MVVM (Model-View-ViewModel) design pattern.
    Unit Testing: Contains unit tests for the common codebase, ensuring code quality and reliability.

Technologies Used

    Compose Multiplatform: UI framework for creating user interfaces across Android, iOS, and Desktop.
    Ktor: For making API calls to TMDb.
    Room Database: For local data storage, adapted for Compose Multiplatform.
    Koin: For dependency injection across all platforms.
    Clean Architecture: For a clean separation of concerns.
    MVVM: For structured and manageable UI logic.
    Unit Testing: Written using Kotlin test libraries to ensure code correctness.

Setup Instructions
Prerequisites

    Android Studio
    JDK 11 or later
    Xcode (for iOS, if running on macOS)

Registering for TMDb API Key

To fetch movie data, you need to register for an API key at The Movie Database (TMDb):

    Visit TMDb and create an account if you don't have one.
    Once logged in, navigate to your account settings.
    Go to the API section and click "Create" to generate an API key.
    Copy your API key.

Adding the API Key

To make the app work properly, you need to add the API key to the MovieApiServiceImpl class:

class MovieApiServiceImpl {
companion object {
const val API_KEY = "your_api_key_here"
}
}

Replace "your_api_key_here" with your actual TMDb API key.
Running the App

    Android:
        Open the project in Android Studio.
        Set up an Android emulator or connect a physical device.
        Run the app.

    Desktop:
        From the terminal or Android Studio, run the desktopMain task to start the desktop application.

    iOS (Note: Testing is limited due to lack of a macOS environment):
        Open the project in Xcode (on a macOS machine).
        Set up an iOS simulator or connect a physical device.
        Run the app.

Known Issues

    The app has been tested on Android and Desktop platforms and works as expected. iOS testing was not possible due to the lack of a macOS device.

Contributions

Contributions are welcome! Please feel free to submit a pull request or open an issue to discuss any changes.
License

This project is licensed under the MIT License. See the LICENSE file for more details.