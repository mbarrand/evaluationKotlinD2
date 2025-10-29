package com.example.evaluationp1.agents.data.local

import androidx.room.withTransaction

class LocalAgentsDataSource(
    private val db: AgentsDatabase
) {
    private val dao = db.agentsDao()

    suspend fun getAgents(): List<AgentEntity> = dao.getAgents()

    suspend fun replaceAll(agents: List<AgentEntity>) {
        db.withTransaction {
            dao.clear()
            dao.insertAll(agents)
        }
    }
}
