package com.example.evaluationp1.exemple.api

import com.example.evaluationp1.exemple.data.datasources.ExempleMockDataSource
import com.example.evaluationp1.exemple.data.repository.ExempleRepositoryImpl
import com.example.evaluationp1.exemple.domain.repository.ExempleRepository
import org.koin.dsl.module

val ExempleModule = module {
    single<ExempleRepository> {
        ExempleRepositoryImpl(ExempleMockDataSource())
    }
}