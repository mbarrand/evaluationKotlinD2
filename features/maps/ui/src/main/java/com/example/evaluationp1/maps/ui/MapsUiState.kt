package com.example.evaluationp1.maps.ui

import com.example.evaluationp1.maps.domain.model.MapItem

/***
 * Modèle de données représentant l'état de l'écran des maps
 */
data class MapsUiState(
    val maps: List<MapItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface MapsUiAction

data class OnMapClick(val id: String) : MapsUiAction

sealed interface MapsUiEvent

data class GoMapDetails(val id: String) : MapsUiEvent
