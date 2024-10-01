package com.example.nfccard.model

import kotlinx.serialization.Serializable

@Serializable
data class PassEvent(
    val timestamp: Long,
    val eventType: PassEventType
)