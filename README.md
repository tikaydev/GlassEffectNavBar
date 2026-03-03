# Glass Effect Navigation Bar

A Kotlin Multiplatform (KMP) multi-module application targeting **Android**, **iOS**, and **Desktop (JVM)**. It uses **Compose Multiplatform** for shared UI and follows a modularized architecture for scalability and maintainability.

## 🏗️ Architecture

The project follows Clean Architecture principles with a **feature-based multi-module architecture**:

- **Multi-Module Design**: Separated into core modules and feature modules for scalability
- **MVI Pattern**: Model-View-Intent with unidirectional data flow (UDF)
- **Repository Pattern**: Data layer abstraction across modules
- **Dependency Injection**: Koin for DI coordination across all modules
- **Compose Multiplatform**: Shared UI components in dedicated modules
- **Room Database**: Local database management
- **DataStore**: Typed data storage for preferences and pagination state persistence

## Project Structure

The project is organized into several modules to separate concerns and enable code sharing:

### 📱 Platform Applications (Entry Points)
* **[/androidApp](./androidApp)**: The native Android application module. It contains the `MainActivity`, Android Manifest, and platform-specific configurations.
* **[/iosApp](./iosApp)**: The native iOS project (Xcode). It provides the entry point for the iOS application and is where SwiftUI components or iOS-specific lifecycle logic are managed.
* **[/desktopApp](./desktopApp)**: The JVM-based desktop application module. It contains the `main` entry point for running the app on Windows, macOS, or Linux.

###  Shared Module
* **[/composeApp](./composeApp)**: The primary shared module for Compose Multiplatform. It coordinates the shared UI and connects the different feature modules.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

### 🧩 Core & Features
* **[/feature](./feature)**: Contains individual feature modules (e.g., `:feature:auth`, `:feature:home`). Each feature is self-contained, promoting high cohesion and reusability.
* **[/core](./core)**: Contains low-level modules that other modules depend on (e.g., `:core:data`, `:core:network`, `:core:designsystem`).

### 🛠️ Infrastructure
* **[/build-logic](./build-logic)**: Contains **Convention Plugins** and dependencies defined in **[libs.versions.toml](./gradle/libs.versions.toml)** to manage Gradle configurations across the project. This ensures consistency in dependency management and compiler settings.

---

## 📁 Project Structure

```
P-Vault/
├── androidApp/           # Android application module (APK entry point)
│   └── src/main/         # Android-specific code (MainActivity, resources)
│
├── composeApp/           # Shared Compose Multiplatform library
│   ├── androidMain/      # Android-specific code (DI)
│   ├── commonMain/       # Shared app code (App.kt, navigation, DI)
│   ├── desktopMain/      # JVM-specific code (DI)
│   └── iosMain/          # iOS-specific code (MainViewController, DI)
│
├── core/                 # Core shared modules
│   ├── common/           # Common utilities and coroutine dispatchers
│   ├── data/             # Repository implementations and data layer
│   ├── database/         # Room database with cross-platform drivers
│   ├── datastore/        # DataStore preferences for pagination state & settings
│   ├── designsystem/     # Shared UI components and design system
│   ├── domain/           # Repository interface definitions
│   ├── model/            # Domain models (Movie, TvShow, AppResult)
│   └── network/          # Ktor HTTP client and TMDB API integration
│
├── features/             # Feature-specific modules
│   ├── auth/             # Authentication related screens
│   └── home/             # Home screen
│
├── build-logic/          # Custom Gradle convention plugins
├── iosApp/               # iOS app wrapper with Xcode project
├── gradle/               # Gradle configuration and version catalog
└── Configuration files   # Build scripts, API keys, properties
```
---

## 🛠️ Technology Stack

