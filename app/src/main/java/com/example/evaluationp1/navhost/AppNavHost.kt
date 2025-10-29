package com.example.evaluationp1.navhost

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.evaluationp1.agents.ui.AgentsScreen
import com.example.evaluationp1.agents.ui.AgentsViewModel
import com.example.evaluationp1.maps.ui.MapsScreen
import com.example.evaluationp1.maps.ui.MapsViewModel

/***
 * Fichier contenant la navigation entre les pages
 */
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Agent.route,
    ) {
        // Route pour naviguer vers la page des agents
        composable(route = Screen.Agent.route) {
            val vm: AgentsViewModel = viewModel()
            AgentsScreen(
                viewModel = vm,
                onAgentClick = { /* Lancement du son et de la vibration */ },
                navController = navController
            )
        }
        // Route pour naviguer vers la page des maps
        composable(route = Screen.Map.route) {
            val vm: MapsViewModel = viewModel()
            MapsScreen(
                viewModel = vm,
                onMapClick = { /* Ajouter bruit et vib */ },
                navController = navController
            )
        }
    }
}