package com.example.evaluationp1.agents.data.mapper

import com.example.evaluationp1.agents.data.dto.AgentsDto
import com.example.evaluationp1.agents.data.dto.ValorantAgentDto
import com.example.evaluationp1.agents.data.local.AgentEntity
import com.example.evaluationp1.agents.domain.model.Agent

/***
 * Extension permettant de mapper les données
 */
fun AgentsDto.toDomain() = Agent(id.toString(), name)
fun Agent.toDto() = AgentsDto(id.toIntOrNull() ?: 0, name)

fun ValorantAgentDto.toDomain() = Agent(uuid, displayName)

fun ValorantAgentDto.toEntity() = AgentEntity(id = uuid, name = displayName)

fun AgentEntity.toDomain() = Agent(id = id, name = name)

