# Agent Rules & Project Map

## Project Overview
Rick and Morty Android application using the Rick and Morty API, demonstrating modern Android development practices.

## Architecture
The project follows a **Clean Architecture** approach with **MVVM** pattern:

- **Presentation Layer (`com.jacqui.rickandmorty.presentation`):**
  - Uses **Jetpack Compose** for UI.
  - **ViewModels** handle state and interact with repositories.
  - **Navigation 3** (experimental) for app navigation.
- **Data Layer (`com.jacqui.rickandmorty.data`):**
  - **Domain Models (`data.domain`):** POJOs representing business entities.
  - **Repositories (`data.repository`):** Abstracts data sources.
  - **Mappers (`data.mappers`):** Extension functions to convert DTOs to Domain models.
- **Data Sources (`com.jacqui.rickandmorty.sources`):**
  - **Remote (`sources.remote`):** Ktor implementation of the API and Paging Source.

## Key Libraries
- **UI:** Jetpack Compose, Material 3.
- **Dependency Injection:** Koin.
- **Networking:** Ktor with OkHttp engine and Kotlinx Serialization.
- **Paging:** Jetpack Paging 3.
- **Navigation:** Navigation 3.
- **Image Loading:** Coil.
- **Logging:** Timber.
- **Network Inspection:** Chucker.

## Package Structure
- `activity`: Main entry point (MainActivity).
- `app`: Application class and Koin initialization.
- `data`:
    - `domain`: Business models.
    - `mappers`: Data transformation logic.
    - `repository`: Repository interfaces and implementations.
    - `utils`: Result wrappers.
- `di`: Koin modules configuration.
- `navigation`: Navigation definitions (Screens and AppNavigation).
- `sources`:
    - `remote`: Ktor API implementation, DTOs, and PagingSource.
- `view`:
    - `component`: Reusable UI components.
    - `ui.theme`: Compose theme configuration.
    - `viewmodel`: MVVM ViewModels.
    - Screen files (e.g., `CharacterScreen.kt`).

## Development Rules
- **Dependency Injection:** Use Koin for DI. Define modules in `com.jacqui.rickandmorty.di.Module.kt`.
- **UI:** Use Jetpack Compose and Material 3 components.
- **Data Flow:** Repository -> ViewModel -> UI.
- **Models:** Always map DTOs to Domain models before they reach the ViewModel.
- **Logging:** Use Timber for logging.
- **Paging:** Use Paging 3 for list data.
- **Navigation:** Use Navigation 3 with `Screen` definitions.
