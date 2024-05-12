package com.djvmil.entretienmentor.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    var id: Int? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var avatar: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null
)
