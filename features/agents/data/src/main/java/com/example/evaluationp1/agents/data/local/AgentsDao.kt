package com.example.evaluationp1.agents.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/***
 * Classe contenant le dao pour les agents, avec les requêtes SQL
 */
@Dao
interface AgentsDao {
    @Query("SELECT * FROM agents")
    suspend fun getAgents(): List<AgentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(agents: List<AgentEntity>)

    @Query("DELETE FROM agents")
    suspend fun clear()
}
