package com.example.evaluationp1.agents.domain.repository

import com.example.evaluationp1.agents.domain.model.AgentsResult

/***
 * Interface contenant les données des agents
 */
interface AgentsRepository {
    suspend fun getAgents(): Result<AgentsResult>
}