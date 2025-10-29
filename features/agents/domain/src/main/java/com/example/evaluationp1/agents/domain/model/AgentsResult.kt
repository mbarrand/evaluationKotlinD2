package com.example.evaluationp1.agents.domain.model

enum class DataSource { REMOTE, LOCAL }

data class AgentsResult(
    val agents: List<Agent>,
    val source: DataSource
)