### Shared
- **Kotlin Multiplatform** (2.3.0) - Multi-platform development
- **Compose Multiplatform** - UI framework
- **Koin** - Dependency injection with Compose and ViewModel support
- **Room** - Local database with SQLite bundled driver
- **DataStore** - Typed data storage for preferences and pagination state
- **Ktor** - HTTP client for API calls with OkHttp (Android) and Darwin (iOS) engines
- **Kotlin Coroutines & Flow** - Asynchronous programming and reactive streams
- **Coil** - Image loading with Compose integration, Ktor network driver, and SVG support
- **Kotlinx Serialization** - JSON serialization for API responses
- **Navigation 3** - Navigation 3 framework with Koin integration
- **Material 3** - Design system components
- **KSP** - Kotlin Symbol Processing for Room code generation
- **BuildKonfig** - Build-time constant generation for secrets and configuration
---

## 🚀 Getting Started
### Prerequisites
- **Android Studio** - Ladybug or later with KMP plugin
- **Xcode 15+** (for iOS development)
- **JDK 17-21**
- **Kotlin Multiplatform Mobile plugin**
- **Minimum SDK**: Android 24, iOS 13+
- **Target SDK**: Android 36

### 🔑 API Key and Secrets Setup

Configure API keys and credentials using BuildKonfig.

#### Step 1: Get API Key or Credentials(secrets)

#### Step 2: Configure API Key (secrets.properties)

The project uses **BuildKonfig** to generate build-time constants from `secrets.properties`. This approach works seamlessly for both Android and iOS.

1. **Create secrets.properties file** in the project root directory:
   ```bash
   touch secrets.properties
   ```

2. **Add your API key** to `secrets.properties`:
   ```properties
   X_API_KEY=your_actual_api_key_here
   BASE_URL = your_base_url
   ENCRYPTION_KEY = your_encryption_key
   ```

   Replace `your_actual_api_key_here`, `your_base_url`, `your_encryption_key` with your actual keys.

3. **Verify .gitignore** - The file should already be excluded from Git:
   ```gitignore
   /secrets.properties
   ```

#### Step 3: Build the Project

BuildKonfig will automatically generate the `BuildKonfig` object containing your API key during the Gradle build process. The generated constant is available to both Android and iOS platforms.

**How it works:**
- BuildKonfig generates a `BuildKonfig.BASE_URL`, `BuildKonfig.X_API_KEY` constants
- The constants are accessible across all platforms without platform-specific code

