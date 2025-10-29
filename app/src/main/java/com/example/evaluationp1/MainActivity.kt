package com.example.evaluationp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.evaluationp1.navhost.AppNavHost
import com.example.evaluationp1.ui.theme.EvaluationP1Theme

/***
 * Main Activity
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvaluationP1Theme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
