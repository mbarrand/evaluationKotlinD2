package com.example.evaluationp1.agents.api

import com.example.evaluationp1.agents.data.datasources.ValorantRemoteDataSource
import com.example.evaluationp1.agents.data.repository.AgentsRepositoryImpl
import com.example.evaluationp1.agents.domain.repository.AgentsRepository
import com.example.evaluationp1.agents.data.local.AgentsDatabase
import com.example.evaluationp1.agents.data.local.LocalAgentsDataSource
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/***
 * Module contenant les dépendances de l'agents
 */
val AgentsModule = module {
    single { ValorantRemoteDataSource(get()) }
    single {
        Room.databaseBuilder(
                androidContext(),
                AgentsDatabase::class.java,
                "agents.db"
            ).fallbackToDestructiveMigration(false).build()
    }
    single { LocalAgentsDataSource(get()) }
    single<AgentsRepository> { AgentsRepositoryImpl(get(), get()) }
}