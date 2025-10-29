package com.example.evaluationp1.exemple.data.mapper

import com.example.evaluationp1.exemple.data.dto.ExempleDto
import com.example.evaluationp1.exemple.domain.model.Exemple

fun ExempleDto.toDomain() = Exemple(id, name)
fun Exemple.toDto() = ExempleDto(id, name)

