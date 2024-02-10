package com.example.samplehttpconnect

import kotlinx.serialization.Serializable

@Serializable
data class SampleData(
    val id: Int,
    val name: String
)