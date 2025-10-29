package com.example.evaluationp1.exemple.domain.repository

import com.example.evaluationp1.exemple.domain.model.Exemple

interface ExempleRepository {
    suspend fun getExemples(): Result<List<Exemple>>
    suspend fun getExemple(id : Int): Result<Exemple>
    suspend fun createExemple(exemple: Exemple): Result<Unit>
    suspend fun updateExemple(exemple: Exemple): Result<Unit>
    suspend fun deleteExemple(id: Int): Result<Unit>
}