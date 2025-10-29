package com.example.evaluationp1.maps.data.repository

import com.example.evaluationp1.maps.data.datasource.MapsRemoteDataSource
import com.example.evaluationp1.maps.data.mapper.toDomain
import com.example.evaluationp1.maps.domain.model.MapItem
import com.example.evaluationp1.maps.domain.repository.MapsRepository

/***
 * Implémentation de l'interface MapsRepository
 */
class MapsRepositoryImpl(
    private val remote: MapsRemoteDataSource
) : MapsRepository {
    override suspend fun getMaps(): Result<List<MapItem>> {
        return try {
            val items = remote.getMaps().map { it.toDomain() }
            Result.success(items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
