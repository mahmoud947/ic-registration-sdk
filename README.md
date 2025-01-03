
# ICR SDK

The **ICR SDK** is a modular and scalable library built using **Clean Architecture**. It provides functionalities for user registration, smile detection, and customizable themes while maintaining clear separation of concerns through its multi-module structure.

---

## Multi-Module Structure

The SDK is designed as a **multi-module project**, where each module serves a specific purpose:

1. **`icr-core`**: Contains core utilities, constants, and shared logic used across modules.
2. **`icr-data`**: Handles data operations, including network requests and database interactions.
3. **`icr-domain`**: Encapsulates business logic and use cases.
4. **`icr-di`**: Handles dependency injection, ensuring modules are loosely coupled.
5. **`icr-ui`**: Provides UI components built with Jetpack Compose for a modern user experience.
6. **`icr-sdk`**: Combines all the modules into a cohesive SDK ready for integration into your app.

### Dependency Graph

```
icr-core <---+
icr-data <---+--- icr-domain <--- icr-ui
icr-di   <---+
icr-ui   <---+
```

This modular approach ensures:
- **Reusability**: Modules can be used independently in other projects.
- **Scalability**: Easily extendable for additional features or layers.
- **Maintainability**: Clear separation of concerns makes it easier to debug and maintain.

---

## Features

- **User Registration**: Simplifies capturing and validating user details (username, email, phone, etc.).
- **Smile Detection**: Automatically or manually captures user images on detecting smiles.
- **Customizable Themes**: Supports light and dark modes with fully customizable colors.
- **Localization**: Includes support for English and Arabic.

---

## Installation

To integrate the SDK, you need to add the following Maven repositories to your build.gradle file at the project level:
```gradle
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}
```
Then, add the SDK dependency to the module-level build.gradle file:

```gradle
//last_version: 1.7.4-alpha
implementation 'com.github.mahmoud947.ic-registration-sdk:icr-sdk:last_version'
```

---

## Usage

### Step 1: Initialize the SDK

Use the `ICRSDK.Builder` to initialize the SDK with your desired configurations:

```kotlin
val icrSdk = ICRSDK.Builder()
    .context(context)
    .setLanguage(ICRLanguage.ENGLISH) // Choose language (ENGLISH or ARABIC)
    .setDarkMode(true) // Enable dark mode
    .setAutoCapture(true) // Default is true
    .setDarkThemeColors(
        primary = Color(0xFF6200EE),
        secondary = Color(0xFF03DAC6),
        background = Color(0xFF121212)
    )
    .setLightThemeColors(
        primary = Color(0xFFFF5722),
        secondary = Color(0xFF607D8B),
        background = Color(0xFFFFFFFF)
    )
    .build()
```

---

## Configuration Setters

| Setter                  | Description                                                                 |
|-------------------------|-----------------------------------------------------------------------------|
| `setLanguage(language)` | Sets the language for the SDK. Options: `ICRLanguage.ENGLISH` or `ICRLanguage.ARABIC`. |
| `setDarkMode(isDarkMode)` | Enables or disables dark mode for the SDK. Accepts a boolean value.       |
| `setAutoCapture(isAuto)` | Enables or disables automatic image capture during smile detection. Default is `true`. |
| `setDarkThemeColors(primary, secondary, background)` | Customizes the primary, secondary, and background colors for dark mode. |
| `setLightThemeColors(primary, secondary, background)` | Customizes the primary, secondary, and background colors for light mode. |

---

### Step 2: Start the Registration Process

```kotlin
icrSdk.validateNewUser(context = this, listener = object : ICRSDKListener {
    override fun onValidationSuccess(result: ICRSdkResult) {
        // Handle successful validation
    }

    override fun onValidationFailure(exception: Exception) {
        // Handle validation failure
    }

    override fun onCancelByTheUser() {
        // Handle user cancellation
    }
})
```

---

## Clean Architecture

The SDK uses **Clean Architecture** principles:

1. **Domain Layer (`icr-domain`)**:
   - Contains business rules and use cases.
   - Operates independently of other layers.

2. **Data Layer (`icr-data`)**:
   - Manages APIs, local databases, and data sources.
   - Provides the necessary data to the domain layer.

3. **UI Layer (`icr-ui`)**:
   - Built with Jetpack Compose for responsive and modern UI components.
   - Interacts with ViewModels that belong to the domain layer.

4. **Core Layer (`icr-core`)**:
   - Contains shared utilities and constants.

5. **Dependency Injection Layer (`icr-di`)**:
   - Manages dependency injection using frameworks like Hilt or Koin.

### Diagram

```
icr-core <---+
icr-data <---+--- icr-domain <--- icr-sdk
icr-di   <---+
icr-ui   <---+
```

---

## Example

Here’s how to integrate the SDK for user registration and smile detection:

```kotlin
val icrSdk = ICRSDK.Builder()
    .context(context)
    .setLanguage(ICRLanguage.ENGLISH)
    .setDarkMode(false)
    .setAutoCapture(true)
    .build()

icrSdk.validateNewUser(context = this, listener = object : ICRSDKListener {
    override fun onValidationSuccess(result: ICRSdkResult) {
        // Handle successful validation
    }

    override fun onValidationFailure(exception: Exception) {
        // Handle validation failure
    }

    override fun onCancelByTheUser() {
        // Handle user cancellation
    }
})
```



## Image 
| Image 1        | Image 2        | Image 3        | Image 4        |
|-----------------|----------------|----------------|----------------|
| ![WhatsApp Image 2024-12-02 at 15 32 16](https://github.com/user-attachments/assets/36283542-e0c0-43d1-803a-d90ab8e27894) | ![WhatsApp Image 2024-12-02 at 15 32 166](https://github.com/user-attachments/assets/8b9c3c13-165d-43f9-b338-9a678d8ac7e8) | ![WhatsApp Image 2024-12-02 at 15 32 15](https://github.com/user-attachments/assets/aa18a4c4-fe31-42e4-bb88-09a545d8569a) | ![WhatsApp Image 2024-12-02 at 15 51 00](https://github.com/user-attachments/assets/3cdf7994-12de-4f38-8a2b-364ff2927674)
|


## Video 



https://github.com/user-attachments/assets/521e5d25-f371-4ace-9681-c933754ef07d