> ⚠️ **Important**:
> - Never commit `secrets.properties` to version control (it's gitignore-d)
> - If the API key is not set, you'll see a build warning but the app will compile
> - API calls will fail at runtime if the key is missing

---

### Running the Project

#### Android
- Run from Android Studio using the **androidApp** configuration.
- Terminal on macOS/Linux:
  ```shell
  # Install debug build on connected device
  ./gradlew :androidApp:installDebug

  # Or assemble APK without installing
  ./gradlew :androidApp:assembleDebug
  ```
 
- Terminal on Windows:
  ```shell
  # Install debug build on connected device
  .\gradlew.bat :androidApp:installDebug
  
  # Or assemble APK without installing
  .\gradlew.bat :androidApp:assembleDebug
  ```

#### Desktop (JVM)
- Run from the IDE using the **desktopApp** configuration.
- Terminal on macOS/Linux:
    ```shell
    ./gradlew :desktopApp:run
    ```
- on Windows
  ```shell
  .\gradlew.bat :desktopApp:run
  ```
If you have problems building your application, it is recommended to select JDK version ```17```. To do this:
* Select JDK version ```17``` at path ```File -> Project Structure -> Project -> SDK```, and also ```Language level``` under it.
* At path ```Settings -> Build -> Compiler -> Java compiler -> Project bytecode version``` select ```17```.
* At path ```Settings -> Build -> Build tools -> Gradle -> Gradle JVM``` select ```Project JDK```.

*Note: you can build your application only for the OS you are currently using. If, for example, you use Windows and need to create a build for Linux, consider doing it in a virtual machine.*

#### iOS
1. Open the `/iosApp` directory in **Xcode**.
2. Select a simulator/device and click **Run**.
3. Alternatively, use the **iosApp** run configuration in Android Studio (macOS only).

---
### Building
```bash
# Clean and build all modules
./gradlew clean build

# Build Android app
./gradlew :androidApp:build

# Build shared library
./gradlew :composeApp:build
```
---

## 🏛️ Architecture Overview

### Repository Pattern (Simplified)
The project uses a simplified repository pattern where:
- **Repository**: Passive data provider that returns `Flow<List<T>>` and handles cache invalidation
- **ViewModel**: Active state coordinator that manages loading/error states and UI logic
- **Clear Separation**: Repository maintains data integrity, ViewModel manages UI state

### Data Storage Strategy
- **Preferences**: DataStore for app settings, user preferences and pagination state persistence

### Architecture Layers
1. **Presentation Layer** - Compose UI, ViewModels, Navigation
2. **Domain Layer** - Business logic, Use cases (Repository interfaces)
3. **Data Layer** - Repository implementations, Data sources, Network, Database

## 🔧 Development Setup

### Architecture Details
- **Multi-Module Clean Architecture** with Repository pattern across modules
- **MVI (Model-View-Intent) with Simplified Repositories**:
    - Model: Immutable UI state representing the screen
    - View: Compose UI that renders state and dispatches events
    - Intent: User events handled via `onEvent()`
    - Repository: Passive data provider returning simple `Flow<List<T>>`
    - ViewModel: Coordinates state and handles loading/error logic
    - Clear separation: Data layer maintains integrity, Presentation layer manages UI state
- **Reactive UI** with Flow-based data streaming between modules
- **Error Handling Strategy**:
    - Initial load errors: Block UI with error screen
- **Platform-specific configs** minimized with BuildKonfig (expect/actual used only where necessary)
- **Feature-based modular design** with clear separation of concerns
- **Module isolation** with well-defined APIs and dependency injection
- **Modern KMP Architecture**: Uses `com.android.kotlin.multiplatform.library` plugin with dedicated platform entry points

### Build Configuration
- **Gradle Version Catalogs** (`gradle/libs.versions.toml`) - Centralized dependency management
- **Custom Convention Plugins** in `build-logic/convention/` module:
    - **glass.kotlin.multiplatform**: Configures KMP with `com.android.kotlin.multiplatform.library` plugin, iOS ARM64/Simulator targets, and expect/actual classes compiler flag
    - **pvault.kotlin.composeMultiplatform**: Adds all Compose Multiplatform dependencies
    - **pvault.kotlin.composeMultiplatformFeature**: Adds dependencies and modules needed by feature modules
    - **Shared Android Config**: Compile/target SDK 36, min SDK 24, JVM target 17
- **Modern Android Library Plugin**: Uses `com.android.kotlin.multiplatform.library` for better KMP integration (replaces deprecated `com.android.library`)
- **BuildKonfig** for build-time constant generation (API keys, app version)
- **KSP** for Room database code generation across platforms
- **DataStore** for preferences and app state persistence
- **Compose Resources** for shared string resources with 
- **Platform-specific** configurations using expect/actual pattern (minimized with BuildKonfig)
- **Room Database** with SQLite bundled driver for offline support
- **Dedicated Android App Module**: `androidApp` wraps the shared `composeApp` library

### Troubleshooting
- **Java Version Issues**: Use JDK 17-21. Java 25 has compatibility issues
- **Build Failures**: Run `./gradlew clean` and retry
- **iOS Simulator**: Use ARM64 simulator on Apple Silicon Macs
- **API Key Issues**:
    - Verify `secrets.properties` exists in project root with valid TMDB API key
    - Check for build warnings about missing API key
    - Rebuild the project after adding/modifying the API key
- **BuildKonfig Issues**: Clean build cache if constants are not updating: `./gradlew clean build`
---

## Resources
- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform Resources](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-getting-started.html)