# TVShowsApp

Application Android pour afficher une liste de séries TV populaires, développée avec Kotlin, Jetpack Compose, Retrofit et Dagger Hilt.

---

## Table des matières

* [Présentation](#présentation)
* [Fonctionnalités](#fonctionnalités)
* [Technologies utilisées](#technologies-utilisées)
* [Architecture](#architecture)
* [Installation](#installation)
* [Utilisation](#utilisation)
* [Auteurs](#auteurs)

---

## Présentation

TVShowsApp est une application Android native permettant de consulter les séries TV les plus populaires en temps réel.
Elle utilise l’API publique [Episodate](https://www.episodate.com/api) pour récupérer les données.

---

## Fonctionnalités

* Affichage des séries TV populaires en grille responsive
* Chargement paginé avec scroll infini
* Affichage des images et titres des séries
* Gestion des états de chargement et d’erreur
* Injection de dépendances avec Dagger Hilt
* Chargement d’images asynchrones avec Coil
* Architecture MVVM avec ViewModel et Repository

---

## Technologies utilisées

* Kotlin
* Jetpack Compose (Material3)
* Retrofit + OkHttp + Gson pour appels réseau
* Dagger Hilt pour injection de dépendances
* Coil pour le chargement d’images
* Coroutines & Flow pour la gestion asynchrone
* API publique Episodate

---

## Architecture

L’application est organisée selon le pattern MVVM :

```
com.eseo.cinemaproject
├── di                // Modules Dagger Hilt (NetworkModule)
├── model             // Classes de données (TVShow, TVShowsResponse)
├── repository        // Logique d’accès aux données via Retrofit
├── service           // Interface Retrofit (TVShowsApiService)
├── viewmodel         // ViewModel pour gérer l’UI state
├── view              // Composables Jetpack Compose pour l’interface
├── ui.theme          // Thèmes, couleurs, typographies
├── MainActivity.kt   // Activité principale lançant l’UI Compose
├── TVShowsApplication.kt // Classe Application avec Hilt
```

---

## Installation

1. Clone le dépôt :

```bash
git clone https://github.com/kurama-a/CinemaProject
```

2. Ouvre le projet dans Android Studio

3. Versions SDK Android 34+ et Kotlin 1.8+

4. Synchronise Gradle :
   **File > Sync Project with Gradle Files**

5. Build et lance l’application sur un émulateur ou appareil physique

---

## Utilisation

* L’application affiche automatiquement la première page des séries TV populaires.
* Scroll vers le bas pour charger plus de séries (pagination infinie).
* En cas d’erreur réseau, un message s’affiche.
* L’interface est responsive et s’adapte à différents écrans.

---

# Auteurs

- Paul ARNAUD
- Emma VANDENBOSSCHE
- Loup LANGARD
