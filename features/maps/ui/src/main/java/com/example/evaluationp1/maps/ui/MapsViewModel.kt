package com.example.evaluationp1.maps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluationp1.maps.domain.repository.MapsRepository
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
 * ViewModel de l'écran des maps
 */
class MapsViewModel : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(MapsUiState(isLoading = true))
    val state: StateFlow<MapsUiState> = _uiState

    private val _events = Channel<MapsUiEvent>(Channel.BUFFERED)
    val events: Flow<MapsUiEvent> = _events.receiveAsFlow()

    private val repository: MapsRepository by inject()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMaps()
            result.onSuccess { maps ->
                _uiState.update { it.copy(maps = maps, isLoading = false, error = null) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun onAction(action: MapsUiAction) {
        when (action) {
            is OnMapClick -> goDetails(action.id)
        }
    }

    private fun goDetails(id: String) {
        viewModelScope.launch {
            _events.send(GoMapDetails(id))
        }
    }
}
