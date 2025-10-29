package com.example.evaluationp1.agents.data.dto

import com.google.gson.annotations.SerializedName

data class ValorantAgentsResponse(
    @SerializedName("data") val data: List<ValorantAgentDto>
)

data class ValorantAgentDto(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("isPlayableCharacter") val isPlayableCharacter: Boolean
)
