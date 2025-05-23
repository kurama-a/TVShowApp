# 1.  master
   Branche principale, stable, contenant la version prête à être publiée ou la plus stable du projet.

# 2. setup-dependencies
   Ajouter la configuration initiale du projet :

Configuration Gradle avec Retrofit, Coil, Dagger-Hilt, Compose, Coroutines, ViewModel

Configuration Hilt (Application class + module Retrofit)

# 3. api-service
   Implémentation du service Retrofit pour consommer l’API https://www.episodate.com/api/most-popular?page=1

Création de l’interface Retrofit + modèle JSON

# 4. repository
   Implémentation du Repository qui utilise le service Retrofit et fournit les données au ViewModel

# 5. viewmodel
   Création du ViewModel avec gestion des états (chargement, données, erreur)

Exposition d’un état réactif à la UI

# 6. ui-grid
   Implémentation de l’interface utilisateur avec Jetpack Compose :

LazyVerticalGrid affichant les séries

Gestion du scroll infini et du spinner en bas

# 7. state-management
   Gestion fine des différents états (chargement initial, chargement supplémentaire)

Ajout d’un système d’indicateur de chargement