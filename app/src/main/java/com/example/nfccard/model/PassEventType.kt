package com.example.nfccard.model


import kotlinx.serialization.Serializable

@Serializable
enum class PassEventType {
    ENTRY,
    EXIT
}