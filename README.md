# Some useful links

- https://kotlinlang.org/docs/multiplatform.html
- https://kmp.jetbrains.com/
- https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-getting-started.html
- https://cashapp.github.io/sqldelight/2.0.1/multiplatform_sqlite/
- https://github.com/russhwolf/To-Do/
- https://ktor.io/docs/create-client.html
- https://github.com/qdsfdhvh/compose-imageloader
- https://github.com/Foso/Ktorfit
- https://github.com/AAkira/Kotlin-Multiplatform-Libraries
- https://github.com/adrielcafe/voyager


# Build

### Desktop
```
./gradlew :composeApp:run 
```
### iOS
Click XCode run button direct.

This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
