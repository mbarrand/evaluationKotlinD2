package com.example.evaluationp1.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import org.koin.dsl.module

/***
 * Module qui fournit un HttpClient Ktor partagé dans toute l'app
 */
val NetworkModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.INFO
            }
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}
