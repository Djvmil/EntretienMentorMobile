package com.djvmil.entretienmentor.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestResult<T>(
    var code: Int? = null,
    var message: String? = null,
    var data: T? = null,
    var timestamp: String? = null
)
