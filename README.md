# Glass Effect Navigation Bar

A Kotlin Multiplatform (KMP) multi-module application targeting **Android**, **iOS**, and **Desktop (JVM)**. It uses **Compose Multiplatform** for shared UI and follows a modularized architecture for scalability and maintainability.
The objective is to design a custom navigation bar with GlassMorphism (BottomBar and NavRail)

## 🏗️ Architecture

The project follows Clean Architecture principles with a **feature-based multi-module architecture**:

- **Multi-Module Design**: Separated into core modules and feature modules for scalability.
- **Compose Multiplatform**: Shared UI components in dedicated modules.
- **Navigation 3**: redined approach to navigation using type-safe routes and `NavDisplay`.
- **Haze**: Glass effect implementation for navigation components.

## Project Structure

The project is organized into several modules to separate concerns and enable code sharing:

### 📱 Platform Applications (Entry Points)
* **[/androidApp](./androidApp)**: The native Android application module.
* **[/iosApp](./iosApp)**: The native iOS project (Xcode).
* **[/desktopApp](./desktopApp)**: The JVM-based desktop application module.

###  Shared Module
* **[/composeApp](./composeApp)**: The primary shared module for Compose Multiplatform. It coordinates the shared UI and connects the different feature modules.
  - [RootNavGraph.kt](./composeApp/src/commonMain/kotlin/com/tikaydev/glasseffect/navigation/RootNavGraph.kt): Defines the main navigation graph using Navigation 3.
  - [MainScreen.kt](./composeApp/src/commonMain/kotlin/com/tikaydev/glasseffect/navigation/MainScreen.kt): Adaptive layout coordinator for BottomBar and NavRail.

### 🧩 Core & Features
* **[/features](./features)**: Contains individual feature modules.
  - **[home](./features/home)**: Image list and details screens with shared element transitions.
  - **[menu](./features/menu)**: Menu related screens and navigation.
  - **[profile](./features/profile)**: User profile screens and navigation.
  - **[settings](./features/settings)**: App settings screens and navigation.
* **[/core](./core)**: Contains low-level modules.
  - **[designsystem](./core/designsystem)**: Shared UI components, themes, and navigation route definitions.

### 🛠️ Infrastructure
* **[/build-logic](./build-logic)**: Contains Gradle Convention Plugins.
* **[libs.versions.toml](./gradle/libs.versions.toml)**: Centralized dependency management.

---

## 📁 Project Structure Detail

```
GlassEffectNavBar/
├── androidApp/           # Android application module
├── composeApp/           # Shared Compose Multiplatform library
│   └── src/commonMain/   # Shared app logic, navigation graph, adaptive UI
├── core/                 # Core shared modules
│   └── designsystem/     # Shared UI, themes, and navigation routes
├── features/             # Feature-specific modules
│   ├── home/             # Home feature (Image Gallery)
│   ├── menu/             # Menu feature
│   ├── profile/          # Profile feature
│   └── settings/         # Settings feature
├── build-logic/          # Custom Gradle convention plugins
├── iosApp/               # iOS app wrapper (Xcode project)
├── desktopApp/           # Desktop application module
└── gradle/               # Version catalog
```

---

## 🛠️ Technology Stack

### Shared
- **Kotlin Multiplatform** - Multi-platform development.
- **Compose Multiplatform** - UI framework.
- **Navigation 3** - redined type-safe navigation framework.
- **Haze** - Advanced blur and glass effects for Compose.
- **Coil 3** - Multiplatform image loading.
- **Kotlinx Serialization** - Type-safe route serialization.
- **Material 3** - Modern design system.

---

## 🚀 Getting Started
### Prerequisites
- **Android Studio** - Panda or later.
- **Xcode 15+** (for iOS).
- **JDK 17-21**.

### Running the Project
- **Android**: Use the `androidApp` configuration.
- **Desktop**: Use the `desktopApp` configuration or `./gradlew :desktopApp:run`.
- **iOS**: Open `iosApp` in Xcode or use the `iosApp` run configuration in Android Studio.

---

## 🔧 Navigation Implementation (Navigation 3)

The project uses the latest **Navigation 3** APIs:
- **Type-safe Routes**: Defined as `@Serializable` objects/classes in `:core:designsystem`.
- **NavDisplay**: Replaces `NavHost` for rendering the back stack.
- **Entry Providers**: Features provide their own entries via extension functions on `EntryProviderScope`.
- **Back Stack Management**: Manual control over a `SnapshotStateList<Route>` within `AppState`.

---

## 🎨 UI Features
- **Glass Effect**: Adaptive `BottomBar` and `NavRail` using the `Haze` library.
- **Adaptive Layout**: Automatically switches between `BottomBar` (Compact) and `NavRail` (Medium/Expanded) based on window size.
- **Shared Element Transitions**: Implemented in the Home feature using `SharedTransitionLayout`.
