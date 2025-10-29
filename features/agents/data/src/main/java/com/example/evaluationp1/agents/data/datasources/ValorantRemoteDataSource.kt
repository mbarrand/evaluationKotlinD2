package com.example.evaluationp1.agents.data.datasources

import com.example.evaluationp1.agents.data.dto.ValorantAgentDto
import com.example.evaluationp1.agents.data.dto.ValorantAgentsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/***
 * Classe contenant les données des agents de l'API Valorant
 */
class ValorantRemoteDataSource(
    private val httpClient: HttpClient
) {
    private val baseUrl = "https://valorant-api.com/v1"

    suspend fun getAgents(): List<ValorantAgentDto> {
        val response: ValorantAgentsResponse = httpClient.get("$baseUrl/agents").body()
        return response.data
    }
}
