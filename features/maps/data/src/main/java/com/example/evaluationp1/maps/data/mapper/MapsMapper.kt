package com.example.evaluationp1.maps.data.mapper

import com.example.evaluationp1.maps.data.dto.ValorantMapDto
import com.example.evaluationp1.maps.domain.model.MapItem

/***
 * Extension permettant de mapper un DTO ValorantMapDto en un objet MapItem
 */
fun ValorantMapDto.toDomain() = MapItem(
    id = uuid,
    name = displayName,
    imageUrl = splash
)
