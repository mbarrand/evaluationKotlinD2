package com.example.evaluationp1.maps.data.dto

import com.google.gson.annotations.SerializedName

data class ValorantMapDto(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("splash") val splash: String?
)
