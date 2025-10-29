package com.example.evaluationp1.agents.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/***
 * Simulation de la BDD
 */
@Database(
    entities = [AgentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AgentsDatabase : RoomDatabase() {
    abstract fun agentsDao(): AgentsDao
}
