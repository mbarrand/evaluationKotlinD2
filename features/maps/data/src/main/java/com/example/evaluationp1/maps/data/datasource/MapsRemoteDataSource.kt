package com.example.evaluationp1.maps.data.datasource

import com.example.evaluationp1.maps.data.dto.ValorantMapDto
import com.example.evaluationp1.maps.data.dto.ValorantMapsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/***
 * Classe permettant de récupérer les maps depuis l'API Valorant
 */
class MapsRemoteDataSource(
    private val httpClient: HttpClient
) {
    private val baseUrl = "https://valorant-api.com/v1"

    suspend fun getMaps(): List<ValorantMapDto> {
        val response: ValorantMapsResponse = httpClient.get("$baseUrl/maps").body()
        return response.data
    }
}
