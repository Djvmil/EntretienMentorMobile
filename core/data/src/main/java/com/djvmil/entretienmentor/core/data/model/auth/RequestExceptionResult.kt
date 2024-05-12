package com.djvmil.entretienmentor.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestExceptionResult(
    var error: RequestResult<String>? = null,
    override var message: String? = error?.message
) : Exception()
