# MovieApp

**MovieApp** is an Android application built using Jetpack Compose that allows users to discover and explore movies. The app provides information on trending, top-rated, and upcoming movies, along with detailed descriptions and user ratings.

## Features

- **Movie Listings:** Browse movies categorized as trending, top-rated, and upcoming.
- **Movie Details:** View comprehensive details about each movie, including synopsis, release date, and ratings.
- **Search Functionality:** Search for movies by title.
- **Favorites:** Add movies to a personal watchlist for future reference.
- **Offline Access:** Access previously viewed movies without an internet connection.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern, ensuring a clear separation of concerns and facilitating testability and maintainability.

## Tech Stack

- **Kotlin:** Utilized for all programming within the app.
- **Jetpack Compose:** Employed for building the user interface.
- **Hilt:** Used for dependency injection.
- **Glide:** Handles image loading 
- **Coroutines & Flow:** Manage asynchronous operations and data streams.

## TODO 
- **Room:** Implement local database storage for offline capabilities.
- **Coroutines & Flow:** Manage asynchronous operations and data streams.
- **Paging 3:** Supports efficient loading of paginated data.
- **DataStore:** Stores user preferences and settings.
- **Firebase Authentication:** Manages user authentication via Google sign-in.
- **YouTube Player API:** Plays movie trailers within the app.

## Getting Started

To run this project locally:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your_username/MovieApp.git
