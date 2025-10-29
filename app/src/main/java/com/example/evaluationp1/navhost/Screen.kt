package com.example.evaluationp1.navhost

/***
 * Fichier contenant les routes
 */
sealed class Screen(val route: String) {
    object Agent : Screen("agents") // Route pour la page des agents
    object Map : Screen("maps") // Route pour la page des maps
}