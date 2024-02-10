package com.example.samplehttpconnect

import kotlinx.serialization.Serializable

@Serializable
data class QiitaResponse(
    val title: String,
    val url: String,
    val body: String,
    val user: User,
    val createdDate: String
) {
    @Serializable
    data class User(
        val id: String,
        val profileImageUrl: String
    )
}