package com.example.evaluationp1.agents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluationp1.agents.domain.repository.AgentsRepository
import com.example.evaluationp1.agents.domain.model.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/***
 * ViewModel permettant de gérer les données des agents
 */
class AgentsViewModel() : ViewModel(), KoinComponent {

    // Liste initiale des agents
    private var _uiState = MutableStateFlow(AgentUiState())
    val state: StateFlow<AgentUiState> = _uiState

    private var _uiEvents = Channel<AgentUiEvent>(Channel.BUFFERED)
    val events: Flow<AgentUiEvent> = _uiEvents.receiveAsFlow()


    val agentRepository: AgentsRepository by inject()

    private fun launchView() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = agentRepository.getAgents()
            result.onSuccess { data ->
                _uiState.update { it.copy(agents = data.agents, isLoading = false, error = null) }
                if (data.source == DataSource.LOCAL) {
                    _uiEvents.send(UsingLocalCache)
                }
            }.onFailure { err ->
                _uiState.update { it.copy(isLoading = false, error = err.message) }
                _uiEvents.send(UsingLocalCache)
            }
        }
    }

    init {
        launchView()
    }

    fun onAction(action: AgentUiAction) {
        when (action) {
            is OnAgentClick -> soundAndVibration(action.id)
        }
    }


    // Modifier un Exemple
    private fun soundAndVibration(id: String) {
        viewModelScope.launch {
            _uiEvents.send(SendSoundAndVibration(id))
        }
    }

}





