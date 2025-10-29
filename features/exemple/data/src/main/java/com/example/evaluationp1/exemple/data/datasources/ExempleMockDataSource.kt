package com.example.evaluationp1.exemple.data.datasources

import com.example.evaluationp1.exemple.data.dto.ExempleDto

class ExempleMockDataSource {
    private val list = mutableListOf(
        ExempleDto(1, "Exemple 1"),
        ExempleDto(2, "Exemple 2"),
        ExempleDto(3, "Exemple 3")
    )

    suspend fun getExemples(): List<ExempleDto> {
        return list.toList()
    }
    suspend fun getExemple(id:Int ): ExempleDto {
        val idx = list.indexOfFirst { it.id == id }
        return list[idx]
    }
    suspend fun createExemple(exemple: ExempleDto): ExempleDto {
        val created = exemple.copy(id = exemple.id)
        list.add(created)
        return created
    }

    suspend fun updateExemple(exemple: ExempleDto): ExempleDto {
        val idx = list.indexOfFirst { it.id == exemple.id }
        require(idx >= 0) { "Exemple not found" }
        list[idx] = exemple
        return exemple
    }

    suspend fun deleteExemple(id: Int) {
        list.removeAll { it.id == id }
    }
}