package com.example.evaluationp1.agents.data.repository

import com.example.evaluationp1.agents.data.datasources.ValorantRemoteDataSource
import com.example.evaluationp1.agents.data.mapper.toDomain
import com.example.evaluationp1.agents.data.local.LocalAgentsDataSource
import com.example.evaluationp1.agents.data.mapper.toEntity
import com.example.evaluationp1.agents.domain.repository.AgentsRepository
import com.example.evaluationp1.agents.domain.model.AgentsResult
import com.example.evaluationp1.agents.domain.model.DataSource

class AgentsRepositoryImpl (
    private val remote: ValorantRemoteDataSource,
    private val local: LocalAgentsDataSource
): AgentsRepository {
    override suspend fun getAgents(): Result<AgentsResult> {
        return try {
            val remoteAgents = remote.getAgents()
            val domain = remoteAgents.map { it.toDomain() }
            local.replaceAll(remoteAgents.map { it.toEntity() })
            Result.success(AgentsResult(domain, DataSource.REMOTE))
        } catch (e: Exception) {
            val cached = local.getAgents().map { it.toDomain() }
            Result.success(AgentsResult(cached, DataSource.LOCAL))
        }
    }
}