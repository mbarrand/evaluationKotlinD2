package com.example.evaluationp1.agents.ui

import com.example.evaluationp1.agents.domain.model.Agent

/***
 * Classe contenant les données de l'agent
 */
data class AgentUiState(
    val agents: List<Agent> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface AgentUiAction

data class OnAgentClick(val id: String) : AgentUiAction

sealed interface AgentUiEvent

// Event de son et vibration
data class SendSoundAndVibration(val id: String) : AgentUiEvent
data object UsingLocalCache : AgentUiEvent
