# Fetch Rewards Coding Exercise - Android

## Overview

This is a native Android app developed in Kotlin for the Fetch Rewards coding exercise. The app retrieves data from the provided endpoint and displays a list of items based on the given requirements.

## Features

- **Data Fetching**: Utilized Retrofit and Gson for data fetching and parsing from the provided URL.
- **Grouping and Sorting**: Implements grouping by "listId" and sorting by "listId" and "name".
- **Filtering**: Excludes items with blank or null "name" values.
- **User Interface**: Displays the list in a clean, user-friendly format using Jetpack Compose (Lazy Column).
- **Dependency Injection**: Integrated Hilt for dependency injection.
- **Navigation**: Employed Jetpack Compose Navigation for managing app navigation.
- **Architecture**: Made use of MVVM and clean architecture along with SOLID design principles.
- **Testing**: Used JUnit and MockK for unit testing.


## Installation

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device
