# First Compose App

A learning Android app that shows a Reddit-style news feed from [Lemmy](https://join-lemmy.org/) (lemmy.world), built with Jetpack Compose.

<!-- Screenshots: add images here, e.g. ![Feed](docs/screenshots/feed.png) -->

## Features

- **News feed**: "Hot" posts from all communities, with cursor-based infinite scrolling
- **Pagination states**: a loading indicator at the bottom of the list while the next page loads, and a snackbar with **Retry** if it fails, so posts already loaded stay on screen
- **Full-screen states** for the initial load and for errors when there are no posts to show
- **Comments screen** for each post, showing upvotes, downvotes and score
- **Bottom navigation** with Home, Favourite and Profile tabs (Favourite and Profile are placeholders for now)

## Tech stack

- [Kotlin](https://kotlinlang.org/) 2.4
- [Jetpack Compose](https://developer.android.com/compose) with Material 3
- [Navigation Compose](https://developer.android.com/develop/ui/compose/navigation)
- [Hilt](https://dagger.dev/hilt/) for dependency injection
- [Retrofit](https://square.github.io/retrofit/) with Gson, plus OkHttp logging
- [Coil 3](https://coil-kt.github.io/coil/) for image loading
- MVVM with clean-architecture layers (data / domain / presentation)

## Project structure

```
app/src/main/java/com/example/firstcomposeapp/
├── data/           # Retrofit API, DTOs, DTO → domain mappers, repository implementation
├── domain/         # Entities, repository interface, use cases
├── di/             # Hilt modules and Application class
├── navigation/     # Nav graphs, routes and navigation state
├── presentation/   # Screens, ViewModels and UI state (main, news, comments)
├── samples/        # Standalone Compose samples and previews
└── ui/theme/       # Colors, typography and theme
```

## Getting started

### Requirements

- Android Studio (a recent version that supports AGP 9.x)
- JDK 25 (Gradle can download it automatically through the toolchain resolver)
- An Android device or emulator running Android 11 (API 30) or higher

### Build and run

```bash
git clone https://github.com/JustAntis/compose_app.git
cd compose_app
./gradlew assembleDebug
```

Or open the project in Android Studio and run the `app` configuration.

No API key is needed, because the app uses the public Lemmy API.

## Data source

All data comes from the [Lemmy API v3](https://join-lemmy.org/api/) of the `lemmy.world` instance:

- `GET post/list` for the news feed
- `GET comment/list` for a post's comments

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
