package com.example.evaluationp1.maps.api

import com.example.evaluationp1.maps.data.datasource.MapsRemoteDataSource
import com.example.evaluationp1.maps.data.repository.MapsRepositoryImpl
import com.example.evaluationp1.maps.domain.repository.MapsRepository
import org.koin.dsl.module

/***
 * Module contenant les dépendances liées aux maps
 */
val MapsModule = module {
    single { MapsRemoteDataSource(get()) }
    single<MapsRepository> { MapsRepositoryImpl(get()) }
}
