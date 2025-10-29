package com.example.evaluationp1.exemple.data.repository

import com.example.evaluationp1.exemple.data.datasources.ExempleMockDataSource
import com.example.evaluationp1.exemple.data.mapper.toDomain
import com.example.evaluationp1.exemple.data.mapper.toDto
import com.example.evaluationp1.exemple.domain.model.Exemple
import com.example.evaluationp1.exemple.domain.repository.ExempleRepository

class ExempleRepositoryImpl (private val remote: ExempleMockDataSource): ExempleRepository {
    override suspend fun createExemple(exemple: Exemple): Result<Unit> {
        remote.createExemple(exemple.toDto()).toDomain()
        return Result.success(Unit)
    }

    override suspend fun getExemples(): Result<List<Exemple>> {
        val allExemples = remote.getExemples().map { it.toDomain() }
        return Result.success(allExemples)
    }

    override suspend fun getExemple(id: Int): Result<Exemple> {
        val exemple = remote.getExemple(id).toDomain()
        return Result.success(exemple)
    }

    override suspend fun updateExemple(exemple: Exemple): Result<Unit> {
        remote.updateExemple(exemple.toDto()).toDomain()
        return Result.success(Unit)
    }

    override suspend fun deleteExemple(id: Int): Result<Unit> {
        remote.deleteExemple(id)
        return Result.success(Unit)
    }
}