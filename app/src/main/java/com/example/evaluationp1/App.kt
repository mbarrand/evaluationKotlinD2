package com.example.evaluationp1

import android.app.Application
import com.example.evaluationp1.core.data.network.NetworkModule
import com.example.evaluationp1.agents.api.AgentsModule
import com.example.evaluationp1.maps.api.MapsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/***
 * Fichier contenant le point d'entrée de l'application et les modules
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Démarrage de Koin + point d'entrée
        startKoin {
            androidContext(this@App)
            modules(
                NetworkModule,
                AgentsModule,
                MapsModule
            )
        }
    }
}