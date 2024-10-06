package com.example.nfccard.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var name: String = "",
    var surname: String = "",
    var photoUri: String = "",
    var nfcId: String = ""
)