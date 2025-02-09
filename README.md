# Akakce Case Study - Android E-commerce App

A modern Android e-commerce application built with Kotlin, demonstrating clean architecture principles and modern Android development practices.

## Features

- Browse products in both horizontal and vertical layouts
- View detailed product information
- Rating system for products
- Responsive design with smooth scrolling
- Error handling and user feedback

## Technology Stack

- **Language:** Kotlin
- **Architecture:** Clean Architecture with MVVM pattern
- **Dependency Injection:** Hilt
- **Networking:** Retrofit with OkHttp
- **Image Loading:** Glide
- **UI Components:** Material Design
- **Asynchronous Programming:** Coroutines
- **Data Binding:** View Binding

## Project Structure

The project follows a clean architecture approach with the following main packages:

- **data:** Contains repository implementations and data sources
- **domain:** Contains business logic, models, and repository interfaces
- **presentation:** Contains UI-related code (activities, view models, adapters)
- **utils:** Contains utility classes and extensions

## Key Components

### API Integration
The app uses Retrofit to communicate with a REST API:

### Repository Pattern
Implements a clean repository pattern for data management:

### MVVM Architecture
Uses ViewModels with LiveData for reactive UI updates:


## Acknowledgments

- FakeStore API for providing the product data
- Material Design for UI components