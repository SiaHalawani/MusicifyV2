
# 🎵 Musicify – Final Project Documentation

## 📘 Overview
Musicify is a full-stack music management application built using **Java Spring Boot** for the backend and **JavaFX** for the frontend. It allows users to register, log in, and manage their music library including artists, albums, songs, playlists, and favorites. The app provides a professional user experience for music enthusiasts, developers, and academics exploring full-stack Java development.

## 📁 Project Structure

- **Backend:** Spring Boot app with full CRUD support and PostgreSQL integration
  - `controllers/`: REST APIs for `User`, `Song`, `Artist`, `Album`, `Playlist`, `Favorite`
  - `models/`: JPA Entities
  - `repositories/`: Spring Data JPA Repos
  - `services/`: Business logic layer

- **Frontend:** JavaFX UI with:
  - `DashboardScene.java`, `PlaylistViewScene.java`, etc.
  - Integrated views for Songs, Playlists, Settings, Register/Login

## 🚀 How to Run

### Backend
1. Clone the repository:
   ```bash
   git clone https://github.com/SiaHalawani/MusicifyV2
   ```
2. Open in IntelliJ or compatible IDE
3. Set up mySql and update `application.properties` with your DB credentials
4. Run `FinalMusicifyApplication.java`

### Frontend
1. Ensure JavaFX SDK is configured in your IDE
2. Run `MusicifyFrontendApp.java` to launch the client

## 🧩 Key Features
- User registration/login with global state
- Full CRUD for songs, playlists, albums, artists
- Playlist song management
- Favorite functionality
- JavaFX rich UI with navigation

## 🌐 GitHub Repository
[https://github.com/SiaHalawani/MusicifyV2](https://github.com/yourusername/musicify)

## 🔧 Technologies Used
- Java 21
- Spring Boot
- JavaFX 21
- mySql
- Maven

## 📌 Notes
- Set JavaFX VM options correctly in IDE
- Create and configure the database before running
- Project supports modular testing and expansion
