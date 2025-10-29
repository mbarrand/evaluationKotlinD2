package com.example.evaluationp1.maps.data.dto

import com.google.gson.annotations.SerializedName

data class ValorantMapsResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<ValorantMapDto>
)
