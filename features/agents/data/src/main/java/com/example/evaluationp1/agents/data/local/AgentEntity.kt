package com.example.evaluationp1.agents.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Classe contenant l'entité agent pour le dao
 */
@Entity(tableName = "agents")
data class AgentEntity(
    @PrimaryKey val id: String,
    val name: String
)
