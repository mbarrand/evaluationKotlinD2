package com.example.evaluationp1.exemple.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluationp1.exemple.domain.model.Exemple
import com.example.evaluationp1.exemple.domain.repository.ExempleRepository
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

data class ExempleUiState(
    val exemples: List<Exemple> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface ExempleUiAction

data class ExempleAction1(val id: Int) : ExempleUiAction
data class ExempleAction2(val id: Int) : ExempleUiAction
//data object ExempleAction2 : ExempleUiAction

sealed interface ExempleUiEvent

data class ExempleEvent1(val id: Int) : ExempleUiEvent
data class ExempleEvent2(val id: Int) : ExempleUiEvent
//class ExempleEvent2() : ExempleUiEvent

class ExempleViewModel() : ViewModel(), KoinComponent {

    // Liste initiale des exemples
    private var _uiState = MutableStateFlow(ExempleUiState())
    val state: StateFlow<ExempleUiState> = _uiState

    private var _uiEvents = Channel<ExempleUiEvent>()
    val events: Flow<ExempleUiEvent> = _uiEvents.receiveAsFlow()


    val exempleRepository: ExempleRepository by inject()

    private fun launchView() {
        viewModelScope.launch(Dispatchers.IO) {
            val exemples = exempleRepository.getExemples()
            _uiState.update { it.copy (exemples = exemples.getOrDefault(emptyList())) }
        }
    }

    init {
        launchView()
    }

//    // Fonction pour toggle l’état "déroulé"
//    fun toggleExpanded(id: Int) {
//        _uiState.update { currentState ->
//            val updatedTypes =
//                currentState.types.map { type ->
//                    if (type.id == id) {
//                        type.copy(expanded = !type.expanded)
//                    } else {
//                        type
//                    }
//                }
//            currentState.copy(types = updatedTypes)
//        }
//    }

    fun onAction(action: ExempleUiAction) {
        when (action) {
            is ExempleAction1 -> DeleteExemple(action.id)
            is ExempleAction2 -> EditExemple(action.id)
        }
    }


    // Modifier un Exemple
    private fun EditExemple(id: Int) {
        viewModelScope.launch {
            _uiEvents.send(ExempleEvent1(id))
        }
    }

    // Supprimer un Exemple
    private fun DeleteExemple(id: Int) {
        val currentState = state.value

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = exempleRepository.deleteExemple(id)

            result.onSuccess { _uiEvents.send(ExempleEvent2(id)) }
                .onFailure { } // TODO: afficher message d'erreur
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}





