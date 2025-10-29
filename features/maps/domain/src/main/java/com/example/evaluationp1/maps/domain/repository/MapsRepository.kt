package com.example.evaluationp1.maps.domain.repository

import com.example.evaluationp1.maps.domain.model.MapItem

/***
 * Interface représentant le repository des maps
 */
interface MapsRepository {
    suspend fun getMaps(): Result<List<MapItem>>
}
