package com.example.nfccard.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var name: String = "LOx",
    var surname: String = "Loxich",
    var photoUri: String = "",
    var nfcId: String = ""
)